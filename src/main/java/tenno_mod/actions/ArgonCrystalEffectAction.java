package tenno_mod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

import static tenno_mod.TennoMod.logger;

public class ArgonCrystalEffectAction extends AbstractGameAction {
  public ArgonCrystalEffectAction() {
    this.actionType = ActionType.CARD_MANIPULATION;
    this.duration = Settings.ACTION_DUR_FAST;
  }

  @Override
  public void update() {
    if (this.duration == Settings.ACTION_DUR_FAST) {
      CardGroup canReduce = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
      for (AbstractCard c : AbstractDungeon.player.hand.group) {
        if ((c.cost > 0) && (c.costForTurn > 0) && (!c.freeToPlayOnce)) {
          canReduce.addToTop(c);
        } else {
          logger.info("COST IS 0: " + c.name);
        }
      }
      ArrayList<AbstractCard> reducedCards = new ArrayList<>();
      if (!canReduce.isEmpty()) {
        canReduce.shuffle();
        reduceCost(canReduce.group.get(0));
        reducedCards.add(canReduce.group.get(0));
//        if (canReduce.size() >= 2) {
//          reduceCost(canReduce.group.get(1));
//          reducedCards.add(canReduce.group.get(1));
//        }
      }

      // Find the card to increase.
      CardGroup canIncrease = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
      for (AbstractCard c : AbstractDungeon.player.hand.group) {
        if ((c.cost >= 0) && (c.costForTurn < 3) && (!c.freeToPlayOnce) && !reducedCards.contains(c)) {
          canIncrease.addToTop(c);
        }
      }
      if (!canIncrease.isEmpty()) {
        canIncrease.shuffle();
        increaseCost(canIncrease.group.get(0));
      }

      this.isDone = true;
    }
    tickDuration();
  }

  public void reduceCost(AbstractCard c) {
    changeCost(c, c.costForTurn - 1);
  }

  public void increaseCost(AbstractCard c) {
    changeCost(c, c.costForTurn + 1);
  }

  public void changeCost(AbstractCard c, int newCost) {
    c.setCostForTurn(newCost);
    c.superFlash();
    c.applyPowers();
  }
}
