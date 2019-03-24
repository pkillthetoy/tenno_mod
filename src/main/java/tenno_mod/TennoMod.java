package tenno_mod;

import static tenno_mod.patches.AbstractCardEnum.TENNO_COLOR;
import static tenno_mod.patches.AbstractCardEnum.TENNO_GENERATED;

import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tenno_mod.cards.BulletJump_TENNO;
import tenno_mod.cards.Defend_TENNO;
import tenno_mod.cards.MaimingStrike_TENNO;
import tenno_mod.cards.Strike_TENNO;
import tenno_mod.characters.Tenno;
import tenno_mod.patches.AbstractCardEnum;
import tenno_mod.patches.TennoPlayerClassEnum;
import tenno_mod.relics.*;

import java.awt.print.Paper;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


@SpireInitializer
public class TennoMod implements EditCharactersSubscriber, EditCardsSubscriber, EditRelicsSubscriber, EditStringsSubscriber, PostPowerApplySubscriber {
    private static final String MY_CHARACTER_BUTTON = "img/charSelect/TennoButton.png";
    private static final String MARISA_PORTRAIT = "img/charSelect/Excalibur.png";

    private ArrayList<AbstractCard> cardsToAdd = new ArrayList<>();

    public static final Logger logger = LogManager.getLogger(TennoMod.class.getName());

    public TennoMod() {
        BaseMod.subscribe(this);
        logger.info("creating color TENNO_COLOR");
        BaseMod.addColor(TENNO_COLOR,
                Color.GOLD, Color.GOLD, Color.GOLD, Color.GOLD, Color.GOLD, Color.GOLD, Color.GOLD,
                "img/512/bg_attack_MRS_s.png", "img/512/bg_skill_MRS_s.png", "img/512/bg_power_MRS_s.png",
                "img/512/card_orb.png",
                "img/1024/bg_attack_MRS.png", "img/1024/bg_skill_MRS.png", "img/1024/bg_power_MRS.png",
                "img/1024/card_orb.png", "img/UI/energyOrb.png");
        BaseMod.addColor(TENNO_GENERATED,
                Color.GOLD, Color.GOLD, Color.GOLD, Color.GOLD, Color.GOLD, Color.GOLD, Color.GOLD,
                "img/512/bg_attack_MRS_s.png", "img/512/bg_skill_MRS_s.png", "img/512/bg_power_MRS_s.png",
                "img/512/card_orb.png",
                "img/1024/bg_attack_MRS.png", "img/1024/bg_skill_MRS.png", "img/1024/bg_power_MRS.png",
                "img/1024/card_orb.png", "img/UI/energyOrb.png");

    }

    public static void initialize() {
        new TennoMod();
    }

    @Override
    public void receiveEditCharacters() {
        logger.info("adding " + TennoPlayerClassEnum.TENNO);
        BaseMod.addCharacter(new Tenno("Tenno"), MY_CHARACTER_BUTTON, MARISA_PORTRAIT, TennoPlayerClassEnum.TENNO);

    }

    public void loadCardsToAdd() {
        cardsToAdd.clear();
        cardsToAdd.add(new Strike_TENNO());
        cardsToAdd.add(new Defend_TENNO());
        cardsToAdd.add(new BulletJump_TENNO());
        cardsToAdd.add(new MaimingStrike_TENNO());
    }

    @Override
    public void receiveEditCards() {

        logger.info("starting editing cards");

        loadCardsToAdd();

        logger.info("adding cards for MARISA");

        for (AbstractCard card : cardsToAdd) {
            logger.info("Adding card : " + card.name);
            BaseMod.addCard(card);
        }

        logger.info("done editing cards");
    }

    @Override
    public void receiveEditRelics() {
        BaseMod.addRelicToCustomPool(new Nikana_TENNO(), TENNO_COLOR);
        BaseMod.addRelicToCustomPool(new DragonNikana_TENNO(), TENNO_COLOR);
        BaseMod.addRelicToCustomPool(new PaperLotus_TENNO(), TENNO_COLOR);
        BaseMod.addRelicToCustomPool(new RaktaDarkDagger_TENNO(), TENNO_COLOR);
        BaseMod.addRelicToCustomPool(new TwinGrakata_TENNO(), TENNO_COLOR);
        BaseMod.addRelicToCustomPool(new ArgonCrystal_TENNO(), TENNO_COLOR);
        BaseMod.addRelicToCustomPool(new Skiajati_TENNO(), TENNO_COLOR);
    }

    @Override
    public void receiveEditStrings() {

        String relicStrings = Gdx.files.internal("localization/Tenno_relics.json").readString(
                String.valueOf(StandardCharsets.UTF_8)
        );
        logger.info(relicStrings);
        BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
    }


    @Override
    public void receivePostPowerApplySubscriber(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        AbstractPlayer p = AbstractDungeon.player;
        if ((power.type == AbstractPower.PowerType.DEBUFF) &&
            (!power.ID.equals("Shackled")) &&
            (source == p) && (target != p) &&
            (!target.hasPower("Artifact"))) {
            if (p.hasRelic(PaperLotus_TENNO.ID)) {
                p.getRelic(PaperLotus_TENNO.ID).onTrigger(target);
            }
        }

    }
}
