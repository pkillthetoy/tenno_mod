### 5/14/2019 - 0.2.1.3

* Added 3 more relic images - Dragon Nikana, Kuva, Twin Grakata.
* Fixed an issue where Parkour's upgraded text wasn't listed properly.

I still don't know what's causing the reported audio crash - I haven't been able to replicate it myself
(yet), and I'm not sure what in this mod might be causing it. Will investigate more on the weekend.

### 5/12/2019 - 0.2.1.2

In which I learn more about Paint.net than I ever wanted.

* Removed some (not all) assets copied from Marisa mod.
* Tenno now uses Ironclad's energy orbs.
* Added non-beta art for Nikana.

### 5/11/2019 - 0.2.1.1

Oops.

* Added Parkour, for real.
* Fixed Drifting Contact using the wrong image.
* Tenno's color is now Yellow instead of Gold to avoid this weird issue with potions turning gray.
* Tweaked Defensive Throw so it doesn't count dead enemies.
* Defensive Throw now properly has the multidamage tag set.

### 5/11/2019 - 0.2.1

Lots of changes.

New cards:

* New card: Gut. 1 cost uncommon attack. 5(7) damage, draw a card for each debuff on the target.
* New card: Parkour. 1 cost uncommon power. Draw 1(2) cards when you play a card with reduced cost.
* New card: Reserves. Unplayable uncommon Skill. Draw 1(2) cards when drawn.

Reworked cards:
 
* Falling Kick now deals damage equal to cards in hand (+3). Internally it's still Exploit options.
Surely this will not cause any sort of confusion.
* Finishing Touch now deals 4(6) damage per skill played this turn.
* Void Blast now deals 18(24) damage and costs 1 less per void exhausted this combat. 

Other card changes: 
* Embrace the Void now always adds 3 voids but gives 1(2) strength.
* Growing Power always has 2 cost but gives 1(2) strength.
* Tenno Mastery no longer upgrades to innate but gives 1(2) strength/dex.
* Drink the Kuva nerfed to only fill up to 6(8) hand size.
* Scattershot buffed to 2(3) damage.
* Void Ignition buffed to 5(8) damage.
* Umbral Fiber/Howl/Intensify changed to rare rarity just to make them look nicer.
* Standardized the "debuff matters" cards to not count the Shackled power, similar to how the base game does it.
This does mean Condition Overload and Gut will effectively lose 1 from their count if you use a strength reduction
card on something that too much strength, but it's kind of an edge case?

### 5/10/2019 - 0.2.0.2

Bugfixes: 

Fixed Cataclysm's Intangible not stacking properly on enemies.

Removed Split chamber. Added Tenno Mastery. 2 cost rare power. 3 attacks gives +1 dex, 3 skills gives +1 strength. 
Upgrades to innate.

Reduced Growing Power cost from 3(2) to 2(1). Tenno has some temporary stat boosts built in, but currently he struggles
to even match an Inflame in terms of more permanent scaling. 

Potential upcoming changes:

* Hallowed Ground is clunky and likely to be replaced.
* Void Blast is filler and likely to be replaced. 
* Finishing Touch will become Finisher (like Silent), but for skills.
* Add a Void payoff card that reduces its cost (similar to current Finishing Touch)
* Add more card draw to support the cost reduction subtheme.
* Add payoffs for having lots of card draw.

Balance notes:

* Linked Narta is a dream to play. Very happy with it.
* Scaling is overall very slow. The fastest scaling methods available are Embrace the Void + Drink the Kuva,
and Growing Power + Chromatic blade. But these take a lot of effort and still don't match the scaling available to
other classes. Might just double the stat gain on these rare powers.  

### 5/9/2019 - 0.2.0.1

Bugfixes: 

* Fixed Cataclysm making enemies permanently intangible.
* Fixed Exalted Blade being at uncommon instead of rare.
* Fixed Split Chamber's description.
* Fixed Condition Overload, Void Blast, and Finishing Touch being at common instead of uncommon.
* Fixed Linked Narta's draw staying forever. Also Fixed Linked Narta not drawing the correct number of cards in multiples.
* Fixed Versatility not granting dexterity.

Balance changes: 

* Made Hallowed Ground no longer exhaust. See if this is any good.
* Made Heavy Slam reduce if there was any skill this turn. Tracking the last card played was too buggy.
* Made Drink the Kuva only go up to 7 in hand if unupgraded. Upgraded now exhausts but goes up to full hand.
* Increased Void Ignition damage from 3(4) to 4(6).

### 5/9/2019 - 0.2.0

All cards are now finished! This makes the mod complete from a gameplay perspective - what's left is
visual polish: card/relic/power images, and picking the right VFX for cards.

New cards:

* Drink the Kuva. 1 cost uncommon skill. Fill your hand with Voids Exhaust (doesn't exhaust)
* Lash Out. 1 cost uncommon attack. Deal 5 damage to a random enemy and apply 1 Weak twice (3 times)
* Purge Corruption. 1 cost uncommon skill. Exhaust all voids and gain 4(5) block for each.
* Resourcefulness. 1 cost rare power. Put a random (upgraded) attack and skill in your hand each turn.
* Void Corruption. 1 cost rare power. At the start of your turn, gain 1 energy and put a void in discard.
* Drifting Contact. 1 cost uncommon power. Applying a debuff gains 3(4) block.
* Expert Technique. 1 cost uncommon attack. Do 3(4) damage and 3(4) block. If you have 5 other cards in hand, do it again.
* Finishing Touch. 4 cost uncommon attack. Do 18(24) damage. Costs 1 less for each skill played this turn.
* Breadth of knowledge. 1(0) cost uncommon power. Draw an extra card each turn.
* Plan Ahead. 1 cost uncommon skill. Gain 8(11) block. Draw 1(2) card(s) and put a card from hand on top of deck.
* Purge Corruption. 1 cost uncommon skill. Exhaust all Voids and gain 4(5) block for each.
* Void Attunement. 1 cost uncommon power. When you draw a void, gain 1(2) energy and draw 1(2) cards.
* Void Guard. 1 cost uncommon skill. Gain 16(20) block and add a void to discard.
* Void Knowledge. 0 cost uncommon skill. Draw 3(4) cards and add a void to discard. Exhaust.
* Void Knowledge. 1 cost uncommon power. Whenever you exhaust a void, deal 3(4) to all enemies. 

Bug fixes:

* Fixed a bunch of cards being improperly marked as basic - Blind Rage, Cripple, Double Guard
* Unupgraded Return Stroke now exhausts properly.

Card changes:

* Self Improvement now exhausts.


### 5/5/2019 - 0.1.6

Major changes. All cards have been designed but not yet implemented. All relics are present.  

Added new cards:

* Exploit options. 0 cost common attack, deals damage equal to cards in hand (+3)
* Concealed Weapons. 2 cost rare attack, Gain 4(6) block for each other attack in hand, Deal 4(6) damage for each skill in hand.
* Embrace the Void. 1 cost rare power, whenever you exhaust a void gain 1 Strength, add 3(4) voids to discard.
* Void Blast. 0 cost uncommon attack. Deal 9(12) damage, add a void to discard.
* Scattershot. 1 cost uncommon power. At end of turn, deal 1(2) damage to a random enemy for each card in hand.
*  

Added relic:
 
* Kuva. When you exhaust a Void, heal for 2. 

Changed Relic:

* Mastery Badge no longer reduces all retained cards, just the one you picked.

Changed cards:

* Cleaving Whirlwind. Adds a copy of itself that costs 0, instead of returning itself to hand.
* Exalted blade. Re-enabled. Deals 5(7) damage 3 times. 
* Split Chamber. Re-enabled. Changed to duplicate all cards with reduced cost instead of 0 cost.
* Corrosive Protection. Nerfed to apply vulnerable to a random enemy instead of all enemies.
* Combat Knowledge: Applies block first. In practice this is a nerf of 1 block for clarity.
* Maiming Strike and Bullet Jump upgrade to apply 2 weak/vulnerable and +3 damage. Energy cost no longer changes. 

Tweaks;

* Flurry and Reposition now use the hand size constant instead of 


### 4/28/2019 - 0.1.5.0

Made enough changes that I consider this another increment.

This version has 18 commons (same as defect) and all necessary relics. 

Added new cards:

* Blind Rage: Common, 1(0) cost, play a random attack from your deck.
* Cripple: Common, 1 cost, apply 2(3) weak and vulnerable.
* Double Guard: Common, 1 cost, gain 4(6) block twice.
* Return Stroke: Common, 1 cost, deal 3 damage, return a card from discard to hand, exhaust (upgrade does not exhaust).
* Duplication: Uncommon, 1 cost, make a copy of a card in your hand. It costs 1 less.
* Hallowed Ground: Uncommon, 2 cost, gain 13(17) block and 1(2) Artifact. Exhaust. 

Added/changed relics:

* Added Mastery badge: rare, retain a card each turn, and any retained cards cost 1 less next turn. (E.g. if you get Runic
  pyramid or get retain effects from another class)
* Changed Polymer Bundle: gain block equal to your hand size at the end of your turn.
* Changed Twin Grakata: reduced to uncommon, only copies the first 0-cost card you play each turn.
  
Other:

* Cleaned up draw-and-reduce actions like those on Flurry, Self Improvement, and Reposition.
* Clarified description for Hunter Adrenaline.
* Shortened the flavor of Janus Key. 

### 4/25/2019 - 0.1.4.2

Added new cards:

* Flying Shot: Common, 1 cost, do 8(11) damage, reduce the cost of a random skill by 1.
* Combat Knowledge: Common, 1 cost, Draw 2 cards, gain block equal to the number of cards in your hand (+3).
* Linked Narta: Rare, 1(0) cost, whenever you play a card this turn, draw a card.
* Masterful Combination: Rare, 1(0) cost, Tutor an attack and a skill into your hand. Exhaust.

### 4/24/2019 - 0.1.4.1

Going nuts with the numbers now.

Added new cards:
* Self Improvement: Uncommon, 1 cost, Draw a card, it costs 1 less this combat. Upgrades to 2 cards. 
* Versatility: Uncommon, 1 cost power, Gain 1 Str, Gain 1 Dex, Draw 1(2) card(s).
* Falling Kick: Common, 1 cost attack, Deal 6(9) damage, draw 2 cards. Yes, this upgrades into Pommel Strike.

Cleaned up some actions to use addToTop whenever applicable. This should clean up some awkward pauses or strange
ordering of effects (e.g. Nikana could gain stats before a card's effects completed) but shouldn't have
any gameplay effects.

Fixed Quick Guard not appearing.

### 4/23/2019 - 0.1.4

Added art to differentiate the three card types. 

### 4/20/2019 - 0.1.3

This changelog exists now.


Buffs to most commons.
* Agile Guard: Cost 2 -> 1
* Aviator: Block 8 -> 10
* Blinding Slice: Damage 7 -> 9
* Cleansing Strike: Upgrading now gives +3 damage, and exhausts an additional void (instead of looking draw and
discard and exhausting one from each)
* Heavy Slam: Damage 12 -> 14
* Meditation: Block 8 -> 9. Upgrading now gives +3 block, and exhausts an additional void (same as Cleansing Strike).
* Planned Shot: Damage 9 -> 11. Renamed to Spray and Pray.
* Void Strike: Damage 14(18) -> 14(19).

Other numbers buffs: 
* Flurry: Damage 8 -> 9.
* Reposition: Block 7 -> 8. 
* Cleaving Whirlwind: Damage 9 -> 10.
* Void Radiance: Damage 7(9) -> 16(21). Card needed a big buff, let's see if this is reasonable.
* Wild Frenzy: Energy 3(2) -> 2(1). 
* Radiant Javelin: Base damage 8 -> 14. Damage per void 2(3) -> 6(9). Also now exhausts a void.
* Condition Overload: Energy 2 -> 1, damage per debuff 3(4) -> 4(6). Description also specifies unique debuffs.

Other card changes:
* Quick Slice Reworked. 0 energy, 2 damage, draw a card. Upgrades to 4 damage.
* Covert lethality reworked. Increased to 2 energy, damage increased to 16(22). Kind of disgusting when it works, lackluster otherwise.
* Burst Strike reworked. Decreased to 1 energy, damage decreased to 5(7).
* Tap Dodge reworked. Decreased to 1 energy, block decreased to 4(6).
* Lethal Torrent reworked. 1 energy, deal Yea1 damage 4(5) times.
* Chromatic Blade no longer applies poison. Card lists the possible effects.

New cards:
* Added Defensive Throw. Common, 1 energy, deal 6(9) to all enemies and gain 3(4) block per enemy.
* Added Quick Guard. Common, 0 energy, 1 block, draw a card. Upgrades to 3 block.
* Added Dual Chop. Uncommon, 2 energy, hit for 10(13) twice.
* Added Transference Beam. Uncommon, X Energy, 6(9) damage X times, apply X Weak and Vulnerable.

Bugs:
* Fixed a display bug with Energy Orb
* Fixed Rolling Guard not retaining past 1 turn.
* Fixed a typo in Polymer Bundle.
* Fixed the rarity on Hunter Adrenaline (rare -> uncommon)
* Implemented a workaround for Cleaving Whirlwind not visually returning to hand until after you hover over a card.

Other:
* Fixed Tenno blocking things at campfires.
* Split Chamber temporarily disabled until theme support exists.
* Exalted blade disabled pending rework.

Watchlist/near future plans:
* Need a more reliable way to apply weak/vulnerable
* Missing an X card.
* Corrosive Projection is really really good. Might need a nerf but it lives for now.