package tenno_mod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class DragonNikana_TENNO extends CustomRelic {
  public static final String ID = "DragonNikana_TENNO";
  private static final int MAGIC_NUMBER = 1;

  private static final String IMG = "img/relics/DragonNikana.png";
  private static final String IMG_OTL = "img/relics/outline/DragonNikana_s.png";

  private int skillCount = 0;
  private int attackCount = 0;

  public DragonNikana_TENNO() {
    super(ID,
        ImageMaster.loadImage(IMG),
        ImageMaster.loadImage(IMG_OTL), RelicTier.BOSS, LandingSound.CLINK);
  }

  public String getUpdatedDescription() {
    return this.DESCRIPTIONS[0] + MAGIC_NUMBER + this.DESCRIPTIONS[1] + MAGIC_NUMBER + this.DESCRIPTIONS[2];
  }

  public void onUseCard(AbstractCard card, UseCardAction action) {
    AbstractPlayer p = AbstractDungeon.player;
    if (card.type == AbstractCard.CardType.SKILL) {
      flash();
      AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(p, this));
      AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(
          p, p, new StrengthPower(p, MAGIC_NUMBER), MAGIC_NUMBER));
      skillCount++;
    }
    if (card.type == AbstractCard.CardType.ATTACK) {
      flash();
      AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(p, this));
      AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(
          p, p, new DexterityPower(p, MAGIC_NUMBER), MAGIC_NUMBER));
      attackCount++;
    }
  }

  public void atTurnStart() {
    AbstractPlayer p = AbstractDungeon.player;
    removeStrength();
    removeDex();

    reset();
  }

  private void removeStrength() {
    AbstractPlayer p = AbstractDungeon.player;
    int strengthLoss = -MAGIC_NUMBER * skillCount;
    if (strengthLoss < 0) {
      AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(
          p, p, new StrengthPower(p, strengthLoss), strengthLoss));
    }
  }

  private void removeDex() {
    AbstractPlayer p = AbstractDungeon.player;
    int dexLoss = -MAGIC_NUMBER * attackCount;
    if (dexLoss < 0) {
      AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(
          p, p, new DexterityPower(p, dexLoss), dexLoss));
    }
  }

  public void onVictory() {
    reset();
  }

  private void reset() {
    skillCount = 0;
    attackCount = 0;
  }

  @Override
  public AbstractRelic makeCopy() {
    return new DragonNikana_TENNO();
  }

  public void obtain() {
    if (AbstractDungeon.player.hasRelic("Nikana_TENNO")) {
      instantObtain(AbstractDungeon.player, 0, false);
    } else {
      super.obtain();
    }
  }
}
