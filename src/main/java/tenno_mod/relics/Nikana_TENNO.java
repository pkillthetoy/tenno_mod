package tenno_mod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class Nikana_TENNO extends CustomRelic {
    public static final String ID = "Nikana_TENNO";
    private static final int MAGIC_NUMBER = 1;

    private static final String IMG = "img/relics/Nikana.png";
    private static final String IMG_OTL = "img/relics/outline/Hakkero_s.png";

    public Nikana_TENNO() {
        super(ID,
                ImageMaster.loadImage(IMG),
                ImageMaster.loadImage(IMG_OTL), RelicTier.STARTER, LandingSound.CLINK);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + MAGIC_NUMBER + this.DESCRIPTIONS[1] + MAGIC_NUMBER + this.DESCRIPTIONS[2];
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        AbstractPlayer p = AbstractDungeon.player;
        if (card.type == AbstractCard.CardType.SKILL) {
            flash();
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(
                  p,p, new StrengthPower(p, MAGIC_NUMBER), MAGIC_NUMBER));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(
                    p,p, new LoseStrengthPower(p, MAGIC_NUMBER), MAGIC_NUMBER));
        }
        if (card.type == AbstractCard.CardType.ATTACK) {
            flash();
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(
                    p,p, new DexterityPower(p, MAGIC_NUMBER), MAGIC_NUMBER));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(
                    p,p, new LoseDexterityPower(p, MAGIC_NUMBER), MAGIC_NUMBER));
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new Nikana_TENNO();
    }
}
