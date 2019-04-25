package tenno_mod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import tenno_mod.TennoMod;

public class DrawAndReduceCardThisCombatAction extends AbstractGameAction {

  private AbstractPlayer p;

  public DrawAndReduceCardThisCombatAction(AbstractPlayer p) {
    this.duration = 0.0F;
    this.p = p;
    this.actionType = ActionType.DRAW;
  }

  public void update() {
    TennoMod.logger.info(p.drawPile.size());
    if (p.drawPile.isEmpty()) {
      this.isDone = true;
      return;
    }

    AbstractCard card = AbstractDungeon.player.drawPile.getTopCard();
    AbstractDungeon.actionManager.addToTop(new DrawCardAction(p, 1));

    AbstractDungeon.actionManager.addToTop(new ReduceThisCardAction(card, true));

    this.isDone = true;
  }
}
