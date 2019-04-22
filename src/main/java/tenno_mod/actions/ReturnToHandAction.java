package tenno_mod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import tenno_mod.TennoMod;

public class ReturnToHandAction extends AbstractGameAction {

  private AbstractCard itself;
  private int where;

  public ReturnToHandAction(AbstractCard itself) {
    this.itself = itself;
    this.duration = Settings.ACTION_DUR_XFAST;
    this.actionType = ActionType.DRAW;
  }

  // Thank you servant mod for this chunk of code.
  public void update() {
    for (AbstractCard c : AbstractDungeon.player.discardPile.group)
      if (c == this.itself)
        this.where = 0;
    for (AbstractCard c : AbstractDungeon.player.drawPile.group)
      if (c == this.itself)
        this.where = 1;
    for (AbstractCard c : AbstractDungeon.player.exhaustPile.group)
      if (c == this.itself)
        this.where = 2;
    for (AbstractCard c : AbstractDungeon.player.hand.group)
      if (c == this.itself)
        this.where = 3;

    if (this.where == 0) {
      if (AbstractDungeon.player.hand.size() == 10) {
        AbstractDungeon.player.createHandIsFullDialog();
      } else {
        AbstractDungeon.player.discardPile.removeCard(this.itself);
        // A hack to get around a quirk in card movement.
        // This would be hand.addToTop, but the game gets confused about where it is.
        // It knows it's in hand, but the card doesn't display properly until you
        // hover and unhover over a card or do anything else that forces an update on cards.
        // I can't figure out how to force that update from within this action.
        // So instead we're adding a copy of the card. This should behave identically
        // in all gameplay respects.
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(this.itself));
      }
    } else if (this.where == 1) {
      if (AbstractDungeon.player.hand.size() == 10) {
        AbstractDungeon.player.drawPile.moveToDiscardPile(this.itself);
        AbstractDungeon.player.createHandIsFullDialog();
      } else {
        AbstractDungeon.player.drawPile.removeCard(this.itself);
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(this.itself));
      }
    } else if (this.where == 2) {
      if (AbstractDungeon.player.hand.size() == 10) {
        AbstractDungeon.player.drawPile.moveToDiscardPile(this.itself);
        AbstractDungeon.player.createHandIsFullDialog();
      } else {
        AbstractDungeon.player.exhaustPile.removeCard(this.itself);
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(this.itself));
      }
    }
    AbstractDungeon.player.hand.refreshHandLayout();
    AbstractDungeon.player.hand.applyPowers();
    AbstractDungeon.player.hand.glowCheck();
    this.isDone = true;
  }
}
