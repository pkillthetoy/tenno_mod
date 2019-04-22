package tenno_mod.cards.uncommon;

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
import tenno_mod.patches.AbstractCardEnum;

public class LethalTorrent_TENNO extends CustomCard {
  public static final String ID = "LethalTorrent_TENNO";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String IMG_PATH = "img/cards/Beta.png";
  private static final int COST = 1;
  private static final int ATTACK_DMG = 1;
  private static final int MAGIC_NUMBER = 4;
  private static final int UPGRADE_MAGIC_NUMBER = 1;

  public LethalTorrent_TENNO() {
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
    this.magicNumber = this.baseMagicNumber = MAGIC_NUMBER;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    for (int i = 0; i < this.magicNumber; i++) {
      AbstractDungeon.actionManager.addToBottom(
          new DamageAction(
              m,
              new DamageInfo(p, this.damage, this.damageTypeForTurn),
              AbstractGameAction.AttackEffect.BLUNT_LIGHT
          )
      );
    }
  }

  public AbstractCard makeCopy() {
    return new LethalTorrent_TENNO();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeMagicNumber(UPGRADE_MAGIC_NUMBER);
    }
  }
}
