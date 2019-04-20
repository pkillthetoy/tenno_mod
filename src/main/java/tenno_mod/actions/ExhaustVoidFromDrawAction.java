package tenno_mod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ExhaustVoidFromDrawAction extends AbstractGameAction {
  public ExhaustVoidFromDrawAction() {
    this.actionType = ActionType.EXHAUST;
    this.duration = Settings.ACTION_DUR_FAST;
  }

  @Override
  public void update() {
    if (this.duration == Settings.ACTION_DUR_FAST) {
      AbstractPlayer p = AbstractDungeon.player;

      CardGroup voids = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
      for (AbstractCard c : p.drawPile.group) {
        if (c.type == AbstractCard.CardType.STATUS && c.name.equals(VoidCard.NAME)) {
          voids.addToBottom(c);
        }
      }
      if (voids.isEmpty()) {
        this.isDone = true;
        return;
      }
      voids.shuffle();
      AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(voids.getTopCard(), p.drawPile, true));
      this.isDone = true;
    }
    tickDuration();
  }
}
