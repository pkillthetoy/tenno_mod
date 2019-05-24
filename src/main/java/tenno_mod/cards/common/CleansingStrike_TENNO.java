package tenno_mod.cards.common;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import tenno_mod.actions.ExhaustVoidFromDrawOrDiscardAction;
import tenno_mod.patches.AbstractCardEnum;

public class CleansingStrike_TENNO extends CustomCard {
  public static final String ID = "CleansingStrike_TENNO";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
  public static final String IMG_PATH = "img/cards/CleansingStrike.png";
  private static final int COST = 1;
  private static final int ATTACK_DMG = 10;
  private static final int UPGRADE_PLUS_DMG = 3;

  public CleansingStrike_TENNO() {
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
    AbstractDungeon.actionManager.addToBottom(
        new DamageAction(
            m,
            new DamageInfo(p, this.damage, this.damageTypeForTurn),
            AbstractGameAction.AttackEffect.BLUNT_LIGHT
        )
    );
    if (this.upgraded) {
      AbstractDungeon.actionManager.addToBottom(
          new ExhaustVoidFromDrawOrDiscardAction());
    }
    AbstractDungeon.actionManager.addToBottom(
        new ExhaustVoidFromDrawOrDiscardAction());

  }

  public AbstractCard makeCopy() {
    return new CleansingStrike_TENNO();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      this.rawDescription = DESCRIPTION_UPG;
      upgradeDamage(UPGRADE_PLUS_DMG);
      initializeDescription();
    }
  }
}
