
package ru.aleshin.timeplanner.navigation

import cafe.adriel.voyager.core.screen.Screen
import ru.aleshin.core.utils.navigation.TabRouter
import ru.aleshin.features.analytics.api.navigation.AnalyticsFeatureStarter
import ru.aleshin.features.home.api.navigation.HomeFeatureStarter
import ru.aleshin.features.home.api.navigation.HomeScreens
import ru.aleshin.features.settings.api.navigation.SettingsFeatureStarter
import javax.inject.Inject
import javax.inject.Provider


interface TabNavigationManager {

    fun showHomeFeature(screen: HomeScreens?, isRoot: Boolean = false)
    fun showAnalyticsFeature()
    fun showSettingsFeature()

    class Base @Inject constructor(
        private val homeFeatureStarter: Provider<HomeFeatureStarter>,
        private val analyticsFeatureStarter: Provider<AnalyticsFeatureStarter>,
        private val settingsFeatureStarter: Provider<SettingsFeatureStarter>,
        private val router: TabRouter,
    ) : TabNavigationManager {

        override fun showHomeFeature(screen: HomeScreens?, isRoot: Boolean) = showTab(
            screen = homeFeatureStarter.get().provideHomeScreen(screen, isRoot),
        )

        override fun showAnalyticsFeature() = showTab(
            screen = analyticsFeatureStarter.get().provideMainScreen(),
        )

        override fun showSettingsFeature() = showTab(
            screen = settingsFeatureStarter.get().provideMainScreen(),
        )

        private fun showTab(screen: Screen) = router.showTab(screen)
    }
}
