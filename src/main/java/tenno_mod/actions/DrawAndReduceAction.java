
package tenno_mod.actions;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 
 public class DrawAndReduceAction extends com.megacrit.cardcrawl.actions.AbstractGameAction
 {
   private AbstractCard.CardType typeToReduce;
   
   public DrawAndReduceAction(AbstractCard.CardType condition)
   {
     this.duration = 0.0F;
     this.actionType = com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType.WAIT;
     this.typeToReduce = condition;
   }
   
   public void update()
   {
     if (AbstractDungeon.player.drawPile.isEmpty()) {
       this.isDone = true;
       return;
     }
     
     AbstractCard card = AbstractDungeon.player.drawPile.getTopCard();
     if (card.type == typeToReduce && card.costForTurn != 0) {
       card.setCostForTurn(card.costForTurn - 1);
       card.applyPowers();
     }
     
     this.isDone = true;
   }
 }

