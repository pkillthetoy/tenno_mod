package tenno_mod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import tenno_mod.TennoMod;
import tenno_mod.actions.ArgonCrystalEffectAction;

public class Kuva_TENNO extends CustomRelic {
  public static final String ID = "Kuva_TENNO";
  private static final int HEAL = 2;

  private static final String IMG = "img/relics/Beta.png";
  private static final String IMG_OTL = "img/relics/outline/Hakkero_s.png";

  public Kuva_TENNO() {
    super(ID,
        ImageMaster.loadImage(IMG),
        ImageMaster.loadImage(IMG_OTL), RelicTier.RARE, LandingSound.MAGICAL);
  }

  public String getUpdatedDescription() {
    return this.DESCRIPTIONS[0] + HEAL + this.DESCRIPTIONS[1];
  }

  public void onExhaust(AbstractCard card) {
    if (card.name.equals(VoidCard.NAME)) {
      flash();
      AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
      AbstractDungeon.actionManager.addToBottom(new HealAction(AbstractDungeon.player, AbstractDungeon.player, HEAL));
    }
  }

  @Override
  public AbstractRelic makeCopy() {
    return new Kuva_TENNO();
  }
}
