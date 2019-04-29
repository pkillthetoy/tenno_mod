package tenno_mod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ReturnToHandAction extends AbstractGameAction {

  private AbstractCard itself;
  private int where;
  private AbstractPlayer p;

  public ReturnToHandAction(AbstractCard itself, AbstractPlayer p) {
    this.itself = itself;
    this.duration = Settings.ACTION_DUR_XFAST;
    this.actionType = ActionType.DRAW;
    this.p = p;
  }

  // Thank you servant mod for this chunk of code.
  public void update() {
    for (AbstractCard c : p.discardPile.group)
      if (c == this.itself)
        this.where = 0;
    for (AbstractCard c : p.drawPile.group)
      if (c == this.itself)
        this.where = 1;
    for (AbstractCard c : p.exhaustPile.group)
      if (c == this.itself)
        this.where = 2;
    for (AbstractCard c : p.hand.group)
      if (c == this.itself)
        this.where = 3;

    if (this.where == 0) {
      if (p.hand.size() == 10) {
        p.createHandIsFullDialog();
      } else {
        p.discardPile.removeCard(this.itself);
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
      if (p.hand.size() == 10) {
        p.drawPile.moveToDiscardPile(this.itself);
        p.createHandIsFullDialog();
      } else {
        p.drawPile.removeCard(this.itself);
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(this.itself));
      }
    } else if (this.where == 2) {
      if (p.hand.size() == 10) {
        p.drawPile.moveToDiscardPile(this.itself);
        p.createHandIsFullDialog();
      } else {
        p.exhaustPile.removeCard(this.itself);
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(this.itself));
      }
    }
    p.hand.refreshHandLayout();
    p.hand.applyPowers();
    p.hand.glowCheck();
    this.isDone = true;
  }
}
