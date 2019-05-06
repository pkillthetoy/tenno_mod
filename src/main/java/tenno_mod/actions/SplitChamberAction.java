
package tenno_mod.actions;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SplitChamberAction extends com.megacrit.cardcrawl.actions.AbstractGameAction {
  private static final com.megacrit.cardcrawl.localization.UIStrings uiStrings = com.megacrit.cardcrawl.core.CardCrawlGame.languagePack.getUIString(
      "SplitChamberAction");
  private AbstractPlayer p;

  public SplitChamberAction(AbstractCreature source) {
    setValues(AbstractDungeon.player, source, amount);
    this.actionType = com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType.DRAW;
    this.duration = Settings.ACTION_DUR_FAST;
    this.p = AbstractDungeon.player;

  }

  public void update() {
    if (this.duration == Settings.ACTION_DUR_FAST) {
      for (AbstractCard c : this.p.hand.group) {
        if (c.costForTurn < c.cost || c.freeToPlayOnce) {

          AbstractDungeon.actionManager.addToTop(new MakeTempCardInHandAction(c
              .makeStatEquivalentCopy()));
        }
      }
      this.isDone = true;
      return;
    }
    tickDuration();
  }
}