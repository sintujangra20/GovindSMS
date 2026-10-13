package com.anindra.messages.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Density
import com.anindra.messages.data.SettingsStore

/**
 * Material 3 color system (m3.material.io/styles/color/system).
 *
 * Govind SMS palette: neutral modern dark surfaces with a light kesariya accent,
 * mapped to the standard M3 scheme roles for light & dark.
 * UI code must reference roles via MaterialTheme.colorScheme — never hardcode.
 */

// ────────────────────────── Light scheme (unchanged) ─────────────────────────────
private val LightColors = lightColorScheme(
    primary = Color(0xFF2E7D32),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFCBEFCD),
    onPrimaryContainer = Color(0xFF002106),
    inversePrimary = Color(0xFFFFB870),
    secondary = Color(0xFFC75000),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFFFDCC1),
    onSecondaryContainer = Color(0xFF2F1500),
    tertiary = Color(0xFF8B5000),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFFFDDB5),
    onTertiaryContainer = Color(0xFF2C1600),
    error = Color(0xFFBA1A1A),
    onError = Color(0xFFFFFFFF),
    errorContainer = Color(0xFFFFDAD6),
    onErrorContainer = Color(0xFF410002),
    background = Color(0xFFF5FBF3),
    onBackground = Color(0xFF181D18),
    surface = Color(0xFFF5FBF3),
    onSurface = Color(0xFF181D18),
    surfaceVariant = Color(0xFFDCE5DB),
    onSurfaceVariant = Color(0xFF404941),
    outline = Color(0xFF707970),
    outlineVariant = Color(0xFFC0C9BF),
    scrim = Color(0xFF000000),
    inverseSurface = Color(0xFF2D322D),
    inverseOnSurface = Color(0xFFEEF2EA),
    surfaceDim = Color(0xFFD5DCD3),
    surfaceBright = Color(0xFFF5FBF3),
    surfaceContainerLowest = Color(0xFFFFFFFF),
    surfaceContainerLow = Color(0xFFEFF6ED),
    surfaceContainer = Color(0xFFE9F0E7),
    surfaceContainerHigh = Color(0xFFE3EAE1),
    surfaceContainerHighest = Color(0xFFDEE4DC)
)

// ────────────────────────── Dark scheme (modern dark + kesariya) ─────────────────
private val DarkColors = darkColorScheme(
    // Kesariya accent
    primary = Color(0xFFFF8A3D),
    onPrimary = Color(0xFF2A1508),
    primaryContainer = Color(0xFF303030),
    onPrimaryContainer = Color(0xFFFFFFFF),
    inversePrimary = Color(0xFFE66F24),

    // Neutral secondary
    secondary = Color(0xFFBDBDBD),
    onSecondary = Color(0xFF202020),
    secondaryContainer = Color(0xFF242424),
    onSecondaryContainer = Color(0xFFFFFFFF),

    // Soft kesariya tertiary
    tertiary = Color(0xFFFFB074),
    onTertiary = Color(0xFF2A1508),
    tertiaryContainer = Color(0xFF3A261A),
    onTertiaryContainer = Color(0xFFFFDCC7),

    error = Color(0xFFFFB4AB),
    onError = Color(0xFF690005),
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6),

    // Modern dark UI
    background = Color(0xFF121212),
    onBackground = Color(0xFFFFFFFF),
    surface = Color(0xFF121212),
    onSurface = Color(0xFFFFFFFF),

    // Message / secondary areas
    surfaceVariant = Color(0xFF242424),
    onSurfaceVariant = Color(0xFFBDBDBD),

    outline = Color(0xFF8E8E8E),
    outlineVariant = Color(0xFF3A3A3A),
    scrim = Color(0xFF000000),

    inverseSurface = Color(0xFFE5E5E5),
    inverseOnSurface = Color(0xFF202020),

    surfaceDim = Color(0xFF0F0F0F),
    surfaceBright = Color(0xFF303030),
    surfaceContainerLowest = Color(0xFF0F0F0F),
    surfaceContainerLow = Color(0xFF181818),
    surfaceContainer = Color(0xFF1C1C1C),
    surfaceContainerHigh = Color(0xFF242424),
    surfaceContainerHighest = Color(0xFF303030)
)

// ─────────────── High-contrast schemes (accessibility mode) ───────────────
// Same M3 roles, pushed to pure black/white text-on-background and stronger
// outlines to meet WCAG AAA contrast in accessibility mode.
private val LightHighContrastColors = lightColorScheme(
    primary = Color(0xFF1B5E20),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFB7E4B9),
    onPrimaryContainer = Color(0xFF000000),
    inversePrimary = Color(0xFFFFB870),
    secondary = Color(0xFF8F3F00),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFFFD2AE),
    onSecondaryContainer = Color(0xFF000000),
    tertiary = Color(0xFF6B3E00),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFFFDDB5),
    onTertiaryContainer = Color(0xFF000000),
    error = Color(0xFF8C0009),
    onError = Color(0xFFFFFFFF),
    errorContainer = Color(0xFFFFDAD6),
    onErrorContainer = Color(0xFF2A0000),
    background = Color(0xFFFFFFFF),
    onBackground = Color(0xFF000000),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF000000),
    surfaceVariant = Color(0xFFDDE5DC),
    onSurfaceVariant = Color(0xFF1A1D1A),
    outline = Color(0xFF3A423B),
    outlineVariant = Color(0xFF5A645B),
    scrim = Color(0xFF000000),
    inverseSurface = Color(0xFF2D322D),
    inverseOnSurface = Color(0xFFFFFFFF),
    surfaceDim = Color(0xFFD5DCD3),
    surfaceBright = Color(0xFFFFFFFF),
    surfaceContainerLowest = Color(0xFFFFFFFF),
    surfaceContainerLow = Color(0xFFEFF6ED),
    surfaceContainer = Color(0xFFE9F0E7),
    surfaceContainerHigh = Color(0xFFE3EAE1),
    surfaceContainerHighest = Color(0xFFDEE4DC)
)

private val DarkHighContrastColors = darkColorScheme(
    // High-contrast dark mode: pure black with clear kesariya accent
    primary = Color(0xFFFFD2A0),
    onPrimary = Color(0xFF000000),
    primaryContainer = Color(0xFFFF8A3D),
    onPrimaryContainer = Color(0xFF000000),
    inversePrimary = Color(0xFFE66F24),

    secondary = Color(0xFFE0E0E0),
    onSecondary = Color(0xFF000000),
    secondaryContainer = Color(0xFF242424),
    onSecondaryContainer = Color(0xFFFFFFFF),

    tertiary = Color(0xFFFFDCC7),
    onTertiary = Color(0xFF000000),
    tertiaryContainer = Color(0xFF3A261A),
    onTertiaryContainer = Color(0xFFFFFFFF),

    error = Color(0xFFFFDAD6),
    onError = Color(0xFF000000),
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6),

    background = Color(0xFF000000),
    onBackground = Color(0xFFFFFFFF),
    surface = Color(0xFF000000),
    onSurface = Color(0xFFFFFFFF),

    surfaceVariant = Color(0xFF242424),
    onSurfaceVariant = Color(0xFFE0E0E0),
    outline = Color(0xFFBDBDBD),
    outlineVariant = Color(0xFF707070),
    scrim = Color(0xFF000000),

    inverseSurface = Color(0xFFE5E5E5),
    inverseOnSurface = Color(0xFF000000),

    surfaceDim = Color(0xFF000000),
    surfaceBright = Color(0xFF303030),
    surfaceContainerLowest = Color(0xFF000000),
    surfaceContainerLow = Color(0xFF101010),
    surfaceContainer = Color(0xFF181818),
    surfaceContainerHigh = Color(0xFF242424),
    surfaceContainerHighest = Color(0xFF303030)
)

// Semantic aliases so screens express intent, not raw colors.
val ColorScheme.outgoingBubble: Color
    get() = if (background.luminance() < 0.5f) Color(0xFF303030) else primaryContainer

val ColorScheme.incomingBubble: Color
    get() = if (background.luminance() < 0.5f) Color(0xFF242424) else surfaceContainerHighest

val ColorScheme.chatBar: Color
    get() = if (background.luminance() < 0.5f) Color(0xFF1C1C1C) else surfaceContainerLow

val ColorScheme.inputPill: Color
    get() = if (background.luminance() < 0.5f) Color(0xFF1C1C1C) else surfaceContainerHigh

val ColorScheme.selectedBubble: Color
    get() = if (background.luminance() < 0.5f) Color(0xFFFF8A3D) else Color(0xFFB04A00)

val ColorScheme.onSelectedBubble: Color
    get() = if (background.luminance() < 0.5f) Color(0xFF2A1508) else Color(0xFFFFFFFF)

// Chat date separators and message times use the platform default typeface
// at a slightly lighter weight than surrounding text.
val ChatMetaWeight: FontWeight = FontWeight.Medium

@Composable
fun MessagesTheme(
    mode: String = "system",   // system | light | dark
    font: String = SettingsStore.FONT_SYSTEM,
    a11y: A11yOptions = A11yOptions.DISABLED,
    content: @Composable () -> Unit
) {
    val darkTheme = when (mode) {
        "dark" -> true
        "light" -> false
        else -> isSystemInDarkTheme()
    }

    // Match status-bar icon appearance to the *app* theme (not just the system).
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            androidx.core.view.WindowCompat.getInsetsController(window, view)
                .isAppearanceLightStatusBars = !darkTheme
        }
    }

    val typography = remember(font, a11y.boldEnabled) {
        messagesTypography(AppFonts.familyFor(font), bold = a11y.boldEnabled)
    }

    val colorScheme = when {
        a11y.highContrastEnabled && darkTheme -> DarkHighContrastColors
        a11y.highContrastEnabled -> LightHighContrastColors
        darkTheme -> DarkColors
        else -> LightColors
    }

    // App text scale stacks on top of the system font scale.
    val systemDensity = LocalDensity.current
    val scaledDensity = remember(systemDensity, a11y.fontScale) {
        Density(systemDensity.density, systemDensity.fontScale * a11y.fontScale)
    }

    CompositionLocalProvider(
        LocalDensity provides scaledDensity,
        LocalReduceMotion provides a11y.reduceMotionEnabled,
        LocalLargeTouchTargets provides a11y.largeTouchTargetsEnabled
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = typography,
            content = content
        )
    }
}
