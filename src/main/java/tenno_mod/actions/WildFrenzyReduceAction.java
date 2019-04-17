package tenno_mod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static tenno_mod.TennoMod.logger;

public class WildFrenzyReduceAction extends AbstractGameAction {
  public WildFrenzyReduceAction() {
    this.actionType = ActionType.CARD_MANIPULATION;
    this.duration = Settings.ACTION_DUR_FAST;
  }

  @Override
  public void update() {
    if (this.duration == Settings.ACTION_DUR_FAST) {
      for (AbstractCard c : AbstractDungeon.player.hand.group) {
        if (c.type == AbstractCard.CardType.ATTACK && (c.cost > 0) && (c.costForTurn > 0) && (!c.freeToPlayOnce)) {
          reduceCost(c);
        } else {
          logger.info("COST IS 0: " + c.name);
        }
      }

      this.isDone = true;
    }
    tickDuration();
  }

  public void reduceCost(AbstractCard c) {
    changeCost(c, c.costForTurn - 1);
  }

  public void changeCost(AbstractCard c, int newCost) {
    c.setCostForTurn(newCost);
    c.superFlash();
    c.applyPowers();
  }
}
