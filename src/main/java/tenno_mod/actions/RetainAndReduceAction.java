
package tenno_mod.actions;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import tenno_mod.relics.MasteryBadge_TENNO;


public class RetainAndReduceAction extends com.megacrit.cardcrawl.actions.AbstractGameAction {
  private static final UIStrings uiStrings = com.megacrit.cardcrawl.core.CardCrawlGame.languagePack.getUIString(
      "RetainAndReduceAction");
  public static final String TEXT = "Retain and Reduce";
  private MasteryBadge_TENNO relicSource;

  public RetainAndReduceAction(AbstractCreature source, int amount, MasteryBadge_TENNO relicSource) {
    setValues(AbstractDungeon.player, source, amount);
    this.relicSource = relicSource;
    this.actionType = com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType.CARD_MANIPULATION;
  }


  public void update() {
    if (this.duration == 0.5F) {
      AbstractDungeon.handCardSelectScreen.open(TEXT, this.amount, false, true, false, false, true);
      AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.utility.WaitAction(0.25F));
      tickDuration();
      return;
    }


    if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
      for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
        if (!c.isEthereal) {
          c.retain = true;
          relicSource.addCardToReduce(c);
        }
        AbstractDungeon.player.hand.addToTop(c);
      }
      AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
    }

    tickDuration();
  }
}