package tenno_mod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import tenno_mod.actions.ArgonCrystalEffectAction;
import tenno_mod.actions.ReduceRandomCardInHandAction;

import java.util.ArrayList;

import static tenno_mod.TennoMod.logger;

public class ArgonCrystal_TENNO extends CustomRelic {
  public ArgonCrystal_TENNO(String id, String imgName, RelicTier tier, LandingSound sfx) {
    super(id, imgName, tier, sfx);
  }


  public static final String ID = "ArgonCrystal_TENNO";
  private static final int DAMAGE = 3;

  private static final String IMG = "img/relics/Beta.png";
  private static final String IMG_OTL = "img/relics/outline/Hakkero_s.png";

  public ArgonCrystal_TENNO() {
    super(ID,
        ImageMaster.loadImage(IMG),
        ImageMaster.loadImage(IMG_OTL), RelicTier.SHOP, LandingSound.MAGICAL);
  }

  public String getUpdatedDescription() {
    return this.DESCRIPTIONS[0];
  }

  public void atTurnStartPostDraw() {
    flash();
    AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    AbstractDungeon.actionManager.addToBottom(new ArgonCrystalEffectAction());
  }



  @Override
  public AbstractRelic makeCopy() {
    return new ArgonCrystal_TENNO();
  }

}
