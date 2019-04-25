
package tenno_mod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class TransferrenceBeamAction extends com.megacrit.cardcrawl.actions.AbstractGameAction {
  private boolean freeToPlayOnce = false;
  private int damage;
  private AbstractPlayer p;
  private AbstractMonster m;
  private DamageInfo.DamageType damageTypeForTurn;
  private int energyOnUse = -1;


  public TransferrenceBeamAction(AbstractPlayer p, AbstractMonster m, int damage,
      DamageInfo.DamageType damageTypeForTurn, boolean freeToPlayOnce, int energyOnUse) {
    this.p = p;
    this.m = m;
    this.damage = damage;
    this.freeToPlayOnce = freeToPlayOnce;
    this.duration = Settings.ACTION_DUR_XFAST;
    this.actionType = AbstractGameAction.ActionType.SPECIAL;
    this.damageTypeForTurn = damageTypeForTurn;
    this.energyOnUse = energyOnUse;
  }

  public void update() {
    int effect = EnergyPanel.totalCount;
    if (this.energyOnUse != -1) {
      effect = this.energyOnUse;
    }

    if (this.p.hasRelic("Chemical X")) {
      effect += 2;
      this.p.getRelic("Chemical X").flash();
    }

    if (effect > 0) {
      AbstractDungeon.actionManager.addToTop(
          new ApplyPowerAction(
              m,
              p,
              new WeakPower(m, effect, false),
              effect,
              true)
      );
      AbstractDungeon.actionManager.addToTop(
          new ApplyPowerAction(
              m,
              p,
              new VulnerablePower(m, effect, false),
              effect,
              true)
      );
      for (int i = 0; i < effect; i++) {
        AbstractDungeon.actionManager.addToTop(new DamageAction(this.m,
            new DamageInfo(this.p, this.damage, this.damageTypeForTurn),
            AttackEffect.FIRE));
      }

      if (!this.freeToPlayOnce) {
        this.p.energy.use(EnergyPanel.totalCount);
      }
    }
    this.isDone = true;
  }
}