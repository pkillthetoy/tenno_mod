package tenno_mod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import tenno_mod.patches.AbstractCardEnum;

public class BulletJump_TENNO extends CustomCard {
    public static final String ID = "BulletJump_TENNO";
    //private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = "Bullet Jump";
    public static final String DESCRIPTION = "Gain !B! Block. Apply !M! Vulnerable.";
    public static final String IMG_PATH = "img/cards/Beta.png";
    private static final int COST = 1;
    private static final int UPG_COST = 0;
    private static final int BLOCK_AMT = 6;
    private static final int MAGIC_NUMBER = 1;

    public BulletJump_TENNO() {
        super(
                ID,
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.SKILL,
                AbstractCardEnum.TENNO_COLOR,
                CardRarity.BASIC,
                CardTarget.SELF_AND_ENEMY
        );
        this.magicNumber = this.baseMagicNumber = MAGIC_NUMBER;
        this.baseBlock = BLOCK_AMT;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new GainBlockAction(p, p, this.block)
        );
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(
                        m,
                        p,
                        new VulnerablePower(m, this.magicNumber, false),
                        this.magicNumber,
                        true,
                        AbstractGameAction.AttackEffect.NONE)
        );
    }

    public AbstractCard makeCopy() {
        return new BulletJump_TENNO();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(UPG_COST);
        }
    }
}
