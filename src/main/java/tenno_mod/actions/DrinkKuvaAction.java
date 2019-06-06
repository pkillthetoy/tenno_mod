package tenno_mod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class DrinkKuvaAction extends AbstractGameAction {
  private boolean freeToPlayOnce;
  private AbstractPlayer p;

  public DrinkKuvaAction(AbstractCreature source, int amount, AbstractPlayer p, boolean freeToPlayOnce) {
    setValues(this.target, source, amount);
    this.p = p;
    this.freeToPlayOnce = freeToPlayOnce;
    this.actionType = com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType.WAIT;
  }

  public void update() {

    AbstractDungeon.actionManager.addToTop(
        new MakeTempCardInHandAction(new VoidCard(), amount));


    if (!this.freeToPlayOnce) {
      this.p.energy.use(EnergyPanel.totalCount);
    }
    this.isDone = true;
  }
}
