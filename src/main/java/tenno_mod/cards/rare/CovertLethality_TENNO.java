package tenno_mod.cards.rare;

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
import tenno_mod.actions.CullingAction;
import tenno_mod.patches.AbstractCardEnum;

public class CovertLethality_TENNO extends CustomCard {
  public static final String ID = "CovertLethality_TENNO";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String IMG_PATH = "img/cards/CovertLethality.png";
  private static final int COST = 3;
  private static final int ATTACK_DMG = 20;
  private static final int UPGRADE_PLUS_DMG = 7;

  public CovertLethality_TENNO() {
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
    this.baseDamage = ATTACK_DMG;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractGameAction.AttackEffect effect;
    if (cullCheck(m)) {
      effect = AbstractGameAction.AttackEffect.SLASH_HEAVY;
    } else {
      effect = AbstractGameAction.AttackEffect.SLASH_HORIZONTAL;
    }
    AbstractDungeon.actionManager.addToBottom(
        new DamageAction(
            m,
            new DamageInfo(p, this.damage, this.damageTypeForTurn),
            effect
        )
    );
    if (cullCheck(m)) {
      AbstractDungeon.actionManager.addToBottom(new CullingAction(m));
    }
  }

  public AbstractCard makeCopy() {
    return new CovertLethality_TENNO();
  }

  private boolean cullCheck(AbstractMonster m) {
    return m.type != AbstractMonster.EnemyType.BOSS && m.type != AbstractMonster.EnemyType.ELITE &&
        m.hasPower("Vulnerable");
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeDamage(UPGRADE_PLUS_DMG);
    }
  }
}
