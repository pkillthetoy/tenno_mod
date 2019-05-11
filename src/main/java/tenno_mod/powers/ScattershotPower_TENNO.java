package tenno_mod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import tenno_mod.actions.ScattershotDamageAction;

public class ScattershotPower_TENNO extends AbstractPower {
  public static final String POWER_ID = "ScattershotPower_TENNO";
  private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
  public static final String NAME = powerStrings.NAME;
  public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


  public ScattershotPower_TENNO(AbstractCreature owner, int amount) {
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

  public void atEndOfTurn(boolean isPlayer) {
    if (isPlayer) {

      AbstractDungeon.actionManager.addToBottom(
          new ScattershotDamageAction(
              AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng),
              new DamageInfo(AbstractDungeon.player, this.amount, DamageInfo.DamageType.THORNS),
              AbstractDungeon.player.hand.size()));

    }
  }

}
