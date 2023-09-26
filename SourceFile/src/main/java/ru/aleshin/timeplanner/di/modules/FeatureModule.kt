
package ru.aleshin.timeplanner.di.modules

import dagger.Module
import dagger.Provides
import ru.aleshin.features.analytics.impl.di.AnalyticsFeatureDependencies
import ru.aleshin.features.analytics.impl.di.holder.AnalyticsComponentHolder
import ru.aleshin.features.editor.impl.di.EditorFeatureDependencies
import ru.aleshin.features.editor.impl.di.holder.EditorComponentHolder
import ru.aleshin.features.home.impl.di.HomeFeatureDependencies
import ru.aleshin.features.home.impl.di.holder.HomeComponentHolder
import ru.aleshin.features.settings.impl.di.SettingsFeatureDependencies
import ru.aleshin.features.settings.impl.di.holder.SettingsComponentHolder

/**
 * @author Stanislav Aleshin on 14.02.2023.
 */
@Module
class FeatureModule {

    @Provides
    fun provideHomeFeatureStarter(
        dependencies: HomeFeatureDependencies,
    ) = with(HomeComponentHolder) {
        init(dependencies)
        fetchApi().fetchStarter()
    }

    @Provides
    fun provideAnalyticsFeatureStarter(
        dependencies: AnalyticsFeatureDependencies,
    ) = with(AnalyticsComponentHolder) {
        init(dependencies)
        fetchApi().fetchStarter()
    }

    @Provides
    fun provideEditorFeatureStarter(
        dependencies: EditorFeatureDependencies,
    ) = with(EditorComponentHolder) {
        init(dependencies)
        fetchApi().fetchStarter()
    }

    @Provides
    fun provideSettingsFeatureStarter(
        dependencies: SettingsFeatureDependencies,
    ) = with(SettingsComponentHolder) {
        init(dependencies)
        fetchApi().fetchStarter()
    }
}
