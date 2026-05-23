package com.kroq.myaquariumsimulator.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kroq.myaquariumsimulator.R

@Composable
fun FrontItems() {
    Box() {
        //STAR
        Image(
            painter = painterResource(R.drawable.starfish),
            contentDescription = null,
            modifier = Modifier
                .size(160.dp)
                .align(Alignment.TopStart)
                .padding(start = 20.dp, bottom = 50.dp)
        )

        //ROCKS
        Image(
            painter = painterResource(R.drawable.rock_small),
            contentDescription = null,
            modifier = Modifier
                .size(130.dp)
                .align(Alignment.BottomStart)
        )
        Image(
            painter = painterResource(R.drawable.rock_small),
            contentDescription = null,
            modifier = Modifier
                .size(130.dp)
                .align(Alignment.BottomEnd)
        )
    }
}