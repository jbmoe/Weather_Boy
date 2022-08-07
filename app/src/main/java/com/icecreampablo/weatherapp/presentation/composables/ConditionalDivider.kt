package com.icecreampablo.weatherapp.presentation.composables

import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ConditionalDivider(
    modifier: Modifier = Modifier,
    isEnabled: Boolean,
    color: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    thickness: Dp = 1.0.dp,
    startIndent: Dp = 0.dp
) {
    if (isEnabled)
        Divider(
            modifier = modifier,
            color = color,
            thickness = thickness,
            startIndent = startIndent
        )
}