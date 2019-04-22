package tenno_mod.shared;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class VoidUtils {
  public static int countVoids() {
    int count = 0;
    AbstractPlayer p = AbstractDungeon.player;

    for (AbstractCard c : p.hand.group) {
      if (isVoid(c)) {
        count++;
      }
    }
    for (AbstractCard c : p.drawPile.group) {
      if (isVoid(c)) {
        count++;
      }
    }
    for (AbstractCard c : p.discardPile.group) {
      if (isVoid(c)) {
        count++;
      }
    }
    for (AbstractCard c : p.exhaustPile.group) {
      if (isVoid(c)) {
        count++;
      }
    }
    return count;
  }


  public static int countVoidsInDrawAndDiscard() {
    int count = 0;
    AbstractPlayer p = AbstractDungeon.player;
    for (AbstractCard c : p.drawPile.group) {
      if (isVoid(c)) {
        count++;
      }
    }
    for (AbstractCard c : p.discardPile.group) {
      if (isVoid(c)) {
        count++;
      }
    }
    return count;
  }
  public static boolean isVoid(AbstractCard c) {
    return c.type == AbstractCard.CardType.STATUS && c.name.equals(VoidCard.NAME);
  }
}
