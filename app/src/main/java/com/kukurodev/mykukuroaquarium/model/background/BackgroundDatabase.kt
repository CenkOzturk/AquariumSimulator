package com.kukurodev.mykukuroaquarium.model.background

import androidx.compose.ui.graphics.Color
import com.kukurodev.mykukuroaquarium.R

object BackgroundDatabase {
    private val backgrounds = listOf(
        BackgroundModel(
            id = 1,
            nameResId = R.string.bg_1_name,
            resId = R.drawable.bg_image,
            dominantColor = Color(0xFF1E88E5),
            waterColorTop = Color(0xFF3FA7D6),
            waterColorBottom = Color(0xFF1B6CA8),
            tankFrameColor = Color(0xFF0A2A43)
        ),
        // 2. Derin (Aynı zümrüt tonunun daha koyu versiyonu)
        BackgroundModel(
            id = 2,
            nameResId = R.string.bg_2_name,
            resId = R.drawable.bg_image_2,
            dominantColor = Color(0xFF00ACC1),
            waterColorTop = Color(0xFF044E3B),
            waterColorBottom = Color(0xFF022C22),
            tankFrameColor = Color(0xFF011A14)
        ),
        // 3. Zümrüt Resif: Üst mavi, alt pembemsi mavi (periwinkle blue)
        BackgroundModel(
            id = 3,
            nameResId = R.string.bg_3_name,
            resId = R.drawable.bg_image_3,
            dominantColor = Color(0xFF00897B),
            waterColorTop = Color(0xFF0466C8),
            waterColorBottom = Color(0xFF8B80C8),
            tankFrameColor = Color(0xFF022C22)
        ),
        // 4. Gece Mavisi: Derin mavi (mor alttonlu)
        BackgroundModel(
            id = 4,
            nameResId = R.string.bg_4_name,
            resId = R.drawable.bg_image_4,
            dominantColor = Color(0xFF3949AB),
            waterColorTop = Color(0xFF2563EB),
            waterColorBottom = Color(0xFF1E3A8A),
            tankFrameColor = Color(0xFF0F172A)
        ),
        // 5. Mor Lagün (Çok iyi - korundu)
        BackgroundModel(
            id = 5,
            nameResId = R.string.bg_5_name,
            resId = R.drawable.bg_image_5,
            dominantColor = Color(0xFF5E35B1),
            waterColorTop = Color(0xFF2DD4BF),
            waterColorBottom = Color(0xFF0F766E),
            tankFrameColor = Color(0xFF042F2C)
        ),
        // 6. Mercan Gün Batımı (Süper - korundu)
        BackgroundModel(
            id = 6,
            nameResId = R.string.bg_6_name,
            resId = R.drawable.bg_image_6,
            dominantColor = Color(0xFF8E24AA),
            waterColorTop = Color(0xFF1E5276),
            waterColorBottom = Color(0xFF0D2E45),
            tankFrameColor = Color(0xFF061A29)
        ),
        // 7. Kristal Pembe: Çok daha açık ve parlak kristal ışıklı su
        BackgroundModel(
            id = 7,
            nameResId = R.string.bg_7_name,
            resId = R.drawable.bg_image_7,
            dominantColor = Color(0xFFD81B60),
            waterColorTop = Color(0xFFF0F9FF),
            waterColorBottom = Color(0xFF7DD3FC),
            tankFrameColor = Color(0xFF0284C7)
        ),
        BackgroundModel(
            id = 8,
            nameResId = R.string.bg_8_name,
            resId = R.drawable.bg_image_8,
            dominantColor = Color(0xFF00838F),
            waterColorTop = Color(0xFF06B6D4),
            waterColorBottom = Color(0xFF0E7490),
            tankFrameColor = Color(0xFF164E63)
        ),
        // 9. Derin Mavi: Mercan mavisi baz alınıp biraz daha mor/lacivert altton eklenmiş hali
        BackgroundModel(
            id = 9,
            nameResId = R.string.bg_9_name,
            resId = R.drawable.bg_image_9,
            dominantColor = Color(0xFF1565C0),
            waterColorTop = Color(0xFF2E407E),
            waterColorBottom = Color(0xFF162045),
            tankFrameColor = Color(0xFF0A102A)
        )
    )

    fun getAllBackgrounds(): List<BackgroundModel> = backgrounds

    fun getBackgroundById(id: Int): BackgroundModel {
        return backgrounds.find { it.id == id } ?: backgrounds.first()
    }
}
