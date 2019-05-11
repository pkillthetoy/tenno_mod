
package tenno_mod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ConcealedWeaponBlockAction extends com.megacrit.cardcrawl.actions.AbstractGameAction {
  private DamageInfo info;
  private AbstractPlayer p;
  private int amount;

  public ConcealedWeaponBlockAction(AbstractPlayer player, int amount) {
    p = player;
    this.amount = amount;
    this.duration = com.megacrit.cardcrawl.core.Settings.ACTION_DUR_XFAST;
    this.actionType = AbstractGameAction.ActionType.BLOCK;
  }

  public void update() {
    for (AbstractCard c : AbstractDungeon.player.hand.group) {
      if (c.type == AbstractCard.CardType.ATTACK) {
        AbstractDungeon.actionManager.addToTop(new GainBlockAction(p, p, amount));
      }
    }

    this.isDone = true;
  }
}
