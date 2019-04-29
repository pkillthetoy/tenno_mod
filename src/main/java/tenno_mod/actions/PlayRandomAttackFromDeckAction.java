package tenno_mod.actions;

import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class PlayRandomAttackFromDeckAction extends com.megacrit.cardcrawl.actions.AbstractGameAction {
  private AbstractPlayer p;

  public PlayRandomAttackFromDeckAction(com.megacrit.cardcrawl.core.AbstractCreature target) {
    this.p = AbstractDungeon.player;
    this.duration = Settings.ACTION_DUR_FAST;
    this.actionType = com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType.WAIT;
    this.source = AbstractDungeon.player;
    this.target = target;
  }

  public void update() {
    if (this.duration == Settings.ACTION_DUR_FAST) {

      if (this.p.drawPile.isEmpty()) {
        this.isDone = true;
        return;
      }

      CardGroup tmp = new CardGroup(com.megacrit.cardcrawl.cards.CardGroup.CardGroupType.UNSPECIFIED);
      for (AbstractCard c : this.p.drawPile.group) {
        if (c.type == AbstractCard.CardType.ATTACK) {
          tmp.addToBottom(c);
        }
      }

      if (tmp.size() == 0) {
        this.isDone = true;
        return;
      }

      tmp.shuffle();
      AbstractCard card = tmp.getBottomCard();
      AbstractDungeon.player.drawPile.group.remove(card);
      AbstractDungeon.getCurrRoom().souls.remove(card);
      card.freeToPlayOnce = true;
      AbstractDungeon.player.limbo.group.add(card);
      card.current_y = (-200.0F * Settings.scale);
      card.target_x = (Settings.WIDTH / 2.0F + 200.0F * Settings.scale);
      card.target_y = (Settings.HEIGHT / 2.0F);
      card.targetAngle = 0.0F;
      card.lighten(false);
      card.drawScale = 0.12F;
      card.targetDrawScale = 0.75F;

      if (!card.canUse(AbstractDungeon.player, (com.megacrit.cardcrawl.monsters.AbstractMonster) this.target)) {
        AbstractDungeon.actionManager.addToTop(new com.megacrit.cardcrawl.actions.utility.UnlimboAction(card));
        AbstractDungeon.actionManager.addToTop(new DiscardSpecificCardAction(card, AbstractDungeon.player.limbo));

        AbstractDungeon.actionManager.addToTop(new WaitAction(0.4F));

      } else {
        card.applyPowers();
        AbstractDungeon.actionManager.addToTop(
            new com.megacrit.cardcrawl.actions.utility.QueueCardAction(card, this.target));
        AbstractDungeon.actionManager.addToTop(new com.megacrit.cardcrawl.actions.utility.UnlimboAction(card));
        if (!Settings.FAST_MODE) {
          AbstractDungeon.actionManager.addToTop(new WaitAction(Settings.ACTION_DUR_MED));
        } else {
          AbstractDungeon.actionManager.addToTop(new WaitAction(Settings.ACTION_DUR_FASTER));
        }
      }
    }
    this.isDone = true;
  }
}

