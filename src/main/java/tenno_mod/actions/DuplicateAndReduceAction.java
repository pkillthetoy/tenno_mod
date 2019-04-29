
package tenno_mod.actions;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DuplicateAndReduceAction extends com.megacrit.cardcrawl.actions.AbstractGameAction {
  private AbstractPlayer p;
  private static final String TEXT = "Duplicate";
  private final boolean reduceToZero;

  public DuplicateAndReduceAction(AbstractPlayer p, boolean reduceToZero) {
    this.p = p;
    this.duration = Settings.ACTION_DUR_FAST;
    this.actionType = ActionType.DRAW;
    this.reduceToZero = reduceToZero;
  }

  public void update() {

    if (this.duration == com.megacrit.cardcrawl.core.Settings.ACTION_DUR_FAST) {
      if (this.p.hand.group.size() == 0) {
        return;
      }


      if (this.p.hand.group.size() > 1) {
        AbstractDungeon.handCardSelectScreen.open(TEXT, 1, false, false, false, false);
        tickDuration();
        return;
      }
      if (this.p.hand.group.size() == 1) {
        dupeAndReduceCard(this.p.hand
            .getTopCard().makeStatEquivalentCopy());
        returnCards();
        this.isDone = true;
      }
    }


    if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
      for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
        AbstractDungeon.actionManager.addToTop(new MakeTempCardInHandAction(c.makeStatEquivalentCopy()));
        dupeAndReduceCard(c);

      }

      returnCards();

      AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
      AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
      this.isDone = true;
    }

    tickDuration();
  }

  private void returnCards() {
    this.p.hand.refreshHandLayout();
  }

  private void dupeAndReduceCard(AbstractCard c) {
    AbstractCard newCard = c.makeStatEquivalentCopy();
    if (newCard.cost > 0 && newCard.costForTurn > 0 && !newCard.freeToPlayOnce) {
      if (reduceToZero) {
        newCard.setCostForTurn(0);
      } else {
        newCard.setCostForTurn(newCard.costForTurn - 1);
      }
    }
    AbstractDungeon.actionManager.addToTop(new MakeTempCardInHandAction(newCard));
  }
}

