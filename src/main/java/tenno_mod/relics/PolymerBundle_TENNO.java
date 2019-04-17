package tenno_mod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class PolymerBundle_TENNO extends CustomRelic {
  public static final String ID = "PolymerBundle_TENNO";

  private static final String IMG = "img/relics/Beta.png";
  private static final String IMG_OTL = "img/relics/outline/Hakkero_s.png";

  public PolymerBundle_TENNO() {
    super(ID,
        ImageMaster.loadImage(IMG),
        ImageMaster.loadImage(IMG_OTL), RelicTier.COMMON, LandingSound.FLAT);
  }

  public String getUpdatedDescription() {
    return this.DESCRIPTIONS[0];
  }

  @Override
  public void atTurnStart() {
    this.counter = 0;
  }

  @Override
  public void onCardDraw(AbstractCard drawnCard) {
    AbstractPlayer p = AbstractDungeon.player;
    this.counter++;
    if (this.counter % 2 == 0) {
      flash();
      AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(p, this));
      this.counter = 0;
      AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, 1));
    }
  }

  @Override
  public AbstractRelic makeCopy() {
    return new PolymerBundle_TENNO();
  }
}
