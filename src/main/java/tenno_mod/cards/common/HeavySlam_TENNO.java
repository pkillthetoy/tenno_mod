package tenno_mod.cards.common;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.DrawPileToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import tenno_mod.TennoMod;
import tenno_mod.patches.AbstractCardEnum;

public class HeavySlam_TENNO extends CustomCard {
  public static final String ID = "HeavySlam_TENNO";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String IMG_PATH = "img/cards/Beta.png";
  private static final int COST = 2;
  private static final int ATTACK_DMG = 12;
  private static final int ENERGY_COST_REDUCTION = 1;
  private static final int UPGRADE_PLUS_DMG = 4;

  public HeavySlam_TENNO() {
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
    AbstractDungeon.actionManager.addToBottom(
        new DrawPileToHandAction(this.magicNumber, CardType.SKILL));
  }

  public AbstractCard makeCopy() {
    return new HeavySlam_TENNO();
  }

  private boolean hadReducedCost = false;

  public void triggerWhenDrawn() {
    super.triggerWhenDrawn();
    hadReducedCost = false;
  }

  @Override
  public void applyPowers() {
    if (this.freeToPlayOnce || this.cost == 0) {
      // Cost should stay 0. It is 0 from another source.
      return;
    }
    if (this.costForTurn == 0 && !hadReducedCost) {
      // This is reduced to 0 from a different effect.
      return;
    }
    if (TennoMod.lastCardUsedWasSkill && !hadReducedCost) {
      setCostForTurn(this.costForTurn - ENERGY_COST_REDUCTION);
      hadReducedCost = true;
    }
    if (!TennoMod.lastCardUsedWasSkill && hadReducedCost) {
      // Undo the cost reduction
      setCostForTurn(this.costForTurn + ENERGY_COST_REDUCTION);
      hadReducedCost = false;
    }
    super.applyPowers();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeDamage(UPGRADE_PLUS_DMG);
      initializeDescription();
    }
  }
}
