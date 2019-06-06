package tenno_mod.cards.common;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import tenno_mod.patches.AbstractCardEnum;

public class CombatKnowledge_TENNO extends CustomCard {
  public static final String ID = "CombatKnowledge_TENNO";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
  public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
  public static final String IMG_PATH = "img/cards/CombatKnowledge.png";
  private static final int COST = 1;
  private static final int MAGIC_NUMBER = 1;
  private static final int UPG_MAGIC_NUMBER = 1;

  public CombatKnowledge_TENNO() {
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
    this.baseBlock = 0;
    this.magicNumber = this.baseMagicNumber = MAGIC_NUMBER;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractDungeon.actionManager.addToBottom(
        new GainBlockAction(p, p, this.block)
    );
    AbstractDungeon.actionManager.addToBottom(
        new DrawCardAction(p, this.magicNumber));
  }

  public AbstractCard makeCopy() {
    return new CombatKnowledge_TENNO();
  }

  public void applyPowers() {
    AbstractPlayer p = AbstractDungeon.player;
    this.baseBlock = p.hand.size();

    if (this.upgraded) {
      this.baseBlock += 3;
    }
    super.applyPowers();
    if (!this.upgraded) {
      this.rawDescription = DESCRIPTION;
    } else {
      this.rawDescription = UPGRADE_DESCRIPTION;
    }
    this.rawDescription += EXTENDED_DESCRIPTION[0];
    initializeDescription();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeBlock(3);
      upgradeMagicNumber(UPG_MAGIC_NUMBER);
      this.rawDescription = UPGRADE_DESCRIPTION;
      initializeDescription();
    }
  }
}
