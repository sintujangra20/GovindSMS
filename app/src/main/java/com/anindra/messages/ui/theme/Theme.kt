package com.anindra.messages.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Density
import com.anindra.messages.data.SettingsStore

/**
 * Google Messages Layout with Saffron/Orange Accent (Material 3)
 * गूगल मैसेजेस के स्ट्रक्चर पर आधारित, लेकिन नीले की जगह केसरिया रंग का इस्तेमाल।
 */

// ────────────────────────── Saffron Light Scheme ───────────────────────
private val SaffronLightColors = lightColorScheme(
    primary = Color(0xFFE65100),          // गहरा केसरिया / ऑरेंज (मेन ब्रांड कलर)
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFFFDCC1), // हल्का केसरिया (Outgoing Bubble और सेलेक्टेड आइटम्स के लिए)
    onPrimaryContainer = Color(0xFF341100),
    secondary = Color(0xFF8F4F00),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFFFDDB8), 
    onSecondaryContainer = Color(0xFF2E1600),
    background = Color(0xFFFFFFFF),
    onBackground = Color(0xFF1F1F1F),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF1F1F1F),
    surfaceVariant = Color(0xFFEAE0D9),
    onSurfaceVariant = Color(0xFF4F453F),
    outline = Color(0xFF82756E),
    surfaceContainerLowest = Color(0xFFFFFFFF),
    surfaceContainerLow = Color(0xFFFDF8F5),
    surfaceContainer = Color(0xFFF6EEEE),       // डिफॉल्ट इनकमिंग मैसेज बैकग्राउंड
    surfaceContainerHigh = Color(0xFFEFE6E1),
    surfaceContainerHighest = Color(0xFFE8DED9)
)

// ────────────────────────── Saffron Dark Scheme ────────────────────────
private val SaffronDarkColors = darkColorScheme(
    primary = Color(0xFFFFB74D),          // चमकदार हल्का केसरिया (डार्क मोड एक्सेंट)
    onPrimary = Color(0xFF4E2600),
    primaryContainer = Color(0xFFB35800), // डार्क मोड में आउटगोइंग बबल का रंग
    onPrimaryContainer = Color(0xFFFFDCC1),
    secondary = Color(0xFFFFB86F),
    onSecondary = Color(0xFF4F2500),
    secondaryContainer = Color(0xFF6F3600),
    onSecondaryContainer = Color(0xFFFFDDB8),
    background = Color(0xFF131314),       // गूगल मैसेजेस का सिग्नेचर डार्क बैकग्राउंड (#131314)
    onSurface = Color(0xFFE3E3E3),
    surfaceVariant = Color(0xFF4F453F),
    onSurfaceVariant = Color(0xFFD3C4BC),
    outline = Color(0xFF9C8E87),
    surfaceContainerLowest = Color(0xFF0F0F10),
    surfaceContainerLow = Color(0xFF1C1B1F),
    surfaceContainer = Color(0xFF202124),       // डार्क मोड में इनकमिंग बबल का न्यूट्रल रंग
    surfaceContainerHigh = Color(0xFF28292A),
    surfaceContainerHighest = Color(0xFF303134)
)

// ─────────────── Chat Specific Mappings (Google Messages Logic) ───────────────

// 1. Outgoing (आपका मैसेज): लाइट मोड में हल्का केसरिया, डार्क मोड में थोड़ा गहरा केसरिया।
val ColorScheme.outgoingBubble: Color get() = primaryContainer          

// 2. Incoming (सामने वाले का मैसेज): गूगल मैसेजेस की तरह न्यूट्रल ग्रे/गहरे रंग का बबल।
val ColorScheme.incomingBubble: Color get() = surfaceContainer          

// 3. Chat Bar (नीचे इनपुट बार): न्यूट्रल और साफ लुक के लिए।
val ColorScheme.chatBar: Color get() = surfaceContainerLow              

// 4. Input Pill (टेक्स्ट फील्ड कैप्सूल):
val ColorScheme.inputPill: Color get() = surfaceContainerHigh           

val ColorScheme.chatBackground: Color get() = background
val ColorScheme.linkColor: Color get() = primary

@Composable
fun MessagesTheme(
    mode: String = "system",       // system | light | dark
    dynamicColor: Boolean = false, // केसरिया थीम को फिक्स रखने के लिए इसे फॉल्स (false) रखा है
    font: String = SettingsStore.FONT_SYSTEM,
    a11y: A11yOptions = A11yOptions.DISABLED,
    content: @Composable () -> Unit
) {
    val darkTheme = when (mode) {
        "dark" -> true
        "light" -> false
        else -> isSystemInDarkTheme()
    }

    val context = LocalContext.current
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> SaffronDarkColors
        else -> SaffronLightColors
    }

    // स्टेटस बार सेटिंग्स (थीम के हिसाब से आइकॉन एडजस्ट करना)
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
