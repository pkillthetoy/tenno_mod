package tenno_mod.cards.uncommon;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import tenno_mod.actions.DamagePerSkillPlayedAction;
import tenno_mod.patches.AbstractCardEnum;
import tenno_mod.shared.SkillUtils;

public class FinishingTouch_TENNO extends CustomCard {
  public static final String ID = "FinishingTouch_TENNO";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
  public static final String IMG_PATH = "img/cards/FinishingTouch.png";
  private static final int COST = 1;
  private static final int ATTACK_DMG = 4;
  private static final int UPGRADE_PLUS_DMG = 2;

  public FinishingTouch_TENNO() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        CardType.ATTACK,
        AbstractCardEnum.TENNO_COLOR,
        CardRarity.UNCOMMON,
        CardTarget.ENEMY
    );
    this.baseDamage = ATTACK_DMG;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractDungeon.actionManager.addToBottom(
        new DamagePerSkillPlayedAction(
            m,
            new DamageInfo(p, this.damage, this.damageTypeForTurn),
            AbstractGameAction.AttackEffect.SLASH_DIAGONAL
        )
    );
  }

  public AbstractCard makeCopy() {
    return new FinishingTouch_TENNO();
  }

  public void applyPowers() {
    super.applyPowers();

    int count = SkillUtils.countSkills();

    this.rawDescription = DESCRIPTION;
    this.rawDescription = (this.rawDescription + EXTENDED_DESCRIPTION[0] + count);

    if (count == 1) {
      this.rawDescription += EXTENDED_DESCRIPTION[1];
    } else {
      this.rawDescription += EXTENDED_DESCRIPTION[2];
    }
    initializeDescription();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeDamage(UPGRADE_PLUS_DMG);
      initializeDescription();
    }
  }
}
