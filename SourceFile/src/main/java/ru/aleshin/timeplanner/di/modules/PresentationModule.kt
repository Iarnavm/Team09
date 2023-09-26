
package ru.aleshin.timeplanner.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cafe.adriel.voyager.core.model.ScreenModel
import dagger.Binds
import dagger.Module
import ru.aleshin.core.utils.di.ScreenModelKey
import ru.aleshin.timeplanner.presentation.ui.main.viewmodel.*
import ru.aleshin.timeplanner.presentation.ui.tabs.screenmodel.TabScreenModel
import ru.aleshin.timeplanner.presentation.ui.tabs.screenmodel.TabsStateCommunicator
import javax.inject.Singleton

/**
 * @author Stanislav Aleshin on 14.02.2023.
 */
@Module
interface PresentationModule {

    // Main ViewModel

    @Binds
    fun bindMainViewModelFactory(factory: MainViewModel.Factory): ViewModelProvider.Factory

    @Binds
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @Singleton
    fun bindMainStateCommunicator(communicator: MainStateCommunicator.Base): MainStateCommunicator

    @Binds
    fun bindSettingsWorkProcessor(processor: SettingsWorkProcessor.Base): SettingsWorkProcessor

    @Binds
    fun bindNavigationWorkProcessor(processor: NavigationWorkProcessor.Base): NavigationWorkProcessor

    // Tabs ScreenModel

    @Binds
    @ScreenModelKey(TabScreenModel::class)
    fun bindTabsScreenModel(screenModel: TabScreenModel): ScreenModel

    @Binds
    @Singleton
    fun bindTabsStateCommunicator(communicator: TabsStateCommunicator.Base): TabsStateCommunicator
}
