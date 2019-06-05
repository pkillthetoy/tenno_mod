package tenno_mod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class TennoMasterySkillPower_TENNO extends AbstractPower {
  public static final String POWER_ID = "TennoMasterySkillPower_TENNO";
  private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
  public static final String NAME = powerStrings.NAME;
  public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

  private int statGain;

  public TennoMasterySkillPower_TENNO(AbstractCreature owner, int amount) {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    this.amount = 3;
    this.statGain = amount;
    updateDescription();
    this.type = PowerType.BUFF;
    this.img = new Texture("img/powers/tennoMasterySkill.png");
  }

  public void stackPower(int stackAmount) {
    this.fontScale = 8.0F;
    this.statGain += stackAmount;
    updateDescription();
  }

  public void atStartOfTurn() {
    this.amount = 3;
  }

  public void onAfterCardPlayed(AbstractCard c) {
    if (c.type == AbstractCard.CardType.SKILL) {
      this.amount--;
      if (amount == 0) {
        flash();
        this.amount = 3;
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(
            owner,
            owner,
            new StrengthPower(owner, this.amount), this.statGain));
      }
    }
  }


  public void updateDescription() {
    if (this.amount == 1) {
      this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.statGain + DESCRIPTIONS[2]);
    } else {
      this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[3] + this.statGain + DESCRIPTIONS[2]);
    }
  }
}

