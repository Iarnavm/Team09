
package ru.aleshin.timeplanner.presentation.ui.splash

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import cafe.adriel.voyager.core.screen.Screen
import ru.aleshin.core.ui.theme.material.splashGradientColors
import ru.aleshin.core.ui.views.SystemBarsColor

/**
 * @author Stanislav Aleshin on 14.02.2023.
 */
class SplashScreen : Screen {

    @Composable
    override fun Content() {
        SplashContent(
            modifier = Modifier.background(
                brush = Brush.verticalGradient(
                    colors = splashGradientColors,
                ),
            ),
        )
        SystemBarsColor(
            statusBarColor = splashGradientColors[0],
            navigationBarColor = splashGradientColors[1],
            isDarkIcons = true,
        )
    }
}
