package tenno_mod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class TwinGrakata_TENNO extends CustomRelic {
  public static final String ID = "TwinGrakata_TENNO";

  private static final String IMG = "img/relics/TwinGrakata.png";
  private static final String IMG_OTL = "img/relics/outline/TwinGrakata_s.png";

  private boolean activated;

  public TwinGrakata_TENNO() {
    super(ID,
        ImageMaster.loadImage(IMG),
        ImageMaster.loadImage(IMG_OTL), RelicTier.UNCOMMON, LandingSound.CLINK);
    this.activated = true;
  }

  public String getUpdatedDescription() {
    return this.DESCRIPTIONS[0];
  }

  public void atTurnStart() {
    this.activated = true;
    beginPulse();
  }

  public void onUseCard(AbstractCard card, UseCardAction action) {
    if (!this.activated) {
      return;
    }
    if (!card.purgeOnUse && card.costForTurn == 0) {
      flash();
      AbstractMonster m = null;

      if (action.target != null) {
        m = (AbstractMonster) action.target;
      }
      AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
      AbstractCard tmp = card.makeSameInstanceOf();
      tmp.current_x = card.current_x;
      tmp.current_y = card.current_y;
      tmp.target_x = (Settings.WIDTH / 2.0F - 300.0F * Settings.scale);
      tmp.target_y = (Settings.HEIGHT / 2.0F);
      tmp.freeToPlayOnce = true;
      tmp.applyPowers();
      tmp.purgeOnUse = true;
      AbstractDungeon.actionManager.cardQueue.add(new com.megacrit.cardcrawl.cards.CardQueueItem(tmp, m));

      this.stopPulse();
      this.activated = false;
    }
  }

  @Override
  public AbstractRelic makeCopy() {
    return new TwinGrakata_TENNO();
  }
}
