package tenno_mod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.List;

public class DrinkKuvaAction extends AbstractGameAction {
  public DrinkKuvaAction(AbstractCreature source, int amount) {
    setValues(this.target, source, amount);
    this.actionType = com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType.WAIT;
  }

  public void update() {
    int toDraw = this.amount - AbstractDungeon.player.hand.size();
    if (toDraw > 0) {
      AbstractDungeon.actionManager.addToTop(
          new MakeTempCardInHandAction(new VoidCard(), toDraw));
    }
    this.isDone = true;
  }
}
