package tenno_mod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

import static tenno_mod.TennoMod.logger;

public class ReduceRandomCardInHandAction extends AbstractGameAction {
  public ReduceRandomCardInHandAction() {
    this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
    this.duration = Settings.ACTION_DUR_FAST;
  }

  @Override
  public void update() {
    if (this.duration == com.megacrit.cardcrawl.core.Settings.ACTION_DUR_FAST) {
      ArrayList<AbstractCard> groupCopy = new ArrayList();
      logger.info(AbstractDungeon.player.hand.size());
      for (AbstractCard c : AbstractDungeon.player.hand.group) {
        if ((c.cost > 0) && (c.costForTurn > 0) && (!c.freeToPlayOnce)) {
          groupCopy.add(c);
        } else {
          logger.info("COST IS 0: " + c.name);
        }
      }
      AbstractCard c = null;
      logger.info(groupCopy.size());
      if (!groupCopy.isEmpty()) {
        c = groupCopy.get(AbstractDungeon.cardRandomRng.random(0, groupCopy.size() - 1));
        c.setCostForTurn(c.costForTurn - 1);
        c.superFlash();
        c.applyPowers();
      }
      this.isDone = true;
      return;
    }
    tickDuration();

  }
}
