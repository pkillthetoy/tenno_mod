package tenno_mod.cards.uncommon;

import basemod.BaseMod;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import tenno_mod.actions.DrinkKuvaAction;
import tenno_mod.patches.AbstractCardEnum;

public class DrinkTheKuva_TENNO extends CustomCard {
  public static final String ID = "DrinkTheKuva_TENNO";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
  public static final String IMG_PATH = "img/cards/Skill.png";
  private static final int COST = 1;

  public DrinkTheKuva_TENNO() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        CardType.SKILL,
        AbstractCardEnum.TENNO_COLOR,
        CardRarity.UNCOMMON,
        CardTarget.SELF
    );
    this.exhaust = true;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractDungeon.actionManager.addToBottom(
        new DrinkKuvaAction(p, BaseMod.MAX_HAND_SIZE)
    );
  }

  public AbstractCard makeCopy() {
    return new DrinkTheKuva_TENNO();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      this.exhaust = false;
      this.rawDescription = DESCRIPTION_UPG;
      initializeDescription();
    }
  }
}
