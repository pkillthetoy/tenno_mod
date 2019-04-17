package tenno_mod.cards.rare;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import tenno_mod.cards.generated.CuttingPoise_TENNO;
import tenno_mod.cards.generated.EqualLaceration_TENNO;
import tenno_mod.cards.generated.LancingJustice_TENNO;
import tenno_mod.cards.generated.VirtuousSlash_TENNO;
import tenno_mod.patches.AbstractCardEnum;

public class ExaltedBlade_TENNO extends CustomCard {
  public static final String ID = "ExaltedBlade_TENNO";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String DESCRIPTION_UPG = cardStrings.DESCRIPTION;
  public static final String IMG_PATH = "img/cards/Beta.png";
  private static final int COST = 0;

  public ExaltedBlade_TENNO() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        CardType.SKILL,
        AbstractCardEnum.TENNO_COLOR,
        CardRarity.RARE,
        CardTarget.SELF
    );
  }

  public void use(AbstractPlayer p, AbstractMonster m) {

    AbstractCard c = new CuttingPoise_TENNO().makeCopy();
    if (upgraded) {
      c.upgrade();
    }
    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c));
    c = new EqualLaceration_TENNO().makeCopy();
    if (upgraded) {
      c.upgrade();
    }
    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c));
    c = new LancingJustice_TENNO().makeCopy();
    if (upgraded) {
      c.upgrade();
    }
    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c));
    c = new VirtuousSlash_TENNO().makeCopy();
    if (upgraded) {
      c.upgrade();
    }
    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c));

  }

  public AbstractCard makeCopy() {
    return new ExaltedBlade_TENNO();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      this.rawDescription = DESCRIPTION_UPG;
      initializeDescription();
    }
  }
}
