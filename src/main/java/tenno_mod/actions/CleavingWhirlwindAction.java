package tenno_mod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;

import java.util.List;

public class CleavingWhirlwindAction extends AbstractGameAction {
  private AbstractCard itself;
  private AbstractPlayer p;
  private DamageInfo info;

  public CleavingWhirlwindAction(AbstractPlayer p, DamageInfo info, AbstractCard itself) {
    this.itself = itself;
    this.p = p;
    this.duration = Settings.ACTION_DUR_FAST;
    this.info = info;
  }

  public void update() {
    List<AbstractMonster> monsters = AbstractDungeon.getCurrRoom().monsters.monsters;
    boolean monsterDied = false;
    for (int i = 0; i < monsters.size(); i++) {
      AbstractMonster target = monsters.get(i);
      target.damage(this.info);
      if ((target.isDying || target.currentHealth <= 0) && (!target.halfDead)) {
        monsterDied = true;
      }
    }
    if (monsterDied) {
      // Return to hand.
      AbstractDungeon.actionManager.addToBottom(new ReturnToHandAction(itself));
    }
    this.isDone = true;
  }
}
