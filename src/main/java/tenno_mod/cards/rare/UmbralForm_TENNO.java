package tenno_mod.cards.rare;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import tenno_mod.patches.AbstractCardEnum;
import tenno_mod.powers.UmbralFormPower;
import tenno_mod.powers.UpgradedUmbralFormPower;

public class UmbralForm_TENNO extends CustomCard {
  public static final String ID = "UmbralForm_TENNO";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
  public static final String IMG_PATH = "img/cards/Beta.png";
  private static final int COST = 3;

  public UmbralForm_TENNO() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        CardType.POWER,
        AbstractCardEnum.TENNO_COLOR,
        CardRarity.RARE,
        CardTarget.SELF
    );
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractPower power;
    if (this.upgraded) {
      power = new UpgradedUmbralFormPower(p, 1);
    } else {
      power = new UmbralFormPower(p, 1);
    }

    AbstractDungeon.actionManager.addToBottom(
        new ApplyPowerAction(
            p,
            p,
            power, 1));
  }

  public AbstractCard makeCopy() {
    return new UmbralForm_TENNO();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      this.rawDescription = DESCRIPTION_UPG;
      initializeDescription();
    }
  }
}
