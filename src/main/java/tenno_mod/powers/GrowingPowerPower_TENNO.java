package tenno_mod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class GrowingPowerPower_TENNO extends AbstractPower {
  public static final String POWER_ID = "GrowingPowerPower_TENNO";
  private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(
      POWER_ID);
  public static final String NAME = powerStrings.NAME;
  public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


  public GrowingPowerPower_TENNO(AbstractCreature owner, int amount) {
    this.ID = POWER_ID;
    this.name = NAME;
    this.owner = owner;
    this.amount = amount;
    updateDescription();
    this.type = PowerType.BUFF;
    this.img = new Texture("img/powers/growingPower.png");
  }

  public void updateDescription() {
    this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);

  }

  public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
    if (power.type == AbstractPower.PowerType.DEBUFF && !power.ID.equals(
        "Shackled") && source == this.owner && target != this.owner &&
        !target.hasPower("Artifact")) {
      flash();
      AbstractDungeon.actionManager.addToBottom(
          new ApplyPowerAction(
              source,
              source,
              new StrengthPower(source, this.amount), this.amount));
    }
  }
}
