package tenno_mod.cards.basic;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import tenno_mod.patches.AbstractCardEnum;

public class MaimingStrike_TENNO extends CustomCard {
  public static final String ID = "MaimingStrike_TENNO";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String IMG_PATH = "img/cards/MaimingStrike.png";
  private static final int COST = 1;
  private static final int UPG_COST = 0;
  private static final int ATTACK_DMG = 7;
  private static final int MAGIC_NUMBER = 1;

  public MaimingStrike_TENNO() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        CardType.ATTACK,
        AbstractCardEnum.TENNO_COLOR,
        CardRarity.BASIC,
        CardTarget.ENEMY
    );
    this.tags.add(CardTags.STRIKE);
    this.magicNumber = this.baseMagicNumber = MAGIC_NUMBER;
    this.baseDamage = ATTACK_DMG;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractDungeon.actionManager.addToBottom(
        new DamageAction(
            m,
            new DamageInfo(p, this.damage, this.damageTypeForTurn),
            AbstractGameAction.AttackEffect.SLASH_DIAGONAL
        )
    );
    AbstractDungeon.actionManager.addToBottom(
        new ApplyPowerAction(
            m,
            p,
            new WeakPower(m, this.magicNumber, false),
            this.magicNumber,
            true)
    );
  }

  public AbstractCard makeCopy() {
    return new MaimingStrike_TENNO();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeBaseCost(UPG_COST);
    }
  }
}
