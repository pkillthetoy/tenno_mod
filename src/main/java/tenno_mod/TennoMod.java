package tenno_mod;

import basemod.BaseMod;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.Keyword;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tenno_mod.cards.basic.BulletJump_TENNO;
import tenno_mod.cards.basic.Defend_TENNO;
import tenno_mod.cards.basic.MaimingStrike_TENNO;
import tenno_mod.cards.basic.Strike_TENNO;
import tenno_mod.cards.common.*;
import tenno_mod.cards.generated.*;
import tenno_mod.cards.rare.*;
import tenno_mod.cards.uncommon.*;
import tenno_mod.characters.Tenno;
import tenno_mod.patches.TennoPlayerClassEnum;
import tenno_mod.relics.*;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import static tenno_mod.patches.AbstractCardEnum.TENNO_COLOR;
import static tenno_mod.patches.AbstractCardEnum.TENNO_GENERATED;


@SpireInitializer
public class TennoMod implements EditCharactersSubscriber,
    EditCardsSubscriber,
    EditRelicsSubscriber,
    EditStringsSubscriber,
    EditKeywordsSubscriber,
    PostPowerApplySubscriber {
  private static final String MY_CHARACTER_BUTTON = "img/charSelect/TennoButton.png";
  private static final String TENNO_PORTRAIT = "img/charSelect/Excalibur.png";

  private ArrayList<AbstractCard> cardsToAdd = new ArrayList<>();

  public static final Logger logger = LogManager.getLogger(TennoMod.class.getName());

  public TennoMod() {
    BaseMod.subscribe(this);
    logger.info("creating color TENNO_COLOR");
    BaseMod.addColor(TENNO_COLOR,
        Color.GOLD, Color.GOLD, Color.GOLD, Color.GOLD, Color.GOLD, Color.GOLD, Color.GOLD,
        "img/512/bg_attack_TENNO.png", "img/512/bg_skill_TENNO.png", "img/512/bg_power_TENNO.png",
        "img/512/card_orb.png",
        "img/1024/bg_attack_TENNO.png", "img/1024/bg_skill_TENNO.png", "img/1024/bg_power_TENNO.png",
        "img/1024/card_orb.png", "img/UI/energyOrb.png");
    BaseMod.addColor(TENNO_GENERATED,
        Color.GOLD, Color.GOLD, Color.GOLD, Color.GOLD, Color.GOLD, Color.GOLD, Color.GOLD,
        "img/512/bg_attack_TENNO.png", "img/512/bg_skill_TENNO.png", "img/512/bg_power_TENNO.png",
        "img/512/card_orb.png",
        "img/1024/bg_attack_TENNO.png", "img/1024/bg_skill_TENNO.png", "img/1024/bg_power_TENNO.png",
        "img/1024/card_orb.png", "img/UI/energyOrb.png");

  }

  public static void initialize() {
    new TennoMod();
  }

  @Override
  public void receiveEditCharacters() {
    logger.info("adding " + TennoPlayerClassEnum.TENNO);
    BaseMod.addCharacter(new Tenno("Tenno"), MY_CHARACTER_BUTTON, TENNO_PORTRAIT, TennoPlayerClassEnum.TENNO);

  }

  public void loadCardsToAdd() {
    cardsToAdd.clear();
    // Starting deck
    cardsToAdd.add(new Strike_TENNO());
    cardsToAdd.add(new Defend_TENNO());
    cardsToAdd.add(new BulletJump_TENNO());
    cardsToAdd.add(new MaimingStrike_TENNO());

    // Commons
    cardsToAdd.add(new AgileGuard_TENNO());
    cardsToAdd.add(new Aviator_TENNO());
    cardsToAdd.add(new BlindingSlice_TENNO());
    cardsToAdd.add(new BlindRage_TENNO());
    cardsToAdd.add(new CleansingStrike_TENNO());
    cardsToAdd.add(new CombatKnowledge_TENNO());
    cardsToAdd.add(new Cripple_TENNO());
    cardsToAdd.add(new DefensiveThrow_TENNO());
    cardsToAdd.add(new DoubleGuard_TENNO());
    cardsToAdd.add(new ExploitOptions_TENNO());
    cardsToAdd.add(new FlyingShot_TENNO());
    cardsToAdd.add(new HeavySlam_TENNO());
    cardsToAdd.add(new Meditation_TENNO());
    cardsToAdd.add(new PlannedShot_TENNO());
    cardsToAdd.add(new QuickGuard_TENNO());
    cardsToAdd.add(new QuickSlash_TENNO());
    cardsToAdd.add(new ReturnStroke_TENNO());
    cardsToAdd.add(new VoidStrike_TENNO());

    // Uncommons
    cardsToAdd.add(new BurstStrike_TENNO());
    cardsToAdd.add(new CleavingWhirlwind_TENNO());
    cardsToAdd.add(new ConditionOverload_TENNO());
    cardsToAdd.add(new CorrosiveProjection_TENNO());
    cardsToAdd.add(new DriftingContact_TENNO());
    cardsToAdd.add(new DrinkTheKuva_TENNO());
    cardsToAdd.add(new DualChop_TENNO());
    cardsToAdd.add(new Duplication_TENNO());
    cardsToAdd.add(new EnergyOrb_TENNO());
    cardsToAdd.add(new ExpertTechnique_TENNO());
    cardsToAdd.add(new FatalTeleport_TENNO());
    cardsToAdd.add(new FinishingTouch_TENNO());
    cardsToAdd.add(new Flurry_TENNO());
    cardsToAdd.add(new HunterAdrenaline_TENNO());
    cardsToAdd.add(new Gut_TENNO());
    cardsToAdd.add(new LashOut_TENNO());
    cardsToAdd.add(new NotMachineLearning_TENNO());
    cardsToAdd.add(new Parkour_TENNO());
    cardsToAdd.add(new PlanAhead_TENNO());
    cardsToAdd.add(new PurgeCorruption_TENNO());
    cardsToAdd.add(new RadiantJavelins_TENNO());
    cardsToAdd.add(new Reserves_TENNO());
    cardsToAdd.add(new Reposition_TENNO());
    cardsToAdd.add(new Scattershot_TENNO());
    cardsToAdd.add(new SelfImprovement_TENNO());
    cardsToAdd.add(new Snipe_TENNO());
    cardsToAdd.add(new TapDodge_TENNO());
    cardsToAdd.add(new TransferenceBeam_TENNO());
    cardsToAdd.add(new Versatility_TENNO());
    cardsToAdd.add(new VoidAttunement_TENNO());
    cardsToAdd.add(new VoidBlast_TENNO());
    cardsToAdd.add(new VoidDash_TENNO());
    cardsToAdd.add(new VoidGuard_TENNO());
    cardsToAdd.add(new VoidIgnition_TENNO());
    cardsToAdd.add(new VoidKnowledge_TENNO());

    // Rares
    cardsToAdd.add(new Cataclysm_TENNO());
    cardsToAdd.add(new ChromaticBlade_TENNO());
    cardsToAdd.add(new ConcealedWeapons_TENNO());
    cardsToAdd.add(new CovertLethality_TENNO());
    cardsToAdd.add(new DailyLogin_TENNO());
    cardsToAdd.add(new EmbraceTheVoid_TENNO());
    cardsToAdd.add(new ExaltedBlade_TENNO());
    cardsToAdd.add(new GrowingPower_TENNO());
    cardsToAdd.add(new LinkedNarta_TENNO());
    cardsToAdd.add(new MasterfulCombination_TENNO());
    cardsToAdd.add(new QuickThinking_TENNO());
    cardsToAdd.add(new Resourcefulness_TENNO());
    cardsToAdd.add(new RollingGuard_TENNO());
    cardsToAdd.add(new NinjaSkills_TENNO());
    cardsToAdd.add(new UmbralForm_TENNO());
    cardsToAdd.add(new VoidCorruption_TENNO());
    cardsToAdd.add(new VoidRadiance_TENNO());
    cardsToAdd.add(new WildFrenzy_TENNO());

    // Generated cards
    cardsToAdd.add(new UmbralFiber_TENNO());
    cardsToAdd.add(new UmbralIntensify_TENNO());
    cardsToAdd.add(new UmbralHowl_TENNO());


  }

  @Override
  public void receiveEditCards() {

    logger.info("starting editing cards");

    loadCardsToAdd();

    logger.info("adding cards for TENNO");

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
    BaseMod.addRelicToCustomPool(new JanusKey_TENNO(), TENNO_COLOR);
    BaseMod.addRelicToCustomPool(new PolymerBundle_TENNO(), TENNO_COLOR);
    BaseMod.addRelicToCustomPool(new MasteryBadge_TENNO(), TENNO_COLOR);
    BaseMod.addRelicToCustomPool(new Kuva_TENNO(), TENNO_COLOR);

  }

  @Override
  public void receiveEditStrings() {
    String relicStrings = loadJson("localization/Tenno_relics.json");
    BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);

    String cardStrings = loadJson("localization/Tenno_cards.json");
    BaseMod.loadCustomStrings(CardStrings.class, cardStrings);

    String powerStrings = loadJson("localization/Tenno_powers.json");
    BaseMod.loadCustomStrings(PowerStrings.class, powerStrings);
  }

  @Override
  public void receivePostPowerApplySubscriber(AbstractPower power, AbstractCreature target, AbstractCreature source) {
    AbstractPlayer p = AbstractDungeon.player;
    if (power.type == AbstractPower.PowerType.DEBUFF &&
        !power.ID.equals("Shackled") &&
        source == p && target != p &&
        !target.hasPower("Artifact")) {
      if (p.hasRelic(PaperLotus_TENNO.ID)) {
        p.getRelic(PaperLotus_TENNO.ID).onTrigger(target);
      }
    }
  }

  private static String loadJson(String jsonPath) {
    return Gdx.files.internal(jsonPath).readString(String.valueOf(StandardCharsets.UTF_8));
  }

  @Override
  public void receiveEditKeywords() {
    logger.info("Setting up custom keywords");

    String keywordsPath = "localization/Tenno_keywords.json";
    Gson gson = new Gson();
    Keywords keywords;
    keywords = gson.fromJson(loadJson(keywordsPath), Keywords.class);
    for (Keyword key : keywords.keywords) {
      logger.info("Loading keyword : " + key.NAMES[0]);
      BaseMod.addKeyword(key.NAMES, key.DESCRIPTION);
    }
    logger.info("Keywords setting finished.");

  }

  class Keywords {
    Keyword[] keywords;
  }
}
