package tenno_mod.cards.common;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import tenno_mod.actions.ExhaustVoidFromDiscardAction;
import tenno_mod.actions.ExhaustVoidFromDrawAction;
import tenno_mod.actions.ExhaustVoidFromDrawOrDiscardAction;
import tenno_mod.actions.ReduceRandomAttackInHandAction;
import tenno_mod.patches.AbstractCardEnum;

public class AgileGuard_TENNO extends CustomCard {
  public static final String ID = "AgileGuard_TENNO";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
  public static final String IMG_PATH = "img/cards/Beta.png";
  private static final int COST = 2;
  private static final int BLOCK_AMT = 9;
  private static final int UPGRADE_PLUS_BLOCK = 3;

  public AgileGuard_TENNO() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        CardType.SKILL,
        AbstractCardEnum.TENNO_COLOR,
        CardRarity.COMMON,
        CardTarget.SELF
    );
    this.baseBlock = BLOCK_AMT;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractDungeon.actionManager.addToBottom(
        new GainBlockAction(p, p, this.block)
    );  AbstractDungeon.actionManager.addToBottom(
        new ReduceRandomAttackInHandAction()
    );
  }

  public AbstractCard makeCopy() {
    return new AgileGuard_TENNO();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeBlock(UPGRADE_PLUS_BLOCK);
    }
  }
}
