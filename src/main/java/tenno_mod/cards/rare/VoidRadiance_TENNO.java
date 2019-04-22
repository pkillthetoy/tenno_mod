package tenno_mod.cards.rare;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import tenno_mod.actions.VoidRadianceAction;
import tenno_mod.patches.AbstractCardEnum;
import tenno_mod.shared.VoidUtils;

public class VoidRadiance_TENNO extends CustomCard {
  public static final String ID = "VoidRadiance_TENNO";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
  public static final String IMG_PATH = "img/cards/Beta.png";
  private static final int COST = 3;
  private static final int DAMAGE = 16;
  private static final int UPG_DMG = 5;

  public VoidRadiance_TENNO() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        CardType.ATTACK,
        AbstractCardEnum.TENNO_COLOR,
        CardRarity.RARE,
        CardTarget.ALL_ENEMY
    );
    this.baseDamage = DAMAGE;
    this.baseMagicNumber = this.magicNumber = 0;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {

    this.magicNumber = this.baseMagicNumber = VoidUtils.countVoids();
    AbstractDungeon.actionManager.addToBottom(
        new VoidRadianceAction(new DamageInfo(p, this.baseDamage, this.damageTypeForTurn)));
  }

  public void applyPowers() {
    super.applyPowers();
    this.baseMagicNumber = 0;
    this.magicNumber = 0;

    this.baseMagicNumber = VoidUtils.countVoids();
    if (this.baseMagicNumber > 0) {
      this.rawDescription = (DESCRIPTION + EXTENDED_DESCRIPTION[0]);
      initializeDescription();
    }
  }

  public void onMoveToDiscard() {
    this.rawDescription = DESCRIPTION;
    initializeDescription();
  }

  public void calculateCardDamage(AbstractMonster mo) {
    super.calculateCardDamage(mo);
    if (this.baseMagicNumber > 0) {
      this.rawDescription = (DESCRIPTION + EXTENDED_DESCRIPTION[0]);
    }
    initializeDescription();
  }

  public AbstractCard makeCopy() {
    return new VoidRadiance_TENNO();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeDamage(UPG_DMG);
    }
  }
}
