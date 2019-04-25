package tenno_mod.cards.uncommon;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import tenno_mod.TennoMod;
import tenno_mod.actions.DrawAndReduceAction;
import tenno_mod.actions.DrawAndReduceCardThisCombatAction;
import tenno_mod.patches.AbstractCardEnum;

public class SelfImprovement_TENNO extends CustomCard {
  public static final String ID = "SelfImprovement_TENNO";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
  public static final String IMG_PATH = "img/cards/Skill.png";
  private static final int COST = 1;

  public SelfImprovement_TENNO() {
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
    if (AbstractDungeon.player.drawPile.isEmpty()) {
      AbstractDungeon.actionManager.addToBottom(new EmptyDeckShuffleAction());
    }
    AbstractDungeon.actionManager.addToBottom(new DrawAndReduceCardThisCombatAction(p));
    if (this.upgraded) {
      if (p.drawPile.size() == 1) {
        TennoMod.logger.info("second shuffle");
        AbstractDungeon.actionManager.addToBottom(new EmptyDeckShuffleAction());
      }
      AbstractDungeon.actionManager.addToBottom(new DrawAndReduceCardThisCombatAction(p));
    }
  }

  public AbstractCard makeCopy() {
    return new SelfImprovement_TENNO();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      this.rawDescription = DESCRIPTION_UPG;
      initializeDescription();
    }
  }
}
