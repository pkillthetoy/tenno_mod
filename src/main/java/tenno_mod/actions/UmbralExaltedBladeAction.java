package tenno_mod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class UmbralExaltedBladeAction extends AbstractGameAction {

  AbstractPlayer p;
  AbstractCreature m;
  AbstractCard itself;

  public UmbralExaltedBladeAction(AbstractPlayer p, AbstractCreature m, AbstractCard itself) {
    this.p = p;
    this.m = m;
    this.itself = itself;
  }

  public void update() {

    AbstractDungeon.actionManager.addToBottom(
        new DamageAction(
            m,
            new DamageInfo(p, itself.damage, itself.damageTypeForTurn),
            AbstractGameAction.AttackEffect.SLASH_VERTICAL
        )
    );
    AbstractDungeon.actionManager.addToBottom(
        new DamageAllEnemiesAction(p, itself.multiDamage, itself.damageTypeForTurn,
            AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    AbstractDungeon.actionManager.addToBottom(new ReturnToHandAction(itself));
    this.isDone = true;
  }
}
