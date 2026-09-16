package com.kukurodev.mykukuroaquarium.model.component

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

object GameColors {

    val Purple = GameGradient(
        top = Color(0xFFF7EEFF),
        light = Color(0xFFE2CCFF),
        base = Color(0xFFC39CFF),
        dark = Color(0xFF9B6AF3),
        border = Color(0xFF7C3AED)
    )

    val PurpleDark = GameGradient(
        top = Color(0xFF7C3AED),
        light = Color(0xFF8E24AA),
        base = Color(0xFF7B1FA2),
        dark = Color(0xFF6A1B9A),
        border = Color(0xFF4A148C)
    )

    val Leaf = GameGradient(
        top = Color(0xFFF1FFF8),
        light = Color(0xFFD8FBE8),
        base = Color(0xFFB8F5D3),
        dark = Color(0xFF76DEA7),
        border = Color(0xFF33C47C)
    )

    val LeafDark = GameGradient(
        top = Color(0xFF33C47C),
        light = Color(0xFF43A047),
        base = Color(0xFF388E3C),
        dark = Color(0xFF2E7D32),
        border = Color(0xFF1B5E20)
    )

    val Ocean = GameGradient(
        top = Color(0xFFF1FCFF),
        light = Color(0xFFD8F5FF),
        base = Color(0xFFB4ECFF),
        dark = Color(0xFF67D3F3),
        border = Color(0xFF2AA7D7)
    )

    val OceanDark = GameGradient(
        top = Color(0xFF2AA7D7),
        light = Color(0xFF42A5F5),
        base = Color(0xFF1E88E5),
        dark = Color(0xFF1565C0),
        border = Color(0xFF0D47A1),
    )

    val Sun = GameGradient(
        top = Color(0xFFFFF9EF),
        light = Color(0xFFFFF0D4),
        base = Color(0xFFFFD88C),
        dark = Color(0xFFF4A938),
        border = Color(0xFFE08700)
    )

    val SunDark = GameGradient(
        top = Color(0xFFE08700),
        light = Color(0xFFFB8C00),
        base = Color(0xFFF57C00),
        dark = Color(0xFFE65100),
        border = Color(0xFFBF360C)
    )

    val Red = GameGradient(
        top = Color(0xFFFFF1F1),
        light = Color(0xFFFFD6D6),
        base = Color(0xFFFF9B9B),
        dark = Color(0xFFEF5350),
        border = Color(0xFFD92D2D)
    )

    val RedDark = GameGradient(
        top = Color(0xFFD92D2D),
        light = Color(0xFFE53935),
        base = Color(0xFFD32F2F),
        dark = Color(0xFFC62828),
        border = Color(0xFF8E0000)
    )

    val Pink = GameGradient(
        top = Color(0xFFFFF0F5),    // Çok açık pembe
        light = Color(0xFFFFE4EC),  // Yumuşak geçiş
        base = Color(0xFFFFC1D6),   // Açık pastel pembe
        dark = Color(0xFFFF94B8),    // Sertleşmeyen, tatlı orta ton
        border = Color(0xFFF06292)  // Çerçeveyi belirgin tutan ama yumuşak kalan renk
    )

    val PinkDark = GameGradient(
        top = Color(0xFFE91E63),
        light = Color(0xFFD81B60),
        base = Color(0xFFC2185B),
        dark = Color(0xFFAD1457),
        border = Color(0xFF880E4F)
    )

    val Slate = GameGradient(
        top = Color(0xFFF8FAFC),
        light = Color(0xFFE2E8F0),
        base = Color(0xFFCBD5E1),
        dark = Color(0xFF94A3B8),
        border = Color(0xFF64748B)
    )

    val SlateDark = GameGradient(
        top = Color(0xFF64748B),
        light = Color(0xFF475569),
        base = Color(0xFF334155),
        dark = Color(0xFF1E293B),
        border = Color(0xFF0F172A)
    )

    val OceanGradient = Brush.verticalGradient(
        listOf(
            Color(0xFF6FD3FF),
            Color(0xFF3BA7F5)
        )
    )

    val GrayGradient = Brush.verticalGradient(
        listOf(
            Color(0xFFD8E4EA),
            Color(0xFFAEBCC5)
        )
    )

    val GoldGradient = Brush.verticalGradient(
        listOf(
            Color(0xFFFFE082),
            Color(0xFFFFB300)
        )
    )
}