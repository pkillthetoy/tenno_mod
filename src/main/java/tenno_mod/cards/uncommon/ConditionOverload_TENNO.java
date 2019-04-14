package tenno_mod.cards.uncommon;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.DrawPileToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.red.HeavyBlade;
import com.megacrit.cardcrawl.cards.red.PerfectedStrike;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import tenno_mod.patches.AbstractCardEnum;

import java.util.Iterator;

public class ConditionOverload_TENNO extends CustomCard {
  public static final String ID = "ConditionOverload_TENNO";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String IMG_PATH = "img/cards/Beta.png";
  private static final int COST = 2;
  private static final int ATTACK_DMG = 10;
  private static final int MAGIC_NUMBER = 3;
  private static final int UPG_MAGIC_NUMBER = 1;

  public ConditionOverload_TENNO() {
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
    this.magicNumber = this.baseMagicNumber = MAGIC_NUMBER;
    this.baseDamage = ATTACK_DMG;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractDungeon.actionManager.addToBottom(
        new DamageAction(
            m,
            new DamageInfo(p, this.damage, this.damageTypeForTurn),
            AbstractGameAction.AttackEffect.SLASH_HEAVY
        )
    );
  }

  public AbstractCard makeCopy() {
    return new ConditionOverload_TENNO();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeMagicNumber(UPG_MAGIC_NUMBER);
      initializeDescription();
    }
  }

  // Almost entirely copied from the single target portion of AbstractCard.calculateCardDamage
  // except for the marked section.
  // Might need to update this - don't know if it's better to do this with a patch class.
  public void calculateCardDamage(AbstractMonster mo) {

    AbstractPlayer player = AbstractDungeon.player;
    this.isDamageModified = false;
    if (mo != null) {
      float tmp = (float)this.baseDamage;

      if (AbstractDungeon.player.hasRelic("WristBlade") && (this.costForTurn == 0 || this.freeToPlayOnce)) {
        tmp += 3.0F;
        if (this.baseDamage != (int)tmp) {
          this.isDamageModified = true;
        }
      }

      Iterator powers = player.powers.iterator();

      AbstractPower p;
      while(powers.hasNext()) {
        p = (AbstractPower)powers.next();

        tmp = p.atDamageGive(tmp, this.damageTypeForTurn);
        if (this.baseDamage != (int)tmp) {
          this.isDamageModified = true;
        }
      }

      if (mo != null) {
        for(powers = mo.powers.iterator(); powers.hasNext(); tmp = p.atDamageReceive(tmp, this.damageTypeForTurn)) {
          p = (AbstractPower)powers.next();
        }
      }

      powers = player.powers.iterator();

      while(powers.hasNext()) {
        p = (AbstractPower)powers.next();
        tmp = p.atDamageFinalGive(tmp, this.damageTypeForTurn);
        if (this.baseDamage != (int)tmp) {
          this.isDamageModified = true;
        }
      }

      if (mo != null) {
        powers = mo.powers.iterator();
        while(powers.hasNext()) {
          p = (AbstractPower)powers.next();
          if (p.type == AbstractPower.PowerType.DEBUFF) {
            tmp += this.magicNumber;
            this.isDamageModified = true;
          }
        }
        powers = mo.powers.iterator();

        while(powers.hasNext()) {
          p = (AbstractPower)powers.next();
          tmp = p.atDamageFinalReceive(tmp, this.damageTypeForTurn);
          if (this.baseDamage != (int)tmp) {
            this.isDamageModified = true;
          }
        }
      }

      if (tmp < 0.0F) {
        tmp = 0.0F;
      }

      this.damage = MathUtils.floor(tmp);
    }

  }

}
