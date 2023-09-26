
package ru.aleshin.timeplanner.presentation.ui.main.viewmodel

import ru.aleshin.core.utils.platform.communications.state.StateCommunicator
import ru.aleshin.timeplanner.presentation.ui.main.contract.MainViewState
import javax.inject.Inject

/**
 * @author Stanislav Aleshin on 14.02.2023.
 */
interface MainStateCommunicator : StateCommunicator<MainViewState> {

    class Base @Inject constructor() : MainStateCommunicator,
        StateCommunicator.Abstract<MainViewState>(defaultState = MainViewState())
}
