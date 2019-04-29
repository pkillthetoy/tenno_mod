package tenno_mod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class CorrosiveProjectionPower_TENNO extends AbstractPower {
  public static final String POWER_ID = "CorrosiveProjectionPower_TENNO";
  private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(
      POWER_ID);
  public static final String NAME = powerStrings.NAME;
  public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

  public CorrosiveProjectionPower_TENNO(AbstractCreature owner, int amount) {
    this.ID = POWER_ID;
    this.name = NAME;
    this.owner = owner;
    this.amount = amount;
    updateDescription();
    this.type = PowerType.BUFF;
    this.img = new Texture("img/powers/uparrow.png");
  }

  public void updateDescription() {
    this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
  }

  public void atStartOfTurnPostDraw() {
    if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
      flash();
      for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
        if ((!m.isDead) && (!m.isDying)) {
          AbstractDungeon.actionManager.addToBottom(
              new com.megacrit.cardcrawl.actions.common.ApplyPowerAction(m, this.owner,
                  new VulnerablePower(m, this.amount, false), this.amount));
        }
      }
    }
  }
}