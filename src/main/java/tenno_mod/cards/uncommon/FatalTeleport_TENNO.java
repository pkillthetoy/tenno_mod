package tenno_mod.cards.uncommon;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import tenno_mod.patches.AbstractCardEnum;

public class FatalTeleport_TENNO extends CustomCard {
  public static final String ID = "FatalTeleport_TENNO";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
  public static final String IMG_PATH = "img/cards/Beta.png";
  private static final int COST = 1;
  private static final int MAGIC_NUMBER = 6;
  private static final int UPG_MAGIC_NUMBER = 2;

  public FatalTeleport_TENNO() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        CardType.SKILL,
        AbstractCardEnum.TENNO_COLOR,
        CardRarity.UNCOMMON,
        CardTarget.ENEMY
    );
    this.baseMagicNumber = this.magicNumber = MAGIC_NUMBER;
    this.exhaust = true;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {

    AbstractDungeon.actionManager.addToBottom(
        new ApplyPowerAction(m, p, new com.megacrit.cardcrawl.powers.StrengthPower(m, -this.magicNumber),
            -this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
    if (!m.hasPower("Artifact")) {
      AbstractDungeon.actionManager.addToBottom(
          new ApplyPowerAction(m, p, new com.megacrit.cardcrawl.powers.GainStrengthPower(m, this.magicNumber),
              this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
    }
    int vulnAmount = 2;
    if (this.upgraded) {
      vulnAmount = 3;
    }
    AbstractDungeon.actionManager.addToBottom(
        new ApplyPowerAction(
            m,
            p,
            new VulnerablePower(m, vulnAmount, false),
            vulnAmount,
            true)
    );
  }

  public AbstractCard makeCopy() {
    return new FatalTeleport_TENNO();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeMagicNumber(UPG_MAGIC_NUMBER);
      this.rawDescription = DESCRIPTION_UPG;
      initializeDescription();
    }
  }
}
