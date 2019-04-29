package tenno_mod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import tenno_mod.TennoMod;

public class ChromaticBladePower_TENNO extends AbstractPower {
  public static final String POWER_ID = "ChromaticBladePower_TENNO";
  private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(
      POWER_ID);
  public static final String NAME = powerStrings.NAME;
  public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


  public ChromaticBladePower_TENNO(AbstractCreature owner, int amount) {
    this.ID = POWER_ID;
    this.name = NAME;
    this.owner = owner;
    this.amount = amount;
    updateDescription();
    this.type = PowerType.BUFF;
    this.img = new Texture("img/powers/uparrow.png");
  }

  public void updateDescription() {
    this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);

  }

  public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
    if (damageAmount > 0 && target != this.owner && info.type == DamageInfo.DamageType.NORMAL) {
      flash();

      int randomNum = AbstractDungeon.miscRng.random(2);
      TennoMod.logger.info(randomNum);
      switch (randomNum) {
        case 0:
          AbstractDungeon.actionManager.addToBottom(
              new ApplyPowerAction(
                  target,
                  owner,
                  new WeakPower(target, this.amount, false),
                  this.amount,
                  true));
          break;
        case 1:
          AbstractDungeon.actionManager.addToBottom(
              new ApplyPowerAction(
                  target,
                  owner,
                  new VulnerablePower(target, this.amount, false),
                  this.amount,
                  true));
          break;
        case 2:
          AbstractDungeon.actionManager.addToBottom(
              new ApplyPowerAction(target, owner, new com.megacrit.cardcrawl.powers.StrengthPower(target, -this.amount),
                  -this.amount, true, AbstractGameAction.AttackEffect.NONE));
          if (!target.hasPower("Artifact")) {
            AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(target, owner,
                    new com.megacrit.cardcrawl.powers.GainStrengthPower(target, this.amount),
                    this.amount, true, AbstractGameAction.AttackEffect.NONE));
          }
          break;
      }

    }
  }
}
