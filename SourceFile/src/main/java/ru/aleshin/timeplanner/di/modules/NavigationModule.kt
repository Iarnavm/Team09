
package ru.aleshin.timeplanner.di.modules

import dagger.Module
import dagger.Provides
import ru.aleshin.core.utils.navigation.CommandBuffer
import ru.aleshin.core.utils.navigation.Router
import ru.aleshin.core.utils.navigation.TabRouter
import ru.aleshin.core.utils.navigation.navigator.NavigatorManager
import ru.aleshin.timeplanner.di.annotation.GlobalNavigation
import ru.aleshin.timeplanner.di.annotation.TabNavigation
import javax.inject.Singleton

/**
 * @author Stanislav Aleshin on 17.02.2023.
 */
@Module
class NavigationModule {

    // Global Navigator

    @GlobalNavigation
    @Provides
    @Singleton
    fun provideGlobalNavigatorManager(
        @GlobalNavigation commandBuffer: CommandBuffer,
    ): NavigatorManager = NavigatorManager.Base(commandBuffer)

    @GlobalNavigation
    @Provides
    @Singleton
    fun provideGlobalCommandBuffer(): CommandBuffer = CommandBuffer.Base()

    @Provides
    @Singleton
    fun provideGlobalRouter(
        @GlobalNavigation commandBuffer: CommandBuffer,
    ): Router = Router.Base(commandBuffer)

    // Tab Navigator

    @TabNavigation
    @Provides
    @Singleton
    fun provideTabNavigatorManager(
        @TabNavigation commandBuffer: CommandBuffer,
    ): NavigatorManager = NavigatorManager.Base(commandBuffer)

    @TabNavigation
    @Provides
    @Singleton
    fun provideTabCommandBuffer(): CommandBuffer = CommandBuffer.Base()

    @Provides
    @Singleton
    fun provideTabRouter(
        @TabNavigation commandBuffer: CommandBuffer,
    ): TabRouter = TabRouter.Base(Router.Base(commandBuffer))
}
