package com.kroq.myaquariumsimulator.ui.popup.upgrade

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kroq.myaquariumsimulator.R
import com.kroq.myaquariumsimulator.model.component.GameColors
import com.kroq.myaquariumsimulator.model.upgrade.UpgradeCategoryTab
import com.kroq.myaquariumsimulator.ui.popup.GamePopup
import com.kroq.myaquariumsimulator.ui.popup.GeneralPopup

@Composable
fun UpgradeTabs(
    selectedTab: UpgradeCategoryTab,
    onTabSelected: (UpgradeCategoryTab) -> Unit
) {
    val listState = rememberScrollState()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(listState),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        UpgradeCategoryTab.entries.forEach { tab ->
            val selected = tab == selectedTab

            Box(
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(18.dp)
                    )
                    .background(
                        if (selected) {
                            Brush.horizontalGradient(
                                listOf(
                                    Color(0xFF5ED5F5),
                                    Color(0xFF32B9E5)
                                )
                            )
                        } else {
                            Brush.horizontalGradient(
                                listOf(
                                    Color.White.copy(.35f),
                                    Color.White.copy(.22f)
                                )
                            )
                        }
                    )
                    .border(
                        width = 2.dp,
                        color = if (selected) {
                            Color(0xFF1BA6D4)
                        } else {
                            Color.White.copy(.35f)
                        },
                        shape = RoundedCornerShape(18.dp)
                    )
                    .clickable {
                        onTabSelected(tab)
                    }
                    .padding(
                        horizontal = 18.dp,
                        vertical = 10.dp
                    )
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = when (tab) {
                            UpgradeCategoryTab.FISH ->
                                "🐟"

                            UpgradeCategoryTab.BUBBLE ->
                                "🫧"
                        },
                        fontSize = 18.sp
                    )

                    Spacer(
                        Modifier.width(7.dp)
                    )

                    Text(
                        text = stringResource(
                            tab.titleResId
                        ),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (selected) {
                            Color.White
                        } else {
                            Color(0xFF267AA2)
                        }
                    )
                }
            }
        }
    }
}