package tenno_mod.cards.uncommon;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DoubleTapPower;
import tenno_mod.patches.AbstractCardEnum;

public class TapDodge_TENNO extends CustomCard {
  public static final String ID = "TapDodge_TENNO";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String IMG_PATH = "img/cards/TapDodge.png";
  private static final int COST = 1;
  private static final int BLOCK_AMT = 4;
  private static final int UPG_BLOCK_AMT = 2;

  public TapDodge_TENNO() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        CardType.SKILL,
        AbstractCardEnum.TENNO_COLOR,
        CardRarity.UNCOMMON,
        CardTarget.SELF
    );
    this.baseBlock = BLOCK_AMT;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractDungeon.actionManager.addToBottom(
        new GainBlockAction(p, p, this.block)
    );
    AbstractDungeon.actionManager.addToBottom(
        new ApplyPowerAction(p, p, new DoubleTapPower(p, 1), 1));
  }

  public AbstractCard makeCopy() {
    return new TapDodge_TENNO();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeBlock(UPG_BLOCK_AMT);
    }
  }
}
