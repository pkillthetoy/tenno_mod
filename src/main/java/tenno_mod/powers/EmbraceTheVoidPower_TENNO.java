
package tenno_mod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class EmbraceTheVoidPower_TENNO extends AbstractPower
 {
   public static final String POWER_ID = "EmbraceTheVoidPower_TENNO";
   private static final PowerStrings powerStrings = com.megacrit.cardcrawl.core.CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
   public static final String NAME = powerStrings.NAME;
   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   
   public EmbraceTheVoidPower_TENNO(AbstractCreature owner, int amount) {
     this.name = NAME;
     this.ID = POWER_ID;
     this.owner = owner;
     this.amount = amount;
     updateDescription();
     loadRegion("darkembrace");
   }
   
   public void updateDescription()
   {
       this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];

   }
   
   public void onExhaust(AbstractCard card)
   {
     if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead() && card.name.equals(VoidCard.NAME)) {
       flash();
       AbstractDungeon.actionManager.addToBottom( new ApplyPowerAction(
           owner,
           owner,
           new StrengthPower(owner, this.amount), this.amount));
     }
   }
 }
