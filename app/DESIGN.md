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

Use the existing `GameColors` / gradient system when available.

Typical pattern: - `top` - `light` - `base` - `border` - `dark`

Use accent colors consistently within a screen/popup.

Red should communicate: - Error - Warning - Dangerous state - Urgent
timer

Do not use red as a general decorative color.

## Typography

Target: - One game/display font. - Optional supporting font.

Titles: - Bold. - Short. - Centered when appropriate.

Buttons: - Strong weight. - High contrast. - Short labels.

Descriptions: - Smaller. - Lower contrast than titles. - Centered when
the component is centered.

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
