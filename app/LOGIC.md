# AquaVille --- Logic & Architecture

## Core State

Persistent player data is represented through `GameState` and managed
through `GameManager`.

Do not create duplicate persistent state in individual composables.

When a value must survive app restart: 1. Add it to the appropriate game
state/model. 2. Update it through the existing `GameManager.update`. 3.
Ensure the existing save/load mechanism persists it.

## Managers

Use managers according to responsibility.

Examples: - `GameManager`: central game state. - `FishManager`: active
fish and movement. - `CoinManager`: coin operations/spawning. -
`CoinLoop`: periodic income. - `FishFoodManager`: fish food and
feeding. - `DirtManager`: aquarium dirt particles and updates. -
`CleanerManager`: cleaner inventory/cleaning. - `UpgradeManager`:
upgrade values and levels. - `BubbleManager`: bubbles. -
`GoldFishManager`: Gold Fish behavior. - `WelcomeGiftManager`: welcome
gift progression. - `DailyTaskManager`: daily tasks. -
`TutorialManager`: tutorial progression. - `AudioManager`: music and
sound effects. - `ScreenManager`: screen dimensions/related game
positioning.

Before creating a new manager, verify that the responsibility does not
belong to an existing one.

## Game Loop

Fish movement and other real-time systems run from the game
screen/lifecycle.

`CoinLoop` periodically calculates active fish income.

Important distinction: - `FishManager.fishes` represents active fish
currently in the aquarium. - Persistent ownership is stored separately.

When aquarium dirt reaches the blocking level, active fish production
and interactions may need to stop.

## Fish Feeding

Typical flow:

1.  Player taps fish.
2.  Check whether fish is already fed.
3.  Check food requirement.
4.  If insufficient food, show game notification.
5.  Trigger tutorial progress if applicable.
6.  Add dirt.
7.  Consume food.
8.  Start/update fish feeding state.
9.  Spawn coin/income effects as appropriate.

Do not duplicate this logic inside multiple UI components.

## Dirt

Dirt is represented by a level abstraction such as `DirtLevel`.

Avoid scattered values like:

`if (dirt >= 25)`

inside multiple classes.

Prefer:

`DirtLevel.fromValue(...)`

and level properties/methods such as: - minimum value - maximum value -
next level - previous level - whether fish production is blocked

Cleaning should update persistent dirt state and then synchronize the
visual particle system.

## Notifications

Gameplay errors should eventually go through one notification mechanism.

Examples: - Insufficient coins. - No food. - No cleaner. - Aquarium
capacity reached. - Locked content.

The notification layer should be global enough to work when: - Aquarium
is visible. - Popup is open. - Bottom menu is open.

Do not use Toast as the long-term game feedback system.

## Popups

Popup state should be controlled by the existing UI state/popup
architecture.

Avoid putting separate `remember` flags into every caller unless
required.

Shared popup behavior belongs in reusable popup components.

## Fish Neglect / Death

Planned system.

Target: - No feeding for approximately 3 days → warning. - No feeding
for approximately 4 days → fish dies.

Recommended implementation direction: - Store a persistent timestamp for
the fish's last feeding. - Calculate elapsed time from the timestamp
rather than storing a continuously changing day counter. - Evaluate the
state when the app/game resumes or when fish state is checked. - Do not
run a background timer solely to determine death. - Keep warning/death
thresholds centralized in configuration or the fish-life system.

Important design requirement: - The feature should create attachment and
encourage returning to the aquarium. - It should not feel like
punishment. - Consider a friendly warning and a possible
recovery/revival mechanic before permanent removal.

## Achievements

Achievement progress should be derived from durable game events/state.

Examples: - Fish purchased. - Fish fed. - Coins earned. - Aquarium
upgraded. - Bubble collected. - Welcome gift completed. - Gold Fish
unlocked. - Aquarium cleaned.

Avoid deriving lifetime statistics from current balances.

Example: `currentCoins` is not the same as `totalCoinsEarned`.

If an achievement needs lifetime data, add a dedicated persistent
statistic.

## Future Fish Skins

Recommended conceptual model:

`Fish ownership` and `Fish cosmetic selection` are separate.

A player can own: - Fish A - Fish A + Golden Skin - Fish A + Neon Skin

The skin should not replace the fish's identity.

Persist selected skin per fish if players can customize individual fish.

## Future Aquarium Themes

Keep theme selection separate from aquarium progression.

A theme can modify: - Background. - Water appearance. -
Lighting/decoration ambience.

Do not duplicate the aquarium's gameplay state just because the visual
theme changes.

## Firebase / Online Data

The game may use Google Sign-In and Firebase/cloud persistence.

General rule: - Local state remains the immediate gameplay source of
truth. - Cloud state is used for account backup/synchronization. - Sync
must be deliberate; do not blindly overwrite local data. - Conflict
resolution must be defined before adding more cloud-backed features. -
Never store sensitive authentication secrets in `GameState`.

## Ads / IAP

Potential future monetization: - Ads. - Cosmetic purchases. - Aquarium
themes. - Fish skins.

Preferred monetization direction: - Cosmetic and personalization
content. - Avoid pay-to-win progression. - Keep the core aquarium
playable without payment.

## Performance

The aquarium contains moving fish, particles, bubbles and animated UI.

Avoid: - Creating unnecessary objects every frame. - Heavy recomposition
caused by unrelated state. - Large architecture rewrites for small
optimizations. - Excessive per-frame allocations.

Real-time loops should remain targeted to the systems that actually need
them.
