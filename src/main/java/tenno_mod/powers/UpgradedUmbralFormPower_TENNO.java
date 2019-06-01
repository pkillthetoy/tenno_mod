package tenno_mod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import tenno_mod.cards.generated.UmbralFiber_TENNO;
import tenno_mod.cards.generated.UmbralHowl_TENNO;
import tenno_mod.cards.generated.UmbralIntensify_TENNO;

public class UpgradedUmbralFormPower_TENNO extends AbstractPower {
  public static final String POWER_ID = "UpgradedUmbralFormPower_TENNO";
  private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(
      POWER_ID);
  public static final String NAME = powerStrings.NAME;
  public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


  public UpgradedUmbralFormPower_TENNO(AbstractCreature owner, int amount) {
    this.ID = POWER_ID;
    this.name = NAME;
    this.owner = owner;
    this.amount = amount;
    updateDescription();
    this.type = PowerType.BUFF;
    this.img = new Texture("img/powers/umbralForm.png");
  }

  public void updateDescription() {
    if (this.amount == 1) {
      this.description = DESCRIPTIONS[0];
    } else {
      this.description = (DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2]);
    }
  }

  public void atStartOfTurn() {
    flash();
    AbstractCard c;
    for (int i = 0; i < this.amount; i++) {
      int randomNum = AbstractDungeon.miscRng.random(2);
      switch (randomNum) {
        case 0:
          c = new UmbralHowl_TENNO().makeCopy();
          break;
        case 1:
          c = new UmbralIntensify_TENNO().makeCopy();
          break;
        case 2:
          c = new UmbralFiber_TENNO().makeCopy();
          break;
        default:
          // Just so we know we always assign something.
          c = new UmbralHowl_TENNO().makeCopy();
      }
      c.upgrade();
      AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c));
    }
  }
}
