package tenno_mod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class RaktaDarkDagger_TENNO extends CustomRelic {
  public static final String ID = "RaktaDarkDagger_TENNO";
  private static final int MAGIC_NUMBER = 1;

  private static final String IMG = "img/relics/RaktaDarkDagger.png";
  private static final String IMG_OTL = "img/relics/outline/RaktaDarkDagger_s.png";

  public RaktaDarkDagger_TENNO() {
    super(ID,
        ImageMaster.loadImage(IMG),
        ImageMaster.loadImage(IMG_OTL), RelicTier.BOSS, LandingSound.CLINK);
  }

  public String getUpdatedDescription() {
    return this.DESCRIPTIONS[0];
  }

  private static boolean SKILL = false;
  private static boolean ATTACK = false;
  private static boolean activated = true;

  public void atTurnStart() {
    this.activated = true;
    beginLongPulse();
    ATTACK = false;
    SKILL = false;
  }

  public void onUseCard(AbstractCard card, UseCardAction action) {
    if (card.type == AbstractCard.CardType.ATTACK) {
      ATTACK = true;
    } else if (card.type == AbstractCard.CardType.SKILL) {
      SKILL = true;
    }
    if (ATTACK && SKILL && this.activated) {
      flash();
      this.activated = false;
      AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
      AbstractDungeon.actionManager.addToTop(new GainEnergyAction(1));
      stopPulse();
    }
  }

  public void atBattleStart() {
    beginLongPulse();
  }

  public void onVictory() {
    stopPulse();
  }

  @Override
  public AbstractRelic makeCopy() {
    return new RaktaDarkDagger_TENNO();
  }
}
