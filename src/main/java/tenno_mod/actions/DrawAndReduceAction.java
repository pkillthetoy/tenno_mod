
package tenno_mod.actions;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DrawAndReduceAction extends com.megacrit.cardcrawl.actions.AbstractGameAction {
  private AbstractCard.CardType typeToReduce;
  private AbstractPlayer p;

  public DrawAndReduceAction(AbstractPlayer p, AbstractCard.CardType condition) {
    this.p = p;
    this.duration = 0.0F;
    this.actionType = com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType.DRAW;
    this.typeToReduce = condition;
  }

  public void update() {
    if (AbstractDungeon.player.drawPile.isEmpty()) {
      this.isDone = true;
      return;
    }
    if (AbstractDungeon.player.hand.size() == 10) {
      AbstractDungeon.player.createHandIsFullDialog();
      this.isDone = true;
    }

    AbstractCard card = AbstractDungeon.player.drawPile.getTopCard();
    AbstractDungeon.actionManager.addToTop(new DrawCardAction(p, 1));
    if (card.type == typeToReduce) {
      AbstractDungeon.actionManager.addToTop(new ReduceThisCardAction(card, false));
    }

    this.isDone = true;
  }
}

