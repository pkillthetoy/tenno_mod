package tenno_mod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ParkourPower_TENNO extends AbstractPower {
  public static final String POWER_ID = "ParkourPower_TENNO";
  private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
  public static final String NAME = powerStrings.NAME;
  public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

  public ParkourPower_TENNO(AbstractCreature owner, int amount) {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    this.amount = amount;
    updateDescription();
    this.type = PowerType.BUFF;
    this.img = new Texture("img/powers/sprinter.png");
  }

  public void onAfterCardPlayed(AbstractCard c) {
    if (c.freeToPlayOnce || c.costForTurn == 0) {
      AbstractDungeon.actionManager.addToTop(new DrawCardAction(owner, 1));
    }
  }

  public void updateDescription() {
    if (this.amount == 1) {
      this.description = DESCRIPTIONS[0];
    } else {
      this.description = (DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2]);
    }
  }
}

