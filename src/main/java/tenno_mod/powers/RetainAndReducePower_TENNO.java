
package tenno_mod.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import tenno_mod.actions.RetainAndReduceAction;
import tenno_mod.relics.MasteryBadge_TENNO;


public class RetainAndReducePower_TENNO extends AbstractPower {
  public static final String POWER_ID = "RetainAndReducePower_TENNO";
  private static final PowerStrings powerStrings = com.megacrit.cardcrawl.core.CardCrawlGame.languagePack.getPowerStrings(
      POWER_ID);
  public static final String NAME = powerStrings.NAME;
  public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
  private MasteryBadge_TENNO relicSource;

  public RetainAndReducePower_TENNO(AbstractCreature owner, int numCards, MasteryBadge_TENNO relicSource) {
    this.name = NAME;
    this.ID = "RetainAndReducePower_TENNO";
    this.owner = owner;
    this.relicSource = relicSource;
    this.amount = numCards;
    updateDescription();
    loadRegion("retain");
  }

  public void updateDescription() {
    if (this.amount == 1) {
      this.description = (DESCRIPTIONS[0]);
    } else {
      this.description = (DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2]);
    }
  }

  public void atEndOfTurn(boolean isPlayer) {
    if ((isPlayer) && (!AbstractDungeon.player.hand.isEmpty())) {
      AbstractDungeon.actionManager.addToBottom(new RetainAndReduceAction(this.owner, this.amount, relicSource));
    }
  }
}
