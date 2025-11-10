package me.lakhtin.themecalibration.ui.theme

import androidx.compose.ui.graphics.Color

// ============================================
// ПАЛИТРА ЯРКОСТИ (0 = черный, 100 = белый)
// ПАЛИТРА ЦВЕТОВ  (усреднённая, модуль 10)
// Система именования: {ColorName}{Brightness0-100}
// ============================================

val Black0 = Color(0xFF000000)   // scrimLight
val Blue10 = Color(0xFF36566B)   // tertiaryLight, onTertiaryContainerLight
val Blue0 = Color(0xFF0F3447)    // onTertiaryDark
val Blue50 = Color(0xFF3572E7)   // Linear Blue Dark
val Blue60 = Color(0xFFA9CBE3)   // tertiaryDark
val Blue80 = Color(0xFFC6E7FF)   // tertiaryContainerLight
val Cyan0 = Color(0xFF171D1B)    // onBackgroundLight, onSurfaceLight
val Cyan10 = Color(0xFF235B4F)   // primaryLight, onPrimaryContainerLight, secondaryLight
val Cyan60 = Color(0xFF86D6BF)   // inversePrimaryLight
val Cyan80 = Color(0xFFC3EBDE)   // primaryContainerLight, secondaryContainerLight, surfaceVariantLight
val Gray0 = Color(0xFF2B322F)    // inverseSurfaceLight
val Gray10 = Color(0xFF242632)   // onSurfaceVariantLight
val Gray20 = Color(0xFF6F7975)   // outlineLight
val Gray30 = Color(0xFF89938F)   // outlineDark
val Gray60 = Color(0xFFBFC9C4)   // outlineVariantLight
val Gray70 = Color(0xFFD5DBD8)   // surfaceDimLight
val Gray80 = Color(0xFFDDE4E0)   // onSecondaryContainer
val Green80 = Color(0xFF30DEBD)  // surfaceContainerHighLight, surfaceContainerHighestLight
val Green90 = Color(0xFFECF2EE)  // inverseOnSurfaceLight, surfaceContainerLowLight, surfaceContainerLight
val Green100 = Color(0xFFF5FBF7) // backgroundLight, surfaceLight, surfaceBrightLight
val Red0 = Color(0xFF690005)     // onErrorDark
val Red10 = Color(0xFFA70D12)    // errorLight, onErrorContainerLight
val Red60 = Color(0xFFFFB4AB)    // errorDark
val Red80 = Color(0xFFFFDAD6)    // errorContainerLight
val White100 = Color(0xFFFFFFFF) // onPrimaryLight, onSecondaryLight, onTertiaryLight, onErrorLight, surfaceContainerLowestLight