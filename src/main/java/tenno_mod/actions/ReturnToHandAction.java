package tenno_mod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ReturnToHandAction extends AbstractGameAction {

  private AbstractCard itself;
  private int where;

  public ReturnToHandAction(AbstractCard itself) {
    this.itself = itself;
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
        AbstractDungeon.player.hand.addToTop(this.itself);
      }
    } else if (this.where == 1) {
      if (AbstractDungeon.player.hand.size() == 10) {
        AbstractDungeon.player.drawPile.moveToDiscardPile(this.itself);
        AbstractDungeon.player.createHandIsFullDialog();
      } else {
        AbstractDungeon.player.drawPile.removeCard(this.itself);
        AbstractDungeon.player.hand.addToTop(this.itself);
      }
    } else if (this.where == 2) {
      if (AbstractDungeon.player.hand.size() == 10) {
        AbstractDungeon.player.drawPile.moveToDiscardPile(this.itself);
        AbstractDungeon.player.createHandIsFullDialog();
      } else {
        AbstractDungeon.player.exhaustPile.removeCard(this.itself);
        this.itself.unfadeOut();
        AbstractDungeon.player.hand.addToTop(this.itself);
      }
    }
    AbstractDungeon.player.hand.refreshHandLayout();
    AbstractDungeon.player.hand.applyPowers();
    AbstractDungeon.player.hand.glowCheck();
    this.isDone = true;
  }
}
