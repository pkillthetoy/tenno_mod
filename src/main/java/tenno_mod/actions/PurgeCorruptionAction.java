package tenno_mod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class PurgeCorruptionAction extends AbstractGameAction {
  private int blockAmount;

  public PurgeCorruptionAction(int blockAmount) {
    this.actionType = ActionType.EXHAUST;
    this.duration = Settings.ACTION_DUR_FAST;
    this.blockAmount = blockAmount;
  }

  @Override
  public void update() {
    int count = 0;
    AbstractPlayer p = AbstractDungeon.player;
    for (AbstractCard c : p.hand.group) {
      if (isVoid(c)) {
        AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(c, p.hand, true));
        count++;
      }
    }
    for (AbstractCard c : p.drawPile.group) {
      if (isVoid(c)) {
        AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(c, p.drawPile, true));
        count++;
      }
    }
    for (AbstractCard c : p.discardPile.group) {
      if (isVoid(c)) {
        AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(c, p.discardPile, true));
        count++;
      }
    }
    AbstractDungeon.actionManager.addToTop(new GainBlockAction(p, p, blockAmount * count));
    this.isDone = true;
  }

  public boolean isVoid(AbstractCard c) {
    return c.type == AbstractCard.CardType.STATUS && c.name.equals(VoidCard.NAME);
  }
}
