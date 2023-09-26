
package ru.aleshin.timeplanner.di.modules

import dagger.Binds
import dagger.Module
import ru.aleshin.features.analytics.impl.di.AnalyticsFeatureDependencies
import ru.aleshin.features.editor.impl.di.EditorFeatureDependencies
import ru.aleshin.features.home.impl.di.HomeFeatureDependencies
import ru.aleshin.features.settings.impl.di.SettingsFeatureDependencies
import ru.aleshin.timeplanner.di.component.AppComponent

/**
 * @author Stanislav Aleshin on 14.02.2023.
 */
@Module
interface DependenciesModule {

    @Binds
    fun bindHomeFeatureDependencies(component: AppComponent): HomeFeatureDependencies

    @Binds
    fun bindEditorFeatureDependencies(component: AppComponent): EditorFeatureDependencies

    @Binds
    fun bindAnalyticsFeatureDependencies(component: AppComponent): AnalyticsFeatureDependencies

    @Binds
    fun bindSettingsFeatureDependencies(component: AppComponent): SettingsFeatureDependencies
}
