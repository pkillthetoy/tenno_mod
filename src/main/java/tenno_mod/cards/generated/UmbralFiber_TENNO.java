package tenno_mod.cards.generated;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import tenno_mod.patches.AbstractCardEnum;

public class UmbralFiber_TENNO extends CustomCard {
  public static final String ID = "UmbralFiber_TENNO";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String IMG_PATH = "img/cards/Beta.png";
  private static final int COST = 1;
  private static final int BLOCK_AMT = 9;
  private static final int MAGIC_NUMBER = 3;
  private static final int UPG_BLOCK_AMT = 3;
  private static final int UPG_MAGIC_NUMBER = 1;

  public UmbralFiber_TENNO() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        CardType.POWER,
        AbstractCardEnum.TENNO_GENERATED,
        CardRarity.SPECIAL,
        CardTarget.SELF
    );
    this.magicNumber = this.baseMagicNumber = MAGIC_NUMBER;
    this.baseBlock = BLOCK_AMT;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractDungeon.actionManager.addToBottom(
        new GainBlockAction(p, p, this.block)
    );
    AbstractDungeon.actionManager.addToBottom(
        new ApplyPowerAction(
            p,
            p,
            new MetallicizePower(p, this.magicNumber), this.magicNumber));
  }

  public AbstractCard makeCopy() {
    return new UmbralFiber_TENNO();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeBlock(UPG_BLOCK_AMT);
      upgradeMagicNumber(UPG_MAGIC_NUMBER);
    }
  }
}
