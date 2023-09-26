
package ru.aleshin.timeplanner.di.component

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.aleshin.core.utils.navigation.navigator.NavigatorManager
import ru.aleshin.core.utils.notifications.NotificationCreator
import ru.aleshin.features.analytics.impl.di.AnalyticsFeatureDependencies
import ru.aleshin.features.editor.impl.di.EditorFeatureDependencies
import ru.aleshin.features.home.impl.di.HomeFeatureDependencies
import ru.aleshin.features.settings.impl.di.SettingsFeatureDependencies
import ru.aleshin.timeplanner.di.annotation.TabNavigation
import ru.aleshin.timeplanner.di.modules.*
import ru.aleshin.timeplanner.presentation.ui.main.MainActivity
import ru.aleshin.timeplanner.presentation.ui.tabs.screenmodel.TabScreenModel
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        DataBaseModule::class,
        DataModule::class,
        NavigationModule::class,
        CoreModule::class,
        PresentationModule::class,
        DomainModules::class,
        DependenciesModule::class,
        FeatureModule::class,
    ],
)
interface AppComponent :
    HomeFeatureDependencies,
    SettingsFeatureDependencies,
    EditorFeatureDependencies,
    AnalyticsFeatureDependencies {

    @TabNavigation
    fun fetchTabNavigatorManager(): NavigatorManager
    fun fetchTabScreenModel(): TabScreenModel
    fun fetchNotificationCreator(): NotificationCreator
    fun inject(activity: MainActivity)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applicationContext(context: Context): Builder
        fun navigationModule(module: NavigationModule): Builder
        fun featureModule(module: FeatureModule): Builder
        fun dataBaseModule(module: DataBaseModule): Builder
        fun build(): AppComponent
    }

    companion object {
        fun create(context: Context): AppComponent {
            return DaggerAppComponent.builder()
                .applicationContext(context)
                .navigationModule(NavigationModule())
                .featureModule(FeatureModule())
                .dataBaseModule(DataBaseModule())
                .build()
        }
    }
}
