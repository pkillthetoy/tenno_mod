package tenno_mod.cards.uncommon;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import tenno_mod.patches.AbstractCardEnum;

public class PlanAhead_TENNO extends CustomCard {
  public static final String ID = "PlanAhead_TENNO";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
  public static final String IMG_PATH = "img/cards/Skill.png";
  private static final int COST = 1;
  private static final int BLOCK_AMT = 8;
  private static final int UPG_BLOCK_AMT = 3;
  private static final int MAGIC_NUMBER = 1;
  private static final int UPG_MAGIC_NUMBER = 1;

  public PlanAhead_TENNO() {
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
    this.magicNumber = this.baseMagicNumber = MAGIC_NUMBER;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractDungeon.actionManager.addToBottom(
        new GainBlockAction(p, p, this.block)
    );
    AbstractDungeon.actionManager.addToBottom(
        new com.megacrit.cardcrawl.actions.common.DrawCardAction(p, this.magicNumber));
    AbstractDungeon.actionManager.addToBottom(
        new com.megacrit.cardcrawl.actions.common.PutOnDeckAction(p, p, 1, false));
  }

  public AbstractCard makeCopy() {
    return new PlanAhead_TENNO();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeBlock(UPG_BLOCK_AMT);
      upgradeMagicNumber(UPG_MAGIC_NUMBER);
      this.rawDescription = DESCRIPTION_UPG;
      initializeDescription();
    }
  }
}
