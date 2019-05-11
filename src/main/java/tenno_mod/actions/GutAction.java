
package tenno_mod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class GutAction extends com.megacrit.cardcrawl.actions.AbstractGameAction {
  public GutAction(AbstractCreature target) {
    this.duration = Settings.ACTION_DUR_XFAST;
    this.actionType = AbstractGameAction.ActionType.BLOCK;
    this.target = target;
  }

  public void update() {
    if ((this.duration == Settings.ACTION_DUR_XFAST) &&
        (this.target != null)) {
      int count = 0;
      for (AbstractPower p : target.powers) {
        if (p.type == AbstractPower.PowerType.DEBUFF && !p.ID.equals(
            "Shackled")) {
          count++;
        }
      }
      AbstractDungeon.actionManager.addToTop(new DrawCardAction(AbstractDungeon.player, count));
    }
    tickDuration();
  }
}