
package ru.aleshin.timeplanner.presentation.ui.tabs.screenmodel

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import ru.aleshin.core.utils.managers.CoroutineManager
import ru.aleshin.core.utils.platform.communications.state.EffectCommunicator
import ru.aleshin.core.utils.platform.screenmodel.BaseScreenModel
import ru.aleshin.core.utils.platform.screenmodel.work.WorkScope
import ru.aleshin.features.home.api.navigation.HomeScreens
import ru.aleshin.timeplanner.application.fetchAppComponent
import ru.aleshin.timeplanner.navigation.TabNavigationManager
import ru.aleshin.timeplanner.presentation.ui.tabs.contract.TabsAction
import ru.aleshin.timeplanner.presentation.ui.tabs.contract.TabsEffect
import ru.aleshin.timeplanner.presentation.ui.tabs.contract.TabsEvent
import ru.aleshin.timeplanner.presentation.ui.tabs.contract.TabsViewState
import ru.aleshin.timeplanner.presentation.ui.tabs.views.TabsBottomBarItems
import javax.inject.Inject

/**
 * @author Stanislav Aleshin on 18.02.2023.
 */
class TabScreenModel @Inject constructor(
    private val navigationManager: TabNavigationManager,
    communicator: TabsStateCommunicator,
    coroutineManager: CoroutineManager,
) : BaseScreenModel<TabsViewState, TabsEvent, TabsAction, TabsEffect>(
    stateCommunicator = communicator,
    effectCommunicator = EffectCommunicator.Empty(),
    coroutineManager = coroutineManager,
) {

    init {
        dispatchEvent(TabsEvent.Init)
    }

    override suspend fun WorkScope<TabsViewState, TabsAction, TabsEffect>.handleEvent(
        event: TabsEvent,
    ) = when (event) {
        TabsEvent.Init -> navigate(TabsBottomBarItems.HOME) {
            showHomeFeature(HomeScreens.Home, isRoot = true)
        }
        TabsEvent.SelectedHomeTab -> navigate(TabsBottomBarItems.HOME) {
            showHomeFeature(null)
        }
        TabsEvent.SelectedMainScreen -> navigate(TabsBottomBarItems.HOME) {
            showHomeFeature(HomeScreens.Home)
        }
        TabsEvent.SelectedTemplateScreen -> navigate(TabsBottomBarItems.HOME) {
            showHomeFeature(HomeScreens.Templates)
        }
        TabsEvent.SelectedCategoriesScreen -> navigate(TabsBottomBarItems.HOME) {
            showHomeFeature(HomeScreens.Categories)
        }
        TabsEvent.SelectedAnalyticsTab -> navigate(TabsBottomBarItems.ANALYTICS) {
            showAnalyticsFeature()
        }
        TabsEvent.SelectedSettingsTab -> navigate(TabsBottomBarItems.SETTINGS) {
            showSettingsFeature()
        }
    }

    override suspend fun reduce(
        action: TabsAction,
        currentState: TabsViewState,
    ) = when (action) {
        is TabsAction.ChangeNavItems -> currentState.copy(
            bottomBarItem = action.item,
        )
    }

    private suspend fun WorkScope<TabsViewState, TabsAction, TabsEffect>.navigate(
        bottomItem: TabsBottomBarItems,
        onAction: TabNavigationManager.() -> Unit,
    ) = sendAction(TabsAction.ChangeNavItems(item = bottomItem)).apply {
        onAction(navigationManager)
    }
}

@Composable
fun Screen.rememberTabsScreenModel(): TabScreenModel {
    val component = fetchAppComponent()
    return rememberScreenModel { component.fetchTabScreenModel() }
}
