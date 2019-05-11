package tenno_mod.cards.rare;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.unique.FlechetteAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import tenno_mod.actions.ConcealedWeaponBlockAction;
import tenno_mod.patches.AbstractCardEnum;

public class ConcealedWeapons_TENNO extends CustomCard {
  public static final String ID = "ConcealedWeapons_TENNO";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
  public static final String IMG_PATH = "img/cards/Beta.png";
  private static final int COST = 2;
  private static final int DAMAGE = 4;
  private static final int UPG_DAMAGE = 2;
  private static final int BLOCK_AMT = 4;
  private static final int UPG_BLOCK_AMT = 2;


  public ConcealedWeapons_TENNO() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        CardType.ATTACK,
        AbstractCardEnum.TENNO_COLOR,
        CardRarity.RARE,
        CardTarget.ENEMY
    );
    this.baseDamage = DAMAGE;
    this.baseBlock = BLOCK_AMT;
    this.retain = true;
    this.exhaust = true;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {

    AbstractDungeon.actionManager.addToBottom(
        new FlechetteAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn)));

    AbstractDungeon.actionManager.addToBottom(new ConcealedWeaponBlockAction(p, this.block));
    this.rawDescription = DESCRIPTION;
    initializeDescription();
  }

  public AbstractCard makeCopy() {
    return new ConcealedWeapons_TENNO();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeDamage(UPG_DAMAGE);
      upgradeBlock(UPG_BLOCK_AMT);
    }
  }

  public void onMoveToDiscard() {
    this.rawDescription = DESCRIPTION;
    initializeDescription();
  }

  @Override
  public void applyPowers() {
    super.applyPowers();

    int skillCount = 0;
    int attackCount = 0;
    for (AbstractCard c : AbstractDungeon.player.hand.group) {
      if (c.type == CardType.SKILL) {
        skillCount++;
      }
      if (c.type == CardType.ATTACK && c != this) {
        attackCount++;
      }
    }

    this.rawDescription = DESCRIPTION;
    this.rawDescription = (this.rawDescription + EXTENDED_DESCRIPTION[0] + skillCount);
    if (skillCount == 1) {
      this.rawDescription += EXTENDED_DESCRIPTION[1] + attackCount;
    } else {
      this.rawDescription += EXTENDED_DESCRIPTION[2] + attackCount;
    }
    if (attackCount == 1) {
      this.rawDescription += EXTENDED_DESCRIPTION[3];
    } else {
      this.rawDescription += EXTENDED_DESCRIPTION[4];
    }
    initializeDescription();
  }
}
