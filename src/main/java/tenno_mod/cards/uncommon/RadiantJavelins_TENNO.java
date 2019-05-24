package tenno_mod.cards.uncommon;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import tenno_mod.actions.ExhaustVoidFromDrawOrDiscardAction;
import tenno_mod.patches.AbstractCardEnum;
import tenno_mod.shared.VoidUtils;

import java.util.Iterator;

public class RadiantJavelins_TENNO extends CustomCard {
  public static final String ID = "RadiantJavelins_TENNO";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String IMG_PATH = "img/cards/RadiantJavelin.png";
  private static final int COST = 2;
  private static final int BASE_DMG = 14;
  private static final int MAGIC_NUMBER = 6;
  private static final int UPG_MAGIC_NUMBER = 3;

  public RadiantJavelins_TENNO() {
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
    this.baseDamage = BASE_DMG;
    this.baseMagicNumber = this.magicNumber = MAGIC_NUMBER;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractDungeon.actionManager.addToBottom(
        new ExhaustVoidFromDrawOrDiscardAction());
    AbstractDungeon.actionManager.addToBottom(
        new DamageAction(
            m,
            new DamageInfo(p, this.damage, this.damageTypeForTurn),
            AbstractGameAction.AttackEffect.FIRE
        )
    );
  }

  public AbstractCard makeCopy() {
    return new RadiantJavelins_TENNO();
  }

  public static int countVoidsInExhaust() {
    int count = 0;
    AbstractPlayer p = AbstractDungeon.player;
    for (AbstractCard c : p.exhaustPile.group) {
      if (VoidUtils.isVoid(c)) {
        count++;
      }
    }
    return count;
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeMagicNumber(UPG_MAGIC_NUMBER);
    }
  }

  @Override
  public void calculateCardDamage(AbstractMonster mo) {
    AbstractPlayer player = AbstractDungeon.player;
    this.isDamageModified = false;
    if (mo != null) {
      float tmp = (float) this.baseDamage;

      int voidCount = countVoidsInExhaust();
      if (voidCount > 0) {
        tmp += this.magicNumber * voidCount;
        this.isDamageModified = true;
      }
      if (VoidUtils.countVoidsInDrawAndDiscard() > 0) {
        tmp += this.magicNumber;
        this.isDamageModified = true;
      }

      if (AbstractDungeon.player.hasRelic("WristBlade") && (this.costForTurn == 0 || this.freeToPlayOnce)) {
        tmp += 3.0F;
        if (this.baseDamage != (int) tmp) {
          this.isDamageModified = true;
        }
      }

      Iterator powers = player.powers.iterator();

      AbstractPower p;
      while (powers.hasNext()) {
        p = (AbstractPower) powers.next();

        tmp = p.atDamageGive(tmp, this.damageTypeForTurn);
        if (this.baseDamage != (int) tmp) {
          this.isDamageModified = true;
        }
      }

      if (mo != null) {
        for (powers = mo.powers.iterator(); powers.hasNext(); tmp = p.atDamageReceive(tmp, this.damageTypeForTurn)) {
          p = (AbstractPower) powers.next();
        }
      }

      powers = player.powers.iterator();

      while (powers.hasNext()) {
        p = (AbstractPower) powers.next();
        tmp = p.atDamageFinalGive(tmp, this.damageTypeForTurn);
        if (this.baseDamage != (int) tmp) {
          this.isDamageModified = true;
        }
      }

      if (mo != null) {
        powers = mo.powers.iterator();

        while (powers.hasNext()) {
          p = (AbstractPower) powers.next();
          tmp = p.atDamageFinalReceive(tmp, this.damageTypeForTurn);
          if (this.baseDamage != (int) tmp) {
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

  @Override
  public void applyPowers() {
    super.applyPowers();
    int count = countVoidsInExhaust();
    this.damage += this.magicNumber * count;
    if (count > 0) {
      this.isDamageModified = true;
    }
    if (VoidUtils.countVoidsInDrawAndDiscard() > 0) {
      this.damage += this.magicNumber;
      this.isDamageModified = true;
    }
  }

}
