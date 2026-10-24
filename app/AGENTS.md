# AquaVille --- Agent Guide

## 1. Project Purpose

AquaVille is a cute, casual aquarium management game built around short,
satisfying interactions:

-   Buy and collect fish.
-   Feed fish.
-   Fish generate coins.
-   Spend coins on aquarium, fish, bubble and other upgrades.
-   Buy aquarium decorations and unlock larger aquarium tiers.
-   Collect bubbles, coins and special rewards.
-   Complete daily tasks, achievements and welcome gifts.
-   Keep the aquarium clean.
-   Customize fish and, in the future, aquarium themes.

The game should feel **cute, relaxing, readable and rewarding**, not
complicated or hardcore.

## 2. Core Design Principles

1.  **Function first, then polish.**
2.  Prefer small, targeted changes over large refactors.
3.  Reuse existing managers, models and UI components.
4.  Do not duplicate game state or create a second source of truth.
5.  New UI must visually belong to the existing AquaVille style.
6.  Avoid unnecessary screens and systems when an existing screen can
    handle the feature.
7.  Keep GameScreen lightweight; game logic belongs in managers/models.
8.  Do not hardcode gameplay values inside composables when they belong
    to game data/configuration.
9.  Persistent player state belongs in `GameState`/`GameManager` and
    must be saved through the existing save flow.
10. Before adding a new architecture pattern, inspect the current
    project and follow its existing conventions.

## 3. Visual Style

AquaVille uses a **cute cartoon aquarium** aesthetic.

### Colors

-   Water: blue/cyan gradients.
-   UI: soft pastel gradients.
-   Each major UI section can have its own accent gradient.
-   Borders are rounded and usually use a darker version of the accent
    color.
-   Avoid harsh, flat UI colors unless used intentionally for
    warnings/errors.
-   Red is reserved for destructive/error/warning states.

### Shapes

-   Rounded corners.
-   Rounded buttons.
-   Soft borders.
-   Small shadows/gradients are preferred over flat rectangles.
-   UI should feel playful rather than corporate.

### Aquarium

The aquarium is the visual center of the game: - Blue water gradient. -
Rounded aquarium container. - Decorative items such as sand, seaweed,
starfish and rocks. - Fish move inside the aquarium. - Coins, bubbles
and dirt appear inside the aquarium. - A subtle glass highlight/border
is used to make the tank feel physical.

### Typography

The project is intended to move toward a dedicated game font system.
Until a font is selected, avoid introducing unrelated typography styles.
Future typography should ideally use: - One primary display/game font. -
Optionally one supporting font. Avoid many unrelated fonts.

## 4. UI Architecture

The project uses Jetpack Compose.

Important existing UI concepts include: - `GameScreen` -
`AquariumView` - `GameHud` - `BottomMenu` - `PopupContainer` -
`GamePopup` - `GeneralPopup` - `SettingsScreen` - `WelcomeGiftPopup` -
Achievement UI - Tutorial UI

Prefer extending shared components rather than creating visually
inconsistent one-off components.

### Popups

Popups are central to the game.

Desired behavior: - Smooth open animation. - Smooth close animation. -
Rounded game-style container. - Close button in a consistent position. -
Game-specific gradient/border. - Avoid Android-default dialog styling.

Future popup polish is an important visual task.

### Notifications

Do not use Android Toasts for normal gameplay feedback when a game UI
notification is more appropriate.

Examples: - Not enough coins. - Aquarium capacity reached. - No
cleaner. - Requirement not met.

The intended future solution is a reusable in-game notification layer
that can appear above normal gameplay and remain visible even when a
popup is open.

## 5. Game Screen

`GameScreen` coordinates the gameplay UI and starts/updates managers.

Typical layers: 1. Background. 2. Aquarium. 3. HUD. 4. Bottom menu. 5.
Notifications. 6. Popups. 7. Tutorial overlay.

Do not move all gameplay logic into `GameScreen`.

Managers currently handle systems such as: - `GameManager` -
`FishManager` - `CoinManager` - `CoinLoop` - `BubbleManager` -
`DirtManager` - `GoldFishManager` - `WelcomeGiftManager` -
`DailyTaskManager` - `TutorialManager` - `CleanerManager` -
`UpgradeManager` - `FishFoodManager` - `AudioManager`

Use the existing manager responsible for a system instead of creating
parallel logic.

## 6. Main Gameplay Loop

The current core loop is approximately:

`Fish → Feed → Fish produces coins → Spend coins → Upgrade aquarium/fish systems → Unlock more content`

Additional loops: - Collect bubbles. - Collect coins. - Clean
aquarium. - Complete daily tasks. - Claim welcome gifts. - Unlock
achievements. - Customize fish. - Unlock larger aquarium tiers.

The game should remain approachable and should not become an idle game
where the player only waits.

## 7. Fish

Fish are active entities inside the aquarium.

Fish have: - Unique IDs. - Income. - Movement. - Feeding state. - Coin
multiplier/state. - Shop requirements. - Upgrade interactions.

Fish feeding is an important interaction.

Current feeding behavior: - Player taps a fish. - If the fish is already
fed, do not feed again. - Food is consumed according to the fish's
income requirement. - Feeding can trigger tutorial progress. - Feeding
creates aquarium dirt. - Feeding starts/affects the fish's coin
production. - Coin rewards can be affected by upgrades.

Future fish-related systems may include: - More fish species. -
Different movement behaviors. - Fish mastery. - Fish skins. -
Special/premium cosmetic skins. - A fish feeding animation. - Fish
death/neglect system.

## 8. Fish Neglect / Death --- Planned

This is a planned feature, not an existing assumption.

Goal: add long-term engagement without making the game frustrating.

Proposed timing: - Around day 3 without feeding: warning. - Around day 4
without feeding: fish dies.

Important: - The purpose is **not** to make the game punishing. -
Warnings should be clear and friendly. - Consider recovery/revival
options before permanent loss. - The exact persistence model and
timestamps must be designed before implementation.

## 9. Aquarium Dirt

Aquarium dirt is already part of the game.

Current concept: - Feeding fish creates dirt particles. - Dirt level is
represented through `DirtLevel`. - Dirt can visually increase through
particles and an overlay. - At high dirt levels the aquarium becomes
difficult to see. - High dirt is intended to eventually stop normal fish
production/interactions until the player cleans the aquarium.

Cleaning is tier-based rather than always deleting all dirt.

Do not reintroduce arbitrary integer thresholds in UI code when the
project already has a `DirtLevel` abstraction.

## 10. Aquarium Progression

Aquarium progression currently uses tiers such as: - SMALL - MEDIUM -
LARGE

The game also has progression concepts such as: - FREE - BRONZE -
SILVER - GOLD

Unlock requirements should remain centralized in game data/models rather
than scattered through composables.

## 11. Economy

Main economy: - Coins. - Fish income. - Bubble rewards. - Upgrades. -
Cleaner purchases. - Fish food. - Aquarium progression.

There are also planned/potential premium systems: - Fish skins. -
Aquarium backgrounds/themes. - Other cosmetic content. - In-app
purchases. - Ads. - Online/cloud features.

Do not make premium systems pay-to-win. Cosmetics and personalization
are preferred.

## 12. Achievements / Rewards

Achievements are part of the game.

The achievement area is accessed through a hamburger-style button on the
GameScreen.

The hamburger menu may later become a general reward/secondary systems
hub containing: - Achievements. - Daily rewards. - Daily tasks. - Other
reward systems. - Potential fish information/collection.

Avoid filling the GameScreen with many permanent buttons.

## 13. Long-Term Expansion

The current game can eventually expand into a larger casual management
game.

Potential progression layers: 1. Aquarium progression. 2. Fish mastery.
3. Fish skins/customization. 4. Aquarium themes/backgrounds. 5. More
fish and decorations. 6. New aquarium regions/shapes. 7. Potential
secondary businesses such as a sushi shop.

The sushi shop is a future expansion concept, not part of the current
core implementation.

Important design direction: - Do not make the player kill/use their
owned aquarium fish as sushi ingredients. - The aquarium and restaurant
should feel connected but remain separate systems. - The restaurant
should be introduced only after the aquarium progression is strong
enough.

## 14. Current Planned Improvements

Priority order should generally be:

### High priority

-   Replace gameplay Toast messages with reusable in-game notifications.
-   Improve popup open/close animations.
-   Introduce a dedicated game typography/font system.
-   Polish button, gradient, border and color consistency.
-   Improve tutorial visuals.

### Medium priority

-   Fish skin/customization system.
-   More fish with distinct behaviors.
-   More aquarium decorations/items.
-   Aquarium background/theme customization.
-   Expand the hamburger/reward hub.

### Later / major expansion

-   Different aquarium shapes.
-   New aquarium regions.
-   Fish mastery/progression.
-   Larger long-term progression system.
-   Sushi shop / secondary business.

### Planned fish neglect system

-   Day 3: warning.
-   Day 4: death.
-   Keep the system forgiving and player-friendly.

## 15. Agent Working Rules

When modifying the project:

-   First inspect existing models/managers/components before making changes.
-   Read relevant documentation (`AGENTS.md`, `DESIGN.md`, `LOGIC.md`) before starting any task.
-   Follow `DESIGN.md` for UI changes and `LOGIC.md` for gameplay/behavior changes.
-   Identify the existing source of truth and preserve existing architecture/managers.
-   Reuse existing code, theme gradients, and components where possible.
-   Make the smallest change that solves the task without unnecessary refactoring.
-   Preserve existing behavior unless the task explicitly changes it.
-   Keep gameplay values out of UI when possible.
-   Keep persistence inside the existing persistence architecture.
-   Keep visual constants centralized in `GameColors` / theme configurations.
-   Test edge cases around saved state, app restart and lifecycle.
-   Consider popup/overlay ordering when adding UI.
-   Consider both fresh install and returning-player behavior.
-   Do not assume a feature exists just because it is listed as planned.
-   Prefer using `?.let { ... }` for null checks instead of `if (value != null)`.
-   Tutorial Overlay Positioning Rules: Category A steps (`showContinue == true`) must be centered (`Alignment.Center`). Category B steps (`showContinue == false`) must be positioned 16.dp above target bounds, tracking moving targets dynamically in real-time, with fallback below target if top edge is reached.
-   Update documentation files after completing code changes to reflect verified state.
-   At the end of every task, summarize changed code files, updated doc files, colors/gradients used, test results, and open items.
