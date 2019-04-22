package tenno_mod.cards.common;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import tenno_mod.patches.AbstractCardEnum;

import java.util.List;

public class DefensiveThrow_TENNO extends CustomCard {
  public static final String ID = "DefensiveThrow_TENNO";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String IMG_PATH = "img/cards/Beta.png";
  private static final int COST = 1;
  private static final int ATTACK_DMG = 6;
  private static final int UPGRADE_PLUS_DMG = 3;
  private static final int BLOCK_AMT = 3;
  private static final int UPGRADE_PLUS_BLOCK = 1;

  public DefensiveThrow_TENNO() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        CardType.ATTACK,
        AbstractCardEnum.TENNO_COLOR,
        CardRarity.COMMON,
        CardTarget.ALL_ENEMY
    );
    this.baseDamage = ATTACK_DMG;
    this.baseBlock = BLOCK_AMT;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn,
        AbstractGameAction.AttackEffect.BLUNT_LIGHT));
    List<AbstractMonster> monsters = AbstractDungeon.getCurrRoom().monsters.monsters;
    for (int i = 0; i < monsters.size(); i++) {
      AbstractDungeon.actionManager.addToBottom(
          new GainBlockAction(p, p, this.block)
      );
    }
  }

  public AbstractCard makeCopy() {
    return new DefensiveThrow_TENNO();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeDamage(UPGRADE_PLUS_DMG);
      upgradeBlock(UPGRADE_PLUS_BLOCK);
    }
  }
}
