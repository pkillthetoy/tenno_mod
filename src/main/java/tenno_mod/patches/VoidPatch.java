package tenno_mod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import tenno_mod.relics.PaperLotus_TENNO;

public class VoidPatch {
  @SpirePatch(cls = "com.megacrit.cardcrawl.cards.status.VoidCard", method="triggerWhenDrawn")
  public static class VoidDraw {
    @SpirePrefixPatch
    public static SpireReturn Prefix(AbstractCard _inst) {
      AbstractPlayer p = AbstractDungeon.player;
      if (p.hasPower("VoidAttunementPower_TENNO")) {
        AbstractPower power = p.getPower("VoidAttunementPower_TENNO");
        power.flash();
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, power.amount));
        AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(power.amount));
      }
      return SpireReturn.Continue();
    }
  }
}
