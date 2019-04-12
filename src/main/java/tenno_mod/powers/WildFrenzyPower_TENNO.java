package tenno_mod.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class WildFrenzyPower_TENNO extends AbstractPower {
  public static final String POWER_ID = "WildFrenzyPower_TENNO";
  private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(
      POWER_ID);
  public static final String NAME = powerStrings.NAME;
  public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

  public WildFrenzyPower_TENNO(AbstractCreature owner) {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    this.amount = -1;
    updateDescription();
    loadRegion("doubleTap");
  }

  public void updateDescription() {
    this.description = DESCRIPTIONS[0];
  }

  public void onUseCard(AbstractCard card, UseCardAction action) {
    if ((!card.purgeOnUse) && (card.type == AbstractCard.CardType.ATTACK)) {
      flash();
      AbstractMonster m = null;

      if (action.target != null) {
        m = (AbstractMonster) action.target;
      }

      AbstractCard tmp = card.makeSameInstanceOf();
      AbstractDungeon.player.limbo.addToBottom(tmp);
      tmp.current_x = card.current_x;
      tmp.current_y = card.current_y;
      tmp.target_x = (Settings.WIDTH / 2.0F - 300.0F * Settings.scale);
      tmp.target_y = (Settings.HEIGHT / 2.0F);
      tmp.freeToPlayOnce = true;

      if (m != null) {
        tmp.calculateCardDamage(m);
      }

      tmp.purgeOnUse = true;
      AbstractDungeon.actionManager.cardQueue.add(
          new com.megacrit.cardcrawl.cards.CardQueueItem(tmp, m, card.energyOnUse));
    }
  }

  public void atEndOfTurn(boolean isPlayer) {
    if (isPlayer) {
      AbstractDungeon.actionManager.addToBottom(
          new RemoveSpecificPowerAction(this.owner, this.owner, "WildFrenzyPower_TENNO"));
    }
  }
}