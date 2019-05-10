package tenno_mod.cards.rare;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.powers.IntangiblePower;
import tenno_mod.patches.AbstractCardEnum;

public class Cataclysm_TENNO extends CustomCard {
  public static final String ID = "Cataclysm_TENNO";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String IMG_PATH = "img/cards/Beta.png";
  private static final int COST = 3;
  private static final int ATTACK_DMG = 25;
  private static final int UPGRADE_PLUS_DMG = 8;

  public Cataclysm_TENNO() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        CardType.ATTACK,
        AbstractCardEnum.TENNO_COLOR,
        CardRarity.RARE,
        CardTarget.ALL
    );
    this.baseDamage = ATTACK_DMG;
    this.isMultiDamage = true;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractDungeon.actionManager.addToBottom(
        new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn,
            AbstractGameAction.AttackEffect.FIRE
        )
    );
    AbstractDungeon.actionManager.addToBottom(
        new ApplyPowerAction(p, p,
            new IntangiblePlayerPower(p, 1), 1));
    for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
      AbstractDungeon.actionManager.addToBottom(
          new ApplyPowerAction(mo, p,
              new IntangiblePlayerPower(mo, 1)));
    }
    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(new VoidCard(), 1));
  }

  public AbstractCard makeCopy() {
    return new Cataclysm_TENNO();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeDamage(UPGRADE_PLUS_DMG);
    }
  }
}
