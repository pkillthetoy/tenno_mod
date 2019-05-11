package tenno_mod.cards.rare;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import tenno_mod.patches.AbstractCardEnum;
import tenno_mod.powers.CorrosiveProjectionPower_TENNO;
import tenno_mod.powers.NinjaSkillsPower_TENNO;

public class NinjaSkills_TENNO extends CustomCard {
  public static final String ID = "NinjaSkills_TENNO";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
  public static final String IMG_PATH = "img/cards/Power.png";
  private static final int COST = 2;

  public NinjaSkills_TENNO() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        CardType.POWER,
        AbstractCardEnum.TENNO_COLOR,
        CardRarity.RARE,
        CardTarget.SELF
    );
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractDungeon.actionManager.addToBottom(
        new ApplyPowerAction(
            p,
            p,
            new NinjaSkillsPower_TENNO(p, 1), 1));
  }

  public AbstractCard makeCopy() {
    return new NinjaSkills_TENNO();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      this.isInnate = true;
      this.rawDescription = DESCRIPTION_UPG;
      initializeDescription();
    }
  }
}
