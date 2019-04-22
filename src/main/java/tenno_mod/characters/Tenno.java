package tenno_mod.characters;

import basemod.abstracts.CustomPlayer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tenno_mod.TennoMod;
import tenno_mod.patches.AbstractCardEnum;
import tenno_mod.patches.TennoPlayerClassEnum;
import tenno_mod.relics.Nikana_TENNO;

import java.util.ArrayList;

public class Tenno extends CustomPlayer {
  private static final int ENERGY_PER_TURN = 3;

  private static final String TENNO_MAIN = "img/char/Marisa/tennoMain.png"; // main image
  private static final String TENNO_SHOULDER_2 = "img/char/Marisa/shoulder.png"; // shoulder2 / shoulder_1
  private static final String TENNO_SHOULDER_1 = "img/char/Marisa/shoulder.png"; // shoulder1 / shoulder_2
  private static final String TENNO_CORPSE = "img/char/Marisa/tennoMain.png"; // dead corpse
  public static final Logger logger = LogManager.getLogger(TennoMod.class.getName());
  //private static final float[] layerSpeeds = { 20.0F, 0.0F, -40.0F, 0.0F, 0.0F, 5.0F, 0.0F, -8.0F, 0.0F, 8.0F };
  private static final String MARISA_SKELETON_ATLAS = "img/char/Marisa/MarisaModelv3.atlas";// Marisa_v0 / MarisaModel_v02 /MarisaModelv3
  private static final String MARISA_SKELETON_JSON = "img/char/Marisa/MarisaModelv3.json";
  private static final String MARISA_ANIMATION = "Idle";// Sprite / Idle
  private static final String[] ORB_TEXTURES = {
      "img/UI/EPanel/layer5.png",
      "img/UI/EPanel/layer4.png",
      "img/UI/EPanel/layer3.png",
      "img/UI/EPanel/layer2.png",
      "img/UI/EPanel/layer1.png",
      "img/UI/EPanel/layer0.png",
      "img/UI/EPanel/layer5d.png",
      "img/UI/EPanel/layer4d.png",
      "img/UI/EPanel/layer3d.png",
      "img/UI/EPanel/layer2d.png",
      "img/UI/EPanel/layer1d.png"
  };
  private static final String ORB_VFX = "img/UI/energyBlueVFX.png";
  private static final float[] LAYER_SPEED =
      {-40.0F, -32.0F, 20.0F, -20.0F, 0.0F, -10.0F, -8.0F, 5.0F, -5.0F, 0.0F};

  public Tenno(String name) {
    super(name, TennoPlayerClassEnum.TENNO, ORB_TEXTURES, ORB_VFX, LAYER_SPEED, null, null);
    this.dialogX = (this.drawX + 0.0F * Settings.scale); // set location for text bubbles
    this.dialogY = (this.drawY + 220.0F * Settings.scale); // you can just copy these values

    logger.info("init Marisa");


    // TODO: Use non-marisa images and animations.
    initializeClass(
        TENNO_MAIN,
        TENNO_SHOULDER_2, // required call to load textures and setup energy/loadout
        TENNO_SHOULDER_1,
        TENNO_CORPSE,
        getLoadout(),
        20.0F, -10.0F, 220.0F, 290.0F,
        new EnergyManager(ENERGY_PER_TURN)
    );

    logger.info("init finish");

  }


  public ArrayList<String> getStartingDeck() { // starting deck 'nuff said
    ArrayList<String> retVal = new ArrayList<>();
    retVal.add("Strike_TENNO");
    retVal.add("Strike_TENNO");
    retVal.add("Strike_TENNO");
    retVal.add("Strike_TENNO");
    retVal.add("Defend_TENNO");
    retVal.add("Defend_TENNO");
    retVal.add("Defend_TENNO");
    retVal.add("Defend_TENNO");
    retVal.add("BulletJump_TENNO");
    retVal.add("MaimingStrike_TENNO");
    return retVal;
  }

  public ArrayList<String> getStartingRelics() { // starting relics - also simple
    ArrayList<String> retVal = new ArrayList<>();
    retVal.add(Nikana_TENNO.ID);
    UnlockTracker.markRelicAsSeen(Nikana_TENNO.ID);
    return retVal;
  }

  private static final int STARTING_HP = 75;
  private static final int MAX_HP = 75;
  private static final int STARTING_GOLD = 99;
  private static final int HAND_SIZE = 5;
  private static final int ASCENSION_MAX_HP_LOSS = 5;

  public CharSelectInfo getLoadout() { // the rest of the character loadout so includes your character select screen info plus hp and starting gold
    String title = "The Tenno";
    String flavor = "A warrior from the void of space. Dismantle enemies with gun and blade.";
    return new CharSelectInfo(
        title,
        flavor,
        STARTING_HP,
        MAX_HP,
        0,
        STARTING_GOLD,
        HAND_SIZE,
        this,
        getStartingRelics(),
        getStartingDeck(),
        false
    );
  }

  public AbstractCard.CardColor getCardColor() {
    return AbstractCardEnum.TENNO_COLOR;
  }

  @Override
  public Color getCardRenderColor() {
    return Color.GOLD;
  }

  @Override
  public AbstractCard getStartCardForEvent() {
    return null;
  }

  public String getTitle(PlayerClass playerClass) {
    return "The Tenno";
  }

  public Color getCardTrailColor() {
    return Color.GOLD;
  }

  public int getAscensionMaxHPLoss() {
    return ASCENSION_MAX_HP_LOSS;
  }

  @Override
  public BitmapFont getEnergyNumFont() {
    return FontHelper.energyNumFontBlue;
  }

  public void updateOrb(int orbCount) {
    this.energyOrb.updateOrb(orbCount);
  }

  @Override
  public void doCharSelectScreenSelectEffect() {
    CardCrawlGame.sound.playA("ATTACK_HEAVY", MathUtils.random(-0.2F, 0.2F));
    CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, true);
  }

  @Override
  public String getCustomModeCharacterButtonSoundKey() {
    return "SELECT_TENNO";
  }

  @Override
  public String getLocalizedCharacterName() {
    return "The Tenno";
  }

  @Override
  public AbstractPlayer newInstance() {
    return new Tenno(this.name);
  }

  public Color getSlashAttackColor() {
    return Color.GOLD;
  }

  @Override
  public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
    return new AttackEffect[]{
        AttackEffect.SLASH_HEAVY,
        AttackEffect.FIRE,
        AttackEffect.SLASH_DIAGONAL,
        AttackEffect.SLASH_HEAVY,
        AttackEffect.FIRE,
        AttackEffect.SLASH_DIAGONAL
    };
  }

  @Override
  public String getVampireText() {
    return com.megacrit.cardcrawl.events.city.Vampires.DESCRIPTIONS[1];
  }

  public TextureAtlas.AtlasRegion getOrb() {
    return new TextureAtlas.AtlasRegion(ImageMaster.loadImage("img/UI/energyOrb.png"), 0, 0, 24, 24);
  }

  @Override
  public String getSpireHeartText() {
    return com.megacrit.cardcrawl.events.beyond.SpireHeart.DESCRIPTIONS[10];
  }
}
