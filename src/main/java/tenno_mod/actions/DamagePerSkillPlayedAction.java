
package tenno_mod.actions;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import tenno_mod.shared.SkillUtils;

public class DamagePerSkillPlayedAction extends com.megacrit.cardcrawl.actions.AbstractGameAction {
  private DamageInfo info;

  public DamagePerSkillPlayedAction(AbstractCreature target, DamageInfo info, AbstractGameAction.AttackEffect effect) {
    this.info = info;
    setValues(target, info);
    this.actionType = com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType.DAMAGE;
    this.attackEffect = effect;
  }

  public DamagePerSkillPlayedAction(AbstractCreature target, DamageInfo info) {
    this(target, info, AbstractGameAction.AttackEffect.NONE);
  }

  public void update() {
    this.isDone = true;
    if ((this.target != null) && (this.target.currentHealth > 0)) {
      int count = SkillUtils.countSkills();

      for (int i = 0; i < count; i++) {
        AbstractDungeon.actionManager.addToTop(
            new com.megacrit.cardcrawl.actions.common.DamageAction(this.target, this.info, this.attackEffect));
      }
    }
  }
}