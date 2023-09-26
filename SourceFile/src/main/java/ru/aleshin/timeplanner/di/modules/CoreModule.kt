
package ru.aleshin.timeplanner.di.modules

import dagger.Binds
import dagger.Module
import ru.aleshin.core.utils.managers.CoroutineManager
import ru.aleshin.core.utils.managers.DateManager
import ru.aleshin.core.utils.managers.TimeOverlayManager
import ru.aleshin.core.utils.notifications.NotificationCreator
import ru.aleshin.features.editor.api.presentation.AlarmReceiverProvider
import ru.aleshin.features.editor.api.presentation.TemplatesAlarmManager
import ru.aleshin.features.editor.api.presentation.TimeTaskAlarmManager
import ru.aleshin.features.home.api.domain.common.ScheduleStatusChecker
import ru.aleshin.features.home.api.domain.common.TimeTaskStatusChecker
import ru.aleshin.timeplanner.navigation.GlobalNavigationManager
import ru.aleshin.timeplanner.navigation.TabNavigationManager
import ru.aleshin.timeplanner.presentation.receiver.AlarmReceiverProviderImpl
import javax.inject.Singleton


@Module
interface CoreModule {

    @Binds
    fun bindAlarmReceiverProvider(provider: AlarmReceiverProviderImpl): AlarmReceiverProvider

    @Binds
    fun bindNotificationCreator(creator: NotificationCreator.Base): NotificationCreator

    @Binds
    @Singleton
    fun bindCoroutineManager(manager: CoroutineManager.Base): CoroutineManager

    @Binds
    @Singleton
    fun bindAppNavigationManager(manager: GlobalNavigationManager.Base): GlobalNavigationManager

    @Binds
    @Singleton
    fun bindTabNavigationManager(manager: TabNavigationManager.Base): TabNavigationManager

    @Binds
    fun bindTimeTaskAlarmManager(manager: TimeTaskAlarmManager.Base): TimeTaskAlarmManager

    @Binds
    fun bindTemplatesAlarmManager(manager: TemplatesAlarmManager.Base): TemplatesAlarmManager

    @Binds
    @Singleton
    fun bindTimeOverlayManager(manager: TimeOverlayManager.Base): TimeOverlayManager

    @Binds
    fun bindTimeTaskStatusChecker(checker: TimeTaskStatusChecker.Base): TimeTaskStatusChecker

    @Binds
    fun bindScheduleStatusChecker(checker: ScheduleStatusChecker.Base): ScheduleStatusChecker

    @Binds
    fun bindDateManager(interactor: DateManager.Base): DateManager
}
