package tenno_mod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class Skiajati_TENNO extends CustomRelic {
  public static final String ID = "Skiajati_TENNO";

  private static final String IMG = "img/relics/Skiajati.png";
  private static final String IMG_OTL = "img/relics/outline/Skiajati_s.png";

  public Skiajati_TENNO() {
    super(ID,
        ImageMaster.loadImage(IMG),
        ImageMaster.loadImage(IMG_OTL), RelicTier.RARE, LandingSound.CLINK);
  }

  public String getUpdatedDescription() {
    return this.DESCRIPTIONS[0];
  }


  public void onMonsterDeath(AbstractMonster m) {
    if ((m.currentHealth == 0) && (!AbstractDungeon.getMonsters().areMonstersBasicallyDead())) {
      flash();
      AbstractPlayer p = AbstractDungeon.player;
      AbstractDungeon.actionManager.addToBottom(
          new com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction(m, this));
      AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, 1), 1));
    }
  }

  @Override
  public AbstractRelic makeCopy() {
    return new Skiajati_TENNO();
  }
}
