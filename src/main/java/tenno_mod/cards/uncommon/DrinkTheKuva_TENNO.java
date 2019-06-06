package tenno_mod.cards.uncommon;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;
import tenno_mod.actions.DrinkKuvaAction;
import tenno_mod.actions.TransferrenceBeamAction;
import tenno_mod.patches.AbstractCardEnum;

public class DrinkTheKuva_TENNO extends CustomCard {
  public static final String ID = "DrinkTheKuva_TENNO";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
  public static final String IMG_PATH = "img/cards/DrinkTheKuva.png";
  private static final int COST = -1;

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
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    if (this.energyOnUse < EnergyPanel.totalCount) {
      this.energyOnUse = EnergyPanel.totalCount;
    }

    int amount = energyOnUse;
    if (this.upgraded) {
      amount ++;
    }
    AbstractDungeon.actionManager.addToBottom(
        new DrinkKuvaAction(p, amount, p, this.freeToPlayOnce));
  }

  public AbstractCard makeCopy() {
    return new DrinkTheKuva_TENNO();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      this.rawDescription = DESCRIPTION_UPG;
      initializeDescription();
    }
  }
}
