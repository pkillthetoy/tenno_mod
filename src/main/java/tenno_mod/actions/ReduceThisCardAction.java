package tenno_mod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class ReduceThisCardAction extends AbstractGameAction {

  private AbstractCard card;
  private boolean isForThisCombat;

  public ReduceThisCardAction(AbstractCard card, boolean isForThisCombat) {
    this.duration = 0.0F;
    this.isForThisCombat = isForThisCombat;
    this.actionType = com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType.WAIT;
    this.card = card;
  }

  public void update() {
    if (isForThisCombat) {
      card.modifyCostForCombat(-1);
    } else if (card.costForTurn != 0) {
      card.setCostForTurn(card.costForTurn - 1);
    }
    card.applyPowers();

    this.isDone = true;
  }
}
