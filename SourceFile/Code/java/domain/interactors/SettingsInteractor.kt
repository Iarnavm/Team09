
package ru.aleshin.timeplanner.domain.interactors

import kotlinx.coroutines.flow.Flow
import ru.aleshin.core.utils.functional.Either
import ru.aleshin.features.settings.api.domain.entities.ThemeSettings
import ru.aleshin.features.settings.api.domain.repositories.ThemeSettingsRepository
import ru.aleshin.timeplanner.domain.common.MainEitherWrapper
import ru.aleshin.timeplanner.domain.common.MainFailures
import javax.inject.Inject


interface SettingsInteractor {

    suspend fun fetchThemeSettings(): Flow<Either<MainFailures, ThemeSettings>>

    class Base @Inject constructor(
        private val settingsRepository: ThemeSettingsRepository,
        private val eitherWrapper: MainEitherWrapper,
    ) : SettingsInteractor {

        override suspend fun fetchThemeSettings() = eitherWrapper.wrapFlow {
            settingsRepository.fetchSettingsFlow()
        }
    }
}
