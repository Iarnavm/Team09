
package ru.aleshin.timeplanner.presentation.ui.main.viewmodel

import kotlinx.coroutines.flow.flow
import ru.aleshin.core.utils.functional.handle
import ru.aleshin.core.utils.platform.screenmodel.work.ActionResult
import ru.aleshin.core.utils.platform.screenmodel.work.FlowWorkProcessor
import ru.aleshin.core.utils.platform.screenmodel.work.WorkCommand
import ru.aleshin.features.settings.impl.presentation.mappers.mapToUi
import ru.aleshin.timeplanner.domain.interactors.SettingsInteractor
import ru.aleshin.timeplanner.presentation.ui.main.contract.MainAction
import ru.aleshin.timeplanner.presentation.ui.main.contract.MainEffect
import javax.inject.Inject

/**
 * @author Stanislav Aleshin on 14.02.2023.
 */
interface SettingsWorkProcessor : FlowWorkProcessor<SettingsWorkCommand, MainAction, MainEffect> {

    class Base @Inject constructor(
        private val settingsInteractor: SettingsInteractor,
    ) : SettingsWorkProcessor {

        override suspend fun work(command: SettingsWorkCommand) = when (command) {
            SettingsWorkCommand.LoadThemeSettings -> loadThemeSettingsWork()
        }

        private fun loadThemeSettingsWork() = flow {
            settingsInteractor.fetchThemeSettings().collect { settingsEither ->
                settingsEither.handle(
                    onLeftAction = { error(RuntimeException("Error get ThemeSettings -> $it")) },
                    onRightAction = {
                        val action = MainAction.ChangeThemeSettings(
                            language = it.language.mapToUi(),
                            themeColors = it.themeColors.mapToUi(),
                            enableDynamicColors = it.isDynamicColorEnable,
                        )
                        emit(ActionResult(action))
                    },
                )
            }
        }
    }
}

sealed class SettingsWorkCommand : WorkCommand {
    object LoadThemeSettings : SettingsWorkCommand()
}
