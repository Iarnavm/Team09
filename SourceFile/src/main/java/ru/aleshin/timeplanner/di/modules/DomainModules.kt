
package ru.aleshin.timeplanner.di.modules

import dagger.Binds
import dagger.Module
import ru.aleshin.timeplanner.domain.common.MainEitherWrapper
import ru.aleshin.timeplanner.domain.common.MainErrorHandler
import ru.aleshin.timeplanner.domain.interactors.SettingsInteractor
import javax.inject.Singleton

/**
 * @author Stanislav Aleshin on 17.02.2023.
 */
@Module
interface DomainModules {

    @Binds
    @Singleton
    fun bindSettingsInteractor(interactor: SettingsInteractor.Base): SettingsInteractor

    @Binds
    fun bindMainEitherWrapper(wrapper: MainEitherWrapper.Base): MainEitherWrapper

    @Binds
    fun bindMainErrorHandler(handler: MainErrorHandler.Base): MainErrorHandler
}
