package tenno_mod.shared;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SkillUtils {
  public static int countSkills() {
    int count = 0;
    for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
      if (c.type == AbstractCard.CardType.SKILL) {
        count++;
      }
    }
    return count;
  }
}
