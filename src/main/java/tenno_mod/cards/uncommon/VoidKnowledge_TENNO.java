package tenno_mod.cards.uncommon;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import tenno_mod.patches.AbstractCardEnum;

public class VoidKnowledge_TENNO extends CustomCard {
  public static final String ID = "VoidKnowledge_TENNO";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String IMG_PATH = "img/cards/Skill.png";
  private static final int COST = 0;
  private static final int MAGIC_NUMBER = 3;
  private static final int UPG_MAGIC_NUMBER = 1;

  public VoidKnowledge_TENNO() {
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
    this.magicNumber = this.baseMagicNumber = MAGIC_NUMBER;
    this.exhaust = true;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractDungeon.actionManager.addToBottom(
        new DrawCardAction(p, this.magicNumber)
    );
    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(new VoidCard(), 1));
  }

  public AbstractCard makeCopy() {
    return new VoidKnowledge_TENNO();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeMagicNumber(UPG_MAGIC_NUMBER);
    }
  }
}
