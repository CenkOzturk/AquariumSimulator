# AquaVille --- Design System

## Visual Identity

AquaVille is a cute cartoon aquarium game. The visual language should
communicate:

-   Cute
-   Calm
-   Playful
-   Colorful
-   Friendly
-   Light progression

Avoid: - Realistic aquarium photography - Corporate/material-heavy UI -
Excessively dark interfaces - Sharp/angular UI - Too many competing
colors - Excessive screen clutter

## Aquarium Visuals

The aquarium is the main visual focus.

Current visual language: - Blue water gradients. - Rounded aquarium
frame. - Glass highlights. - Cartoon fish. - Cartoon decorations. -
Floating coins and bubbles. - Dirt particles/overlay.

The aquarium should remain visually dominant. UI should support it
rather than cover it.

## UI Components

Preferred: - Rounded cards. - Rounded buttons. - Soft gradients. -
Darker border versions of the same accent. - Clear hierarchy. - Large
readable titles. - Short descriptions. - Strong primary action.

Avoid: - Default Android dialogs. - Default Toasts for important game
feedback. - Flat system-looking buttons. - Excessive text.

## Color Usage

Use the existing `GameColors` / gradient system when available (`Purple`, `Leaf`, `Ocean`, `Sun`, `Red`, `Pink`, `Slate`, `Mint`, `Coral`, `Lime`).

Typical pattern: - `top` - `light` - `base` - `border` - `dark`

Do not define loose inline `Color(#...)` hex codes in UI composables; reuse central `GameColors` gradients.

Use accent colors consistently within a screen/popup.

Red should communicate: - Error - Warning - Dangerous state - Urgent
timer

Do not use red as a general decorative color.

## Contrast & Legibility

All text elements must maintain high contrast against their background container:
- Dark text (`Color(0xFF0F172A)` / `gradient.dark`) on light backgrounds.
- Crisp white text (`Color.White`) on dark/gradient backgrounds.
- Secondary and subtitle text must remain bold and high-contrast (`Color(0xFF334155)` / `Color(0xFF475569)`).
- Never place light text on light pastel backgrounds or dark text on dark backgrounds.

## Popup & Component Standards

Popups:
- Use solid/opaque container surfaces to prevent background aquarium graphics from bleeding through and straining eyes.
- Use `3.5.dp` 3D gradient borders matching `gradient.border` and `gradient.dark`.
- Rounded corners (`28.dp`-`32.dp`) and `16.dp`-`18.dp` drop shadows.
- Integrated corner close tab (`CornerCloseTab`) attached at `Alignment.TopEnd`.

Buttons:
- 3D game button style with inner top glossy highlight line (`Color.White.copy(0.35f)`).
- Bottom 3D bevel shadow edge (`Color.Black.copy(0.20f)`).
- Bouncy press scale animation (`scale = 0.92f`-`0.93f`).
- Single-line responsive text fitting for both English and Turkish lengths.

## Tutorial UI & Speech Bubble Standards

- Talking Fish Mascot: Uses `R.drawable.fish_01` on the left side with gentle swimming bobbing Y animation (`-3.dp` to `+3.dp`).
- Speech Bubble Panel: Puffy rounded shape (`26.dp`), `3.5.dp` 3D gradient border (`GameColors.Ocean`), and `14.dp` drop shadow.
- High-Contrast Text: All tutorial titles & messages are centered (`TextAlign.Center`) using bold, high-contrast dark slate text (`Color(0xFF0F172A)`) directly on the speech bubble background without unnecessary inner boxes.
- Compact Action Button: Compact right-aligned `SmallGameButton` (`Alignment.CenterEnd`) with text + arrow (`"DEVAM ➔"` / `"NEXT ➔"`).
- Positioning: Steps with continue buttons (`showContinue == true`) are centered on screen (`Alignment.Center`). Target-action steps are placed 16.dp above the target element, tracking moving targets dynamically, with top-edge fallback placing popup 16.dp below target.

## Responsive Localization

UI layouts must accommodate both English and Turkish text lengths:
- Turkish text is often longer than English; design buttons and tabs using flexible weights (`weight(1f)`) or compact padding instead of hardcoded fixed `dp` widths.
- Ensure text never clips or overflows on small screen sizes.

## Animation

Animation should make the game feel alive without slowing the player.

Preferred popup animation: - Fade + scale. - Slight overshoot is
acceptable. - Quick open. - Slightly faster close.

Preferred notification animation: - Fade/slide in. - Stay briefly. -
Fade/slide out.

Do not make every UI element bounce.

## GameScreen Layout

The aquarium should occupy most of the screen.

Permanent UI should be limited to important information/actions: -
HUD. - Primary navigation. - Essential controls.

Secondary systems should be moved behind: - Hamburger/reward menu. -
Popups. - Bottom menu.

The goal is a clean aquarium view.

## Customization

Future cosmetic systems should preserve the player's aquarium identity.

Fish skins are intended to let players personalize a fish they already
own.

Potential cosmetics: - Glow. - Golden. - Neon. - Crystal. - Seasonal. -
Special event skins.

Aquarium themes can change: - Background. - Water appearance. -
Lighting. - Decorative ambience.

Cosmetics should not directly create unfair gameplay advantages.

## Content Density

More content does not automatically mean a better screen.

Prefer: - A few strong decorations. - Distinct fish
silhouettes/colors. - Clear separation between foreground/background. -
Enough empty water for fish movement.

New fish should ideally add either: - Visual variety. - Movement
variety. - Progression value. - Cosmetic value.
