package tenno_mod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class HunterAdrenalinePower_TENNO extends AbstractPower {
  public static final String POWER_ID = "HunterAdrenalinePower_TENNO";
  private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
  public static final String NAME = powerStrings.NAME;
  public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

  public HunterAdrenalinePower_TENNO(AbstractCreature owner, int lightningAmount) {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    this.amount = lightningAmount;
    updateDescription();
    this.type = PowerType.BUFF;
    this.img = new Texture("img/powers/uparrow.png");
  }

  public int onAttacked(DamageInfo info, int damageAmount) {
    if ((info.type != DamageInfo.DamageType.THORNS) && (info.type != DamageInfo.DamageType.HP_LOSS) && (info.owner != null) && (info.owner != this.owner) && (damageAmount > 0) &&
        (!this.owner.hasPower("Buffer"))) {
      flash();
      for (int i = 0; i < this.amount; i++) {
        AbstractDungeon.actionManager.addToBottom(
            new ApplyPowerAction(
                owner,
                owner,
                new HunterAdrenalineChargePower_TENNO(owner, 1), 1));
      }
    }
    return damageAmount;
  }

  public void updateDescription() {
    if (this.amount == 1) {
      this.description = DESCRIPTIONS[0];
    } else {
      this.description = (DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2]);
    }
  }
}

