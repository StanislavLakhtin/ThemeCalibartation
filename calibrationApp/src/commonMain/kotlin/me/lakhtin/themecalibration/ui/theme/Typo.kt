package me.lakhtin.themecalibration.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.Typography
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.Font
import themecalibration.calibrationapp.generated.resources.*

@Composable
fun defaultAppFontFamily(): FontFamily {
    return FontFamily(
        // Thin (100)
        Font(Res.font.IBMPlexSans_Thin, weight = FontWeight.Thin),

        // ExtraLight (200)
        Font(Res.font.IBMPlexSans_ExtraLight, weight = FontWeight.ExtraLight),

        // Light (300)
        Font(Res.font.IBMPlexSans_Light, weight = FontWeight.Light),

        // Regular (400) - Normal
        Font(Res.font.IBMPlexSans_Regular, weight = FontWeight.Normal),

        // Medium (500)
        Font(Res.font.IBMPlexSans_Medium, weight = FontWeight.Medium),

        // SemiBold (600)
        Font(Res.font.IBMPlexSans_SemiBold, weight = FontWeight.SemiBold),

        // Bold (700)
        Font(Res.font.IBMPlexSans_Bold, weight = FontWeight.Bold),
    )
}

@Composable
fun condensedAppFontFamily(): FontFamily {
    return FontFamily(
        // Thin (100)
        Font(Res.font.IBMPlexSans_Condensed_Thin, weight = FontWeight.Thin),

        // ExtraLight (200)
        Font(Res.font.IBMPlexSans_Condensed_ExtraLight, weight = FontWeight.ExtraLight),

        // Light (300)
        Font(Res.font.IBMPlexSans_Condensed_Light, weight = FontWeight.Light),

        // Regular (400)
        Font(Res.font.IBMPlexSans_Condensed_Regular, weight = FontWeight.Normal),

        // Medium (500)
        Font(Res.font.IBMPlexSans_Condensed_Medium, weight = FontWeight.Medium),

        // SemiBold (600)
        Font(Res.font.IBMPlexSans_Condensed_SemiBold, weight = FontWeight.SemiBold),

        // Bold (700)
        Font(Res.font.IBMPlexSans_Condensed_Bold, weight = FontWeight.Bold),
    )
}

@Composable
fun semiCondensedAppFontFamily(): FontFamily {
    return FontFamily(
        // Thin (100)
        Font(Res.font.IBMPlexSans_SemiCondensed_Thin, weight = FontWeight.Thin),

        // ExtraLight (200)
        Font(Res.font.IBMPlexSans_SemiCondensed_ExtraLight, weight = FontWeight.ExtraLight),

        // Light (300)
        Font(Res.font.IBMPlexSans_SemiCondensed_Light, weight = FontWeight.Light),

        // Regular (400)
        Font(Res.font.IBMPlexSans_SemiCondensed_Regular, weight = FontWeight.Normal),

        // Medium (500)
        Font(Res.font.IBMPlexSans_SemiCondensed_Medium, weight = FontWeight.Medium),

        // SemiBold (600)
        Font(Res.font.IBMPlexSans_SemiCondensed_SemiBold, weight = FontWeight.SemiBold),

        // Bold (700)
        Font(Res.font.IBMPlexSans_SemiCondensed_Bold, weight = FontWeight.Bold),
    )
}

@Composable
fun createAppTypography(useCondensed: Boolean = false): Typography {
    val fonts = if (useCondensed) condensedAppFontFamily() else defaultAppFontFamily()

    return Typography(
        // ============================================
        // DISPLAY - Очень крупные заголовки (редко)
        // ============================================
        displayLarge = TextStyle(
            fontFamily = fonts,
            fontWeight = FontWeight.Light, // 300
            fontSize = 57.sp,
            lineHeight = 64.sp,
            letterSpacing = (-0.25).sp
        ),
        displayMedium = TextStyle(
            fontFamily = fonts,
            fontWeight = FontWeight.Light, // 300
            fontSize = 45.sp,
            lineHeight = 52.sp,
            letterSpacing = 0.sp
        ),
        displaySmall = TextStyle(
            fontFamily = fonts,
            fontWeight = FontWeight.Normal, // 400
            fontSize = 36.sp,
            lineHeight = 44.sp,
            letterSpacing = 0.sp
        ),

        // ============================================
        // HEADLINE - Заголовки экранов и разделов
        // ============================================
        headlineLarge = TextStyle(
            fontFamily = fonts,
            fontWeight = FontWeight.SemiBold, // 600
            fontSize = 32.sp,
            lineHeight = 40.sp,
            letterSpacing = 0.sp
        ),
        headlineMedium = TextStyle(
            fontFamily = fonts,
            fontWeight = FontWeight.SemiBold, // 600
            fontSize = 28.sp,
            lineHeight = 36.sp,
            letterSpacing = 0.sp
        ),
        headlineSmall = TextStyle(
            fontFamily = fonts,
            fontWeight = FontWeight.SemiBold, // 600
            fontSize = 24.sp,
            lineHeight = 32.sp,
            letterSpacing = 0.sp
        ),

        // ============================================
        // TITLE - Заголовки карточек, AppBar, диалогов
        // ============================================
        titleLarge = TextStyle(
            fontFamily = fonts,
            fontWeight = FontWeight.SemiBold, // 500
            fontSize = 20.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.sp
        ),
        titleMedium = TextStyle(
            fontFamily = fonts,
            fontWeight = FontWeight.SemiBold, // 500
            fontSize = 18.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.15.sp
        ),
        titleSmall = TextStyle(
            fontFamily = fonts,
            fontWeight = FontWeight.SemiBold, // 500
            fontSize = 16.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp
        ),

        // ============================================
        // BODY - Основной текст приложения (ВАЖНО!)
        // ============================================
        bodyLarge = TextStyle(
            fontFamily = fonts,
            fontWeight = FontWeight.Normal, // 400
            fontSize = 20.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.5.sp
        ),
        bodyMedium = TextStyle(
            fontFamily = fonts,
            fontWeight = FontWeight.Normal, // 400
            fontSize = 18.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.25.sp
        ),
        bodySmall = TextStyle(
            fontFamily = fonts,
            fontWeight = FontWeight.Normal, // 400
            fontSize = 16.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.4.sp
        ),

        // ============================================
        // LABEL - Кнопки, табы, чипы, метки
        // ============================================
        labelLarge = TextStyle(
            fontFamily = fonts,
            fontWeight = FontWeight.Normal, // 400
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.1.sp
        ),
        labelMedium = TextStyle(
            fontFamily = fonts,
            fontWeight = FontWeight.Light, // 300
            fontSize = 11.sp,
            lineHeight = 14.sp,
            letterSpacing = 0.5.sp
        ),
        labelSmall = TextStyle(
            fontFamily = fonts,
            fontWeight = FontWeight.Light, // 300
            fontSize = 9.sp,
            lineHeight = 11.sp,
            letterSpacing = 0.5.sp
        ),
    )
}