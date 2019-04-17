package tenno_mod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import tenno_mod.shared.VoidUtils;

// Exhaust all voids and then do damage. Note addToTop.
public class VoidRadianceAction extends AbstractGameAction {
  private DamageInfo info;
  public VoidRadianceAction(DamageInfo info) {
    this.actionType = ActionType.EXHAUST;
    this.duration = Settings.ACTION_DUR_FAST;
    this.info = info;
  }

  @Override
  public void update() {
    if (this.duration == Settings.ACTION_DUR_FAST) {
      int count = VoidUtils.countVoids();
      if (count > 0) {
        AbstractDungeon.actionManager.addToTop(new VoidRadianceDamageAction(
            AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng),
            info,
            count));
      }
      AbstractPlayer p = AbstractDungeon.player;
      for (AbstractCard c : p.hand.group) {
        if (isVoid(c)) {
          AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(c, p.hand, true));
        }
      }
      for (AbstractCard c : p.drawPile.group) {
        if (isVoid(c)) {
          AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(c, p.drawPile, true));
        }
      }
      for (AbstractCard c : p.discardPile.group) {
        if (isVoid(c)) {
          AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(c, p.discardPile, true));
        }
      }
      this.isDone = true;
    }
    tickDuration();
  }

  public boolean isVoid(AbstractCard c) {
    return c.type == AbstractCard.CardType.STATUS && c.name.equals(VoidCard.NAME);
  }
}
