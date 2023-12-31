
package ru.aleshin.timeplanner.application

import android.app.Application
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import ru.aleshin.core.ui.theme.tokens.TimePlannerStrings
import ru.aleshin.core.ui.theme.tokens.fetchCoreLanguage
import ru.aleshin.core.ui.theme.tokens.fetchCoreStrings
import ru.aleshin.core.utils.extensions.fetchLocale
import ru.aleshin.core.utils.functional.Constants
import ru.aleshin.core.utils.notifications.parameters.NotificationDefaults
import ru.aleshin.core.utils.notifications.parameters.NotificationPriority
import ru.aleshin.timeplanner.di.component.AppComponent


class TimePlannerApp : Application() {

    val appComponent by lazy {
        AppComponent.create(applicationContext)
    }

    private val notificationCreator by lazy {
        appComponent.fetchNotificationCreator()
    }

    private val coreStrings: TimePlannerStrings
        get() = fetchCoreStrings(fetchCoreLanguage(fetchLocale().language))

    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createTimeTaskNotifyChannel()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createTimeTaskNotifyChannel() = notificationCreator.createNotifyChannel(
        channelId = Constants.Notification.CHANNEL_ID,
        channelName = coreStrings.timeTaskChannelName,
        priority = NotificationPriority.MAX,
        defaults = NotificationDefaults(true, true, true),
    )
}

fun Context.fetchApp(): TimePlannerApp {
    return applicationContext as TimePlannerApp
}

@Composable
fun fetchAppComponent(): AppComponent {
    return LocalContext.current.fetchApp().appComponent
}
