package tenno_mod;

import basemod.BaseMod;
import basemod.interfaces.EditCharactersSubscriber;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import tenno_mod.patches.AbstractCardEnum;

@SpireInitializer
public class TennoMod implements EditCharactersSubscriber {
    public TennoMod() {
        BaseMod.subscribe(this);
        logger.info("creating color TENNO_COLOR");
        BaseMod.addColor(AbstractCardEnum.TENNO_COLOR,
                Color.GOLD, Color.GOLD, Color.GOLD, Color.GOLD, Color.GOLD, Color.GOLD, Color.GOLD,
                "img/512/bg_attack_MRS_s.png", "img/512/bg_skill_MRS_s.png", "img/512/bg_power_MRS_s.png",
                "img/512/cardOrb.png",
                "img/1024/bg_attack_MRS.png","img/1024/bg_skill_MRS.png", "img/1024/bg_power_MRS.png",
                "img/1024/cardOrb.png", "img/UI/energyOrb.png")

    }

    public static void initialize(){
        new TennoMod();
    }

    public void receiveEditCharacters() {

    }
}
