
package ru.aleshin.timeplanner.navigation

import ru.aleshin.core.utils.navigation.Router
import ru.aleshin.timeplanner.presentation.ui.tabs.TabsScreen
import javax.inject.Inject


interface GlobalNavigationManager {

    fun showTabScreen()

    class Base @Inject constructor(private val router: Router) : GlobalNavigationManager {

        override fun showTabScreen() {
            router.replaceTo(TabsScreen())
        }
    }
}
