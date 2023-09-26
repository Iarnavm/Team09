
package ru.aleshin.timeplanner.domain.common

import ru.aleshin.core.utils.handlers.ErrorHandler
import javax.inject.Inject


interface MainErrorHandler : ErrorHandler<MainFailures> {

    class Base @Inject constructor() : MainErrorHandler {
        override fun handle(throwable: Throwable) = when (throwable) {
            else -> MainFailures.OtherError(throwable)
        }
    }
}
