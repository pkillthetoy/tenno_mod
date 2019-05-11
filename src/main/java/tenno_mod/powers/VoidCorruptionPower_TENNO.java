package tenno_mod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class VoidCorruptionPower_TENNO extends AbstractPower {
  public static final String POWER_ID = "VoidCorruptionPower_TENNO";
  private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
  public static final String NAME = powerStrings.NAME;
  public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

  public VoidCorruptionPower_TENNO(AbstractCreature owner, int amount) {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    this.amount = amount;
    updateDescription();
    this.type = PowerType.BUFF;
    this.img = new Texture("img/powers/uparrow.png");
  }

  public void atStartOfTurn() {
    flash();
    com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(this.amount));
    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(new VoidCard(), this.amount));
  }

  public void updateDescription() {
    if (this.amount == 1) {
      this.description = DESCRIPTIONS[0];
    } else {
      StringBuilder builder = new StringBuilder();
      builder.append(DESCRIPTIONS[1]);
      for (int i = 0; i < this.amount; i++) {
        builder.append("[E] ");
      }
      builder.append(DESCRIPTIONS[2] + this.amount + DESCRIPTIONS[3]);
    }
  }
}

