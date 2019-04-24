package tenno_mod.cards.generated;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;
import tenno_mod.patches.AbstractCardEnum;

public class UmbralHowl_TENNO extends CustomCard {
  public static final String ID = "UmbralHowl_TENNO";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String IMG_PATH = "img/cards/Skill.png";
  private static final int COST = 1;
  private static final int MAGIC_NUMBER = 6;
  private static final int UPG_MAGIC_NUMBER = 2;

  public UmbralHowl_TENNO() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        CardType.SKILL,
        AbstractCardEnum.TENNO_GENERATED,
        CardRarity.SPECIAL,
        CardTarget.ALL_ENEMY
    );
    this.magicNumber = this.baseMagicNumber = MAGIC_NUMBER;
    this.exhaust = true;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractDungeon.actionManager.addToBottom(
        new com.megacrit.cardcrawl.actions.utility.SFXAction("ATTACK_PIERCING_WAIL"));
    if (Settings.FAST_MODE) {
      AbstractDungeon.actionManager.addToBottom(new VFXAction(p,
          new ShockWaveEffect(p.hb.cX, p.hb.cY, Settings.GREEN_TEXT_COLOR, ShockWaveEffect.ShockWaveType.CHAOTIC),
          0.3F));

    } else {
      AbstractDungeon.actionManager.addToBottom(new VFXAction(p,
          new ShockWaveEffect(p.hb.cX, p.hb.cY, Settings.GREEN_TEXT_COLOR, ShockWaveEffect.ShockWaveType.CHAOTIC),
          1.5F));
    }


    for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
      AbstractDungeon.actionManager.addToBottom(
          new ApplyPowerAction(mo, p, new com.megacrit.cardcrawl.powers.StrengthPower(mo, -this.magicNumber),
              -this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
    }
    for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
      if (!mo.hasPower("Artifact")) {
        AbstractDungeon.actionManager.addToBottom(
            new ApplyPowerAction(mo, p, new com.megacrit.cardcrawl.powers.GainStrengthPower(mo, this.magicNumber),
                this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
      }
    }

    for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
      AbstractDungeon.actionManager.addToBottom(
          new ApplyPowerAction(mo, p, new com.megacrit.cardcrawl.powers.WeakPower(mo, 2, false), 2, true,
              AbstractGameAction.AttackEffect.NONE));


      AbstractDungeon.actionManager.addToBottom(
          new ApplyPowerAction(mo, p, new com.megacrit.cardcrawl.powers.VulnerablePower(mo, 2, false), 2, true,
              AbstractGameAction.AttackEffect.NONE));
    }
  }

  public AbstractCard makeCopy() {
    return new UmbralHowl_TENNO();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeMagicNumber(UPG_MAGIC_NUMBER);
    }
  }
}
