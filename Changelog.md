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