package tenno_mod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class PaperLotus_TENNO extends CustomRelic {
  public PaperLotus_TENNO(String id, String imgName, RelicTier tier, LandingSound sfx) {
    super(id, imgName, tier, sfx);
  }


  public static final String ID = "PaperLotus_TENNO";
  private static final int DAMAGE = 3;

  private static final String IMG = "img/relics/PaperLotus.png";
  private static final String IMG_OTL = "img/relics/outline/Hakkero_s.png";

  public PaperLotus_TENNO() {
    super(ID,
        ImageMaster.loadImage(IMG),
        ImageMaster.loadImage(IMG_OTL), RelicTier.UNCOMMON, LandingSound.FLAT);
  }

  public String getUpdatedDescription() {
    return this.DESCRIPTIONS[0] + DAMAGE + this.DESCRIPTIONS[1];
  }

  @Override
  public AbstractRelic makeCopy() {
    return new PaperLotus_TENNO();
  }

  @Override
  public void onTrigger(AbstractCreature target) {
    AbstractPlayer p = AbstractDungeon.player;
    AbstractDungeon.actionManager.addToBottom(
        new com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction(target, this));
    AbstractDungeon.actionManager.addToBottom(
        new com.megacrit.cardcrawl.actions.common.DamageAction(
            target,
            // Sadistic Nature uses Thorns as damage type, copied from there.
            new DamageInfo(p, DAMAGE, com.megacrit.cardcrawl.cards.DamageInfo.DamageType.THORNS),
            AbstractGameAction.AttackEffect.FIRE));


  }
}
