package tenno_mod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import tenno_mod.powers.RetainAndReducePower_TENNO;

import java.util.ArrayList;
import java.util.List;

public class MasteryBadge_TENNO extends CustomRelic {
  public static final String ID = "MasteryBadge_TENNO";

  private static final String IMG = "img/relics/Beta.png";
  private static final String IMG_OTL = "img/relics/outline/Hakkero_s.png";
  List<AbstractCard> cardsToReduce;

  public MasteryBadge_TENNO() {
    super(ID,
        ImageMaster.loadImage(IMG),
        ImageMaster.loadImage(IMG_OTL), RelicTier.RARE, LandingSound.CLINK);
    cardsToReduce = new ArrayList<>();
  }

  public String getUpdatedDescription() {
    return this.DESCRIPTIONS[0];
  }

  @Override
  public void atBattleStart() {
    this.flash();
    AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
        new RetainAndReducePower_TENNO(AbstractDungeon.player, 1, this), 1));
    AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
  }

  @Override
  public void atTurnStart() {
    for (AbstractCard c : cardsToReduce) {
      if (c.cost > 0 && c.costForTurn > 0 && !c.freeToPlayOnce) {
        c.setCostForTurn(c.costForTurn - 1);
        c.flash();
        c.superFlash();
        c.applyPowers();
      }
    }
    cardsToReduce.clear();
  }

  public void addCardToReduce(AbstractCard c) {
    cardsToReduce.add(c);
  }

  @Override
  public AbstractRelic makeCopy() {
    return new MasteryBadge_TENNO();
  }
}
