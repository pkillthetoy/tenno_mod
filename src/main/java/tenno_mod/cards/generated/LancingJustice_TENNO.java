package tenno_mod.cards.generated;

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
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import tenno_mod.patches.AbstractCardEnum;

public class LancingJustice_TENNO extends CustomCard {
  public static final String ID = "LancingJustice_TENNO";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String IMG_PATH = "img/cards/Beta.png";
  private static final int COST = 1;
  private static final int ATTACK_DMG = 10;
  private static final int UPGRADE_PLUS_DMG = 3;

  public LancingJustice_TENNO() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        CardType.ATTACK,
        AbstractCardEnum.TENNO_GENERATED,
        CardRarity.SPECIAL,
        CardTarget.ENEMY
    );
    this.magicNumber = 1;
    this.baseDamage = ATTACK_DMG;
    this.exhaust = true;
    this.isEthereal = true;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractDungeon.actionManager.addToBottom(
        new DamageAction(
            m,
            new DamageInfo(p, this.damage, this.damageTypeForTurn),
            AbstractGameAction.AttackEffect.SLASH_HORIZONTAL
        )
    );
    AbstractDungeon.actionManager.addToBottom(
        new ApplyPowerAction(
            m,
            p,
            new VulnerablePower(m, this.magicNumber, false),
            this.magicNumber,
            true,
            AbstractGameAction.AttackEffect.NONE)
    );
    AbstractDungeon.actionManager.addToBottom(
        new ApplyPowerAction(
            m,
            p,
            new WeakPower(m, this.magicNumber, false),
            this.magicNumber,
            true,
            AbstractGameAction.AttackEffect.NONE)
    );
  }

  public AbstractCard makeCopy() {
    return new LancingJustice_TENNO();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeDamage(UPGRADE_PLUS_DMG);
    }
  }
}
