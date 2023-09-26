
package ru.aleshin.timeplanner.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.aleshin.features.home.api.data.datasources.categories.CategoriesLocalDataSource
import ru.aleshin.features.home.api.data.datasources.categories.MainCategoriesDao
import ru.aleshin.features.home.api.data.datasources.schedules.SchedulesDao
import ru.aleshin.features.home.api.data.datasources.schedules.SchedulesDataBase
import ru.aleshin.features.home.api.data.datasources.schedules.SchedulesLocalDataSource
import ru.aleshin.features.home.api.data.datasources.subcategories.SubCategoriesDao
import ru.aleshin.features.home.api.data.datasources.subcategories.SubCategoriesLocalDataSource
import ru.aleshin.features.home.api.data.datasources.templates.TemplatesDao
import ru.aleshin.features.home.api.data.datasources.templates.TemplatesLocalDataSource
import ru.aleshin.features.settings.api.data.datasources.SettingsDataBase
import ru.aleshin.features.settings.api.data.datasources.tasks.TasksSettingsDao
import ru.aleshin.features.settings.api.data.datasources.tasks.TasksSettingsLocalDataSource
import ru.aleshin.features.settings.api.data.datasources.theme.ThemeSettingsDao
import ru.aleshin.features.settings.api.data.datasources.theme.ThemeSettingsLocalDataSource
import javax.inject.Singleton


@Module
class DataBaseModule {

    // LocalDataSources

    @Provides
    @Singleton
    fun provideTemplatesLocalDataSource(
        dao: TemplatesDao,
    ): TemplatesLocalDataSource = TemplatesLocalDataSource.Base(dao)

    @Provides
    @Singleton
    fun provideThemeSettingsLocalDataSource(
        dao: ThemeSettingsDao,
    ): ThemeSettingsLocalDataSource = ThemeSettingsLocalDataSource.Base(dao)

    @Provides
    @Singleton
    fun provideTasksSettingsLocalDataSource(
        dao: TasksSettingsDao,
    ): TasksSettingsLocalDataSource = TasksSettingsLocalDataSource.Base(dao)

    @Provides
    @Singleton
    fun provideCategoriesLocalDataSource(
        dao: MainCategoriesDao,
    ): CategoriesLocalDataSource = CategoriesLocalDataSource.Base(dao)

    @Provides
    @Singleton
    fun provideSubCategoriesLocalDataSource(
        dao: SubCategoriesDao,
    ): SubCategoriesLocalDataSource = SubCategoriesLocalDataSource.Base(dao)

    @Provides
    @Singleton
    fun provideSchedulesLocalDataSource(
        schedulesDao: SchedulesDao,
    ): SchedulesLocalDataSource = SchedulesLocalDataSource.Base(schedulesDao)

    // Dao

    @Provides
    @Singleton
    fun provideThemeSettingsDao(dataBase: SettingsDataBase): ThemeSettingsDao =
        dataBase.fetchThemeSettingsDao()

    @Provides
    @Singleton
    fun provideTasksSettingsDao(dataBase: SettingsDataBase): TasksSettingsDao =
        dataBase.fetchTasksSettingsDao()

    @Provides
    @Singleton
    fun provideTemplatesDao(dataBase: SchedulesDataBase): TemplatesDao =
        dataBase.fetchTemplatesDao()

    @Provides
    @Singleton
    fun provideMainCategoriesDao(dataBase: SchedulesDataBase): MainCategoriesDao =
        dataBase.fetchMainCategoriesDao()

    @Provides
    @Singleton
    fun provideSubCategoriesDao(dataBase: SchedulesDataBase): SubCategoriesDao =
        dataBase.fetchSubCategoriesDao()

    @Provides
    @Singleton
    fun provideScheduleDao(dataBase: SchedulesDataBase): SchedulesDao =
        dataBase.fetchSchedulesDao()

    // DataBases

    @Provides
    @Singleton
    fun provideSettingsDataBase(
        context: Context,
    ): SettingsDataBase = Room.databaseBuilder(
        context = context,
        klass = SettingsDataBase::class.java,
        name = SettingsDataBase.NAME,
    ).createFromAsset("database/settings_prepopulated.db")
        .addMigrations(SettingsDataBase.MIGRATION_1_2)
        .addMigrations(SettingsDataBase.MIGRATION_2_3)
        .build()

    @Provides
    @Singleton
    fun provideSchedulesDataBase(
        context: Context,
    ) = Room.databaseBuilder(
        context = context,
        klass = SchedulesDataBase::class.java,
        name = SchedulesDataBase.NAME,
    ).createFromAsset("database/categories_prepopulate.db")
        .addMigrations(SchedulesDataBase.MIGRATE_2_3)
        .addMigrations(SchedulesDataBase.MIGRATE_4_5)
        .addMigrations(SchedulesDataBase.MIGRATE_5_6)
        .build()
}
