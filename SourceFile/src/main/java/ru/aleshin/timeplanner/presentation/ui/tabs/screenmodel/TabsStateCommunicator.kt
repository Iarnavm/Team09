
package ru.aleshin.timeplanner.presentation.ui.tabs.screenmodel

import ru.aleshin.core.utils.platform.communications.state.StateCommunicator
import ru.aleshin.timeplanner.presentation.ui.tabs.contract.TabsViewState
import javax.inject.Inject
 
interface TabsStateCommunicator : StateCommunicator<TabsViewState> {

    class Base @Inject constructor() : TabsStateCommunicator,
        StateCommunicator.Abstract<TabsViewState>(defaultState = TabsViewState())
}
