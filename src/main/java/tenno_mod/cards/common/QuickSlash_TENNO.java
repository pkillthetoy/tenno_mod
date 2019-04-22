package tenno_mod.cards.common;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import tenno_mod.patches.AbstractCardEnum;

public class QuickSlash_TENNO extends CustomCard {
  public static final String ID = "QuickSlash_TENNO";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String IMG_PATH = "img/cards/Beta.png";
  private static final int COST = 0;
  private static final int ATTACK_DMG = 2;

  private static final int UPGRADE_PLUS_DMG = 2;

  public QuickSlash_TENNO() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        CardType.ATTACK,
        AbstractCardEnum.TENNO_COLOR,
        CardRarity.COMMON,
        CardTarget.ENEMY
    );
    this.baseDamage = ATTACK_DMG;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
        new com.megacrit.cardcrawl.cards.DamageInfo(p, this.damage, this.damageTypeForTurn),
        AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DrawCardAction(p, 1));
  }

  public AbstractCard makeCopy() {
    return new QuickSlash_TENNO();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeDamage(UPGRADE_PLUS_DMG);
    }
  }
}
