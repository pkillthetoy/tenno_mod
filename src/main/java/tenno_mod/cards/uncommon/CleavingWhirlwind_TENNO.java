package tenno_mod.cards.uncommon;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import tenno_mod.actions.CleavingWhirlwindAction;
import tenno_mod.patches.AbstractCardEnum;

public class CleavingWhirlwind_TENNO extends CustomCard {
  public static final String ID = "CleavingWhirlwind_TENNO";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String IMG_PATH = "img/cards/CleavingWhirlwind.png";
  private static final int COST = 1;
  private static final int ATTACK_DMG = 10;
  private static final int UPGRADE_PLUS_DMG = 3;

  public CleavingWhirlwind_TENNO() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        CardType.ATTACK,
        AbstractCardEnum.TENNO_COLOR,
        CardRarity.UNCOMMON,
        CardTarget.ALL_ENEMY
    );
    this.baseDamage = ATTACK_DMG;
    this.isMultiDamage = true;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {

    DamageInfo info = new DamageInfo(p, this.damage, this.damageTypeForTurn);
    AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_HEAVY"));
    AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new CleaveEffect(), 0.0F));

    AbstractDungeon.actionManager.addToBottom(new CleavingWhirlwindAction(p, info, this));

  }

  public AbstractCard makeCopy() {
    return new CleavingWhirlwind_TENNO();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeDamage(UPGRADE_PLUS_DMG);
    }
  }
}
