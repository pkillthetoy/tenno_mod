package tenno_mod.cards.uncommon;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import tenno_mod.actions.DrawAndReduceAction;
import tenno_mod.patches.AbstractCardEnum;

public class Reposition_TENNO extends CustomCard {
  public static final String ID = "Reposition_TENNO";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String IMG_PATH = "img/cards/Beta.png";
  private static final int COST = 1;
  private static final int BLOCK_AMT = 8;
  private static final int UPGRADE_PLUS_BLOCK = 3;

  public Reposition_TENNO() {
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
    this.baseBlock = BLOCK_AMT;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    if (AbstractDungeon.player.drawPile.isEmpty()) {
      AbstractDungeon.actionManager.addToBottom(new EmptyDeckShuffleAction());
    }
    AbstractDungeon.actionManager.addToBottom(new DrawAndReduceAction(CardType.ATTACK));
    AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, 1));
    AbstractDungeon.actionManager.addToBottom(
        new GainBlockAction(p, p, this.block)
    );
  }

  public AbstractCard makeCopy() {
    return new Reposition_TENNO();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeBlock(UPGRADE_PLUS_BLOCK);
    }
  }
}
