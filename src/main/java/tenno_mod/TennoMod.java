package tenno_mod;

import basemod.BaseMod;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.cards.AbstractCard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tenno_mod.cards.Strike_TENNO;
import tenno_mod.patches.AbstractCardEnum;

import java.util.ArrayList;


@SpireInitializer
public class TennoMod implements EditCharactersSubscriber, EditCardsSubscriber {
    private ArrayList<AbstractCard> cardsToAdd = new ArrayList<>();

    public static final Logger logger = LogManager.getLogger(TennoMod.class.getName());

    public TennoMod() {
        BaseMod.subscribe(this);
        logger.info("creating color TENNO_COLOR");
        BaseMod.addColor(AbstractCardEnum.TENNO_COLOR,
                Color.GOLD, Color.GOLD, Color.GOLD, Color.GOLD, Color.GOLD, Color.GOLD, Color.GOLD,
                "img/512/bg_attack_MRS_s.png", "img/512/bg_skill_MRS_s.png", "img/512/bg_power_MRS_s.png",
                "img/512/cardOrb.png",
                "img/1024/bg_attack_MRS.png","img/1024/bg_skill_MRS.png", "img/1024/bg_power_MRS.png",
                "img/1024/cardOrb.png", "img/UI/energyOrb.png");

    }

    public static void initialize(){
        new TennoMod();
    }

    @Override
    public void receiveEditCharacters() {

    }

    public void loadCardsToAdd() {
        cardsToAdd.clear();
        cardsToAdd.add(new Strike_TENNO());
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
    }
}
