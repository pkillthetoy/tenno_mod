/*    */
package tenno_mod.powers;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.GameActionManager;
/*    */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.LocalizedStrings;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

/*    */
/*    */ public class DriftingContactPower_TENNO extends AbstractPower
/*    */ {
/*    */   public static final String POWER_ID = "DriftingContactPower_TENNO";
/* 14 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
/* 15 */   public static final String NAME = powerStrings.NAME;
/* 16 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public DriftingContactPower_TENNO(AbstractCreature owner, int amount) {
/* 19 */     this.name = NAME;
/* 20 */     this.ID = POWER_ID;
/* 21 */     this.owner = owner;
/* 22 */     this.amount = amount;
/* 23 */     updateDescription();
  this.type = PowerType.BUFF;
  this.img = new Texture("img/powers/uparrow.png");
/*    */   }
/*    */   
/*    */   public void updateDescription()
/*    */   {
/* 29 */     this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
/*    */   }
/*    */   
/*    */   public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source)
/*    */   {
/* 34 */     if ((power.type == AbstractPower.PowerType.DEBUFF) && (!power.ID.equals("Shackled")) && (source == this.owner) && (target != this.owner) && 
/* 35 */       (!target.hasPower("Artifact"))) {
/* 36 */       flash();
/* 37 */       AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this.owner, this.owner, this.amount));
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Tony\Documents\my_mods\lib\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\DriftingContactPower_TENNO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */