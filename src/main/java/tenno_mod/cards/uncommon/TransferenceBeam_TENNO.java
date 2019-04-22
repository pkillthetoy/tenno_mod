package tenno_mod.cards.uncommon;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;
import tenno_mod.actions.TransferrenceBeamAction;
import tenno_mod.patches.AbstractCardEnum;

public class TransferenceBeam_TENNO extends CustomCard {
  public static final String ID = "TransferenceBeam_TENNO";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String IMG_PATH = "img/cards/Beta.png";
  private static final int ATTACK_DMG = 6;
  private static final int UPG_ATTACK_DAMAGE = 3;

  public TransferenceBeam_TENNO() {
    super(
        ID,
        NAME,
        IMG_PATH,
        -1,
        DESCRIPTION,
        CardType.ATTACK,
        AbstractCardEnum.TENNO_COLOR,
        CardRarity.UNCOMMON,
        CardTarget.ENEMY
    );
    this.baseDamage = ATTACK_DMG;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_HEAVY"));

    AbstractDungeon.actionManager.addToBottom(
        new VFXAction(p, new MindblastEffect(p.dialogX, p.dialogY, p.flipHorizontal),
            0.1F));
    if (this.energyOnUse < EnergyPanel.totalCount) {
      this.energyOnUse = EnergyPanel.totalCount;
    }

    AbstractDungeon.actionManager.addToBottom(
        new TransferrenceBeamAction(p, m, this.damage, this.damageTypeForTurn,
            this.freeToPlayOnce, this.energyOnUse));
  }

  public AbstractCard makeCopy() {
    return new TransferenceBeam_TENNO();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeDamage(UPG_ATTACK_DAMAGE);
    }
  }
}
