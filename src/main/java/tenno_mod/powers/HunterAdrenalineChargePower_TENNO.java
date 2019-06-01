package tenno_mod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import tenno_mod.actions.ReduceRandomCardInHandAction;

public class HunterAdrenalineChargePower_TENNO extends AbstractPower {
  public static final String POWER_ID = "HunterAdrenalineChargePower_TENNO";
  private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
  public static final String NAME = powerStrings.NAME;
  public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

  public HunterAdrenalineChargePower_TENNO(AbstractCreature owner, int amount) {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    this.amount = amount;
    updateDescription();
    this.type = PowerType.BUFF;
    this.img = new Texture("img/powers/hunterAdrenalineCharge.png");
  }

  public void atStartOfTurnPostDraw() {
    for (int i = 0; i < amount; i++) {
      AbstractDungeon.actionManager.addToBottom(new ReduceRandomCardInHandAction());
    }
    AbstractDungeon.actionManager.addToBottom(
        new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
  }

  public void updateDescription() {
    if (this.amount == 1) {
      this.description = DESCRIPTIONS[0];
    } else {
      this.description = (DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2]);
    }
  }
}

