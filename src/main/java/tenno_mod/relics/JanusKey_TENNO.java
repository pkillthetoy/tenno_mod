package tenno_mod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class JanusKey_TENNO extends CustomRelic {
  public static final String ID = "JanusKey_TENNO";
  private static final int GAIN_NUMBER = 2;
  private static final int SHUFFLE_NUMBER = 3;

  private static final String IMG = "img/relics/Beta.png";
  private static final String IMG_OTL = "img/relics/outline/Hakkero_s.png";

  public JanusKey_TENNO() {
    super(ID,
        ImageMaster.loadImage(IMG),
        ImageMaster.loadImage(IMG_OTL), RelicTier.BOSS, LandingSound.MAGICAL);
  }

  public String getUpdatedDescription() {
    return this.DESCRIPTIONS[0] + GAIN_NUMBER + this.DESCRIPTIONS[1] + GAIN_NUMBER +
        this.DESCRIPTIONS[2] + SHUFFLE_NUMBER + this.DESCRIPTIONS[3];
  }

  public void atBattleStart() {
    AbstractPlayer p = AbstractDungeon.player;
    AbstractDungeon.actionManager.addToBottom(
        new RelicAboveCreatureAction(p, this));
    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(
        p, p, new StrengthPower(p, GAIN_NUMBER), GAIN_NUMBER));
    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(
        p, p, new DexterityPower(p, GAIN_NUMBER), GAIN_NUMBER));
    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(new VoidCard(), 3));
  }

  @Override
  public AbstractRelic makeCopy() {
    return new JanusKey_TENNO();
  }

}
