package tenno_mod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.List;

// Credit to Marisa mod. This is more satisfying than just SuicideAction.
public class CullingAction extends AbstractGameAction {

  private AbstractMonster target;
  public CullingAction(AbstractMonster target) {
    this.target = target;
  }

  public void update() {
    if (target.hasPower("Intangible")) {
      AbstractPower removeMe = target.getPower("Intangible");
      removeMe.onRemove();
      target.powers.remove(removeMe);
      AbstractDungeon.onModifyPower();
    }
    if (target.hasPower("IntangiblePlayer")) {

      AbstractPower removeMe = target.getPower("IntangiblePlayer");
      removeMe.onRemove();
      target.powers.remove(removeMe);
      AbstractDungeon.onModifyPower();
    }
    target.damage(
        new DamageInfo(
            AbstractDungeon.player,
            Integer.MAX_VALUE,
            DamageInfo.DamageType.HP_LOSS
        )
    );
    this.isDone = true;
  }
}
