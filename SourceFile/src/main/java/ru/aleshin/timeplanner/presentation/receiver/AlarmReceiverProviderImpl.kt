
package ru.aleshin.timeplanner.presentation.receiver

import android.content.Context
import android.content.Intent
import ru.aleshin.core.utils.functional.Constants
import ru.aleshin.features.editor.api.presentation.AlarmReceiverProvider
import ru.aleshin.features.home.api.domain.entities.template.RepeatTime
import java.util.Date
import javax.inject.Inject


class AlarmReceiverProviderImpl @Inject constructor(
    private val context: Context,
) : AlarmReceiverProvider {

    override fun provideReceiverIntent(
        category: String,
        subCategory: String,
        icon: Int?,
        appIcon: Int,
        time: Date?,
        templateId: Int?,
        repeatTime: RepeatTime?,
    ) = Intent(context, TimeTaskAlarmReceiver::class.java).apply {
        action = Constants.Alarm.ALARM_NOTIFICATION_ACTION
        putExtra(Constants.Alarm.NOTIFICATION_CATEGORY, category)
        putExtra(Constants.Alarm.NOTIFICATION_SUBCATEGORY, subCategory)
        putExtra(Constants.Alarm.NOTIFICATION_ICON, icon)
        putExtra(Constants.Alarm.APP_ICON, appIcon)
        if (time != null) putExtra(Constants.Alarm.REPEAT_TIME, time.time)
        if (repeatTime != null) putExtra(Constants.Alarm.REPEAT_TYPE, repeatTime.type.name)
        if (templateId != null) putExtra(Constants.Alarm.TEMPLATE_ID, templateId)
        when (repeatTime) {
            is RepeatTime.WeekDays -> {
                putExtra(Constants.Alarm.WEEK_DAY, repeatTime.day.name)
            }
            is RepeatTime.MonthDay -> {
                putExtra(Constants.Alarm.DAY_OF_MONTH, repeatTime.dayNumber)
            }
            is RepeatTime.WeekDayInMonth -> {
                putExtra(Constants.Alarm.WEEK_DAY, repeatTime.day.name)
                putExtra(Constants.Alarm.WEEK_NUMBER, repeatTime.weekNumber)
            }
            is RepeatTime.YearDay -> {
                putExtra(Constants.Alarm.DAY_OF_MONTH, repeatTime.dayNumber)
                putExtra(Constants.Alarm.MONTH, repeatTime.month.name)
            }
            null -> {}
        }
    }
}
