
package ru.aleshin.timeplanner.presentation.ui.tabs.contract

import kotlinx.parcelize.Parcelize
import ru.aleshin.core.utils.platform.screenmodel.contract.*
import ru.aleshin.timeplanner.presentation.ui.tabs.views.TabsBottomBarItems


@Parcelize
data class TabsViewState(
    val bottomBarItem: TabsBottomBarItems = TabsBottomBarItems.HOME,
) : BaseViewState

sealed class TabsEvent : BaseEvent {
    object Init : TabsEvent()
    object SelectedHomeTab : TabsEvent()
    object SelectedMainScreen : TabsEvent()
    object SelectedTemplateScreen : TabsEvent()
    object SelectedCategoriesScreen : TabsEvent()
    object SelectedAnalyticsTab : TabsEvent()
    object SelectedSettingsTab : TabsEvent()
}

sealed class TabsEffect : EmptyUiEffect

sealed class TabsAction : TabsEffect(), BaseAction {
    data class ChangeNavItems(val item: TabsBottomBarItems) : TabsAction()
}
