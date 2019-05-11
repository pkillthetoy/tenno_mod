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

public class NinjaSkillsPower_TENNO extends AbstractPower {
  public static final String POWER_ID = "NinjaSkillsPower_TENNO";
  private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
  public static final String NAME = powerStrings.NAME;
  public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

  private int skillCount = 0;
  private int attackCount = 0;

  public NinjaSkillsPower_TENNO(AbstractCreature owner, int amount) {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    this.amount = amount;
    updateDescription();
    this.type = PowerType.BUFF;
    this.img = new Texture("img/powers/uparrow.png");
  }

  public void atStartOfTurn() {
    skillCount = 0;
    attackCount = 0;

  }

  public void onAfterCardPlayed(AbstractCard c) {
    if (c.type == AbstractCard.CardType.ATTACK) {
      attackCount ++;
    }
    if (c.type == AbstractCard.CardType.SKILL) {
      skillCount ++;
    }
    if (attackCount >= 3) {
      AbstractDungeon.actionManager.addToTop( new ApplyPowerAction(
          owner,
          owner,
          new DexterityPower(owner, this.amount), this.amount));
      attackCount = 0;
    }
    if (skillCount>= 3) {
      AbstractDungeon.actionManager.addToTop( new ApplyPowerAction(
          owner,
          owner,
          new StrengthPower(owner, this.amount), this.amount));
      skillCount = 0;
    }

  }

  public void updateDescription() {
      this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2]);
  }
}

