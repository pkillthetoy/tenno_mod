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

import java.util.Iterator;

public class Snipe_TENNO extends CustomCard {
  public static final String ID = "Snipe_TENNO";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String IMG_PATH = "img/cards/Beta.png";
  private static final int COST = 0;
  private static final int BASE_DMG = 5;
  private static final int UPG_ATTACK_DAMAGE = 3;

  public Snipe_TENNO() {
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
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractDungeon.actionManager.addToBottom(
        new ExhaustVoidFromDrawOrDiscardAction());
    AbstractDungeon.actionManager.addToBottom(
        new DamageAction(
            m,
            new DamageInfo(p, this.damage, this.damageTypeForTurn),
            AbstractGameAction.AttackEffect.BLUNT_LIGHT
        )
    );
  }

  public AbstractCard makeCopy() {
    return new Snipe_TENNO();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeDamage(UPG_ATTACK_DAMAGE);
    }
  }

  @Override
  public void calculateCardDamage(AbstractMonster mo) {
    AbstractPlayer player = AbstractDungeon.player;
    this.isDamageModified = false;
    if (mo != null) {
      float tmp = (float) this.baseDamage;
      if (player.hasPower("Dexterity")) {
        tmp += player.getPower("Dexterity").amount;
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
    AbstractPlayer p = AbstractDungeon.player;
    if (p.hasPower("Dexterity")) {
      this.damage += p.getPower("Dexterity").amount;
      this.isDamageModified = true;
    }
  }

}
