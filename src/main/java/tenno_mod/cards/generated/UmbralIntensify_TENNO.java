package tenno_mod.cards.generated;

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
import tenno_mod.powers.IntensifyPower_TENNO;

public class UmbralIntensify_TENNO extends CustomCard {
  public static final String ID = "UmbralIntensify_TENNO";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String IMG_PATH = "img/cards/Power.png";
  private static final int COST = 1;
  private static final int MAGIC_NUMBER = 2;
  private static final int UPG_MAGIC_NUMBER = 1;

  public UmbralIntensify_TENNO() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        CardType.POWER,
        AbstractCardEnum.TENNO_GENERATED,
        CardRarity.RARE,
        CardTarget.SELF
    );
    this.magicNumber = this.baseMagicNumber = MAGIC_NUMBER;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractPower pow = new IntensifyPower_TENNO(p, 1);
    AbstractDungeon.actionManager.addToBottom(
        new com.megacrit.cardcrawl.actions.common.ApplyPowerAction(p, p, pow,
            this.magicNumber));
    AbstractDungeon.actionManager.addToBottom(
        new ApplyPowerAction(
            p,
            p,
            new StrengthPower(p, this.magicNumber), this.magicNumber));
  }

  public AbstractCard makeCopy() {
    return new UmbralIntensify_TENNO();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeMagicNumber(UPG_MAGIC_NUMBER);
    }
  }
}
