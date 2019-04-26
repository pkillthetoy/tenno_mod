package tenno_mod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import tenno_mod.cards.generated.UmbralFiber_TENNO;
import tenno_mod.cards.generated.UmbralHowl_TENNO;
import tenno_mod.cards.generated.UmbralIntensify_TENNO;

public class LinkedNartaPower_TENNO extends AbstractPower {
  public static final String POWER_ID = "LinkedNartaPower_TENNO";
  private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(
      POWER_ID);
  public static final String NAME = powerStrings.NAME;
  public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


  public LinkedNartaPower_TENNO(AbstractCreature owner, int amount) {
    this.ID = POWER_ID;
    this.name = NAME;
    this.owner = owner;
    this.amount = amount;
    updateDescription();
    this.type = PowerType.BUFF;
    this.img = new Texture("img/powers/uparrow.png");
  }

  public void updateDescription() {
    if (this.amount == 1) {
      this.description = DESCRIPTIONS[0];
    } else {
      this.description = (DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2]);
    }
  }

  public void onUseCard(AbstractCard card, UseCardAction action) {
    AbstractDungeon.actionManager.addToBottom(new DrawCardAction(this.owner, 1));
  }
}
