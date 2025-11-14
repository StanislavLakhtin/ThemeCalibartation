package me.lakhtin.themecalibration.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TopBar(title: String, onBackClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .padding(top = 24.dp)
            .statusBarsPadding(),
    horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "←",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .clickable { onBackClick() }
                .padding(horizontal = 16.dp)
        )
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall
        )
    }

}