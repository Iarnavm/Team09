
package ru.aleshin.timeplanner.domain.common

import ru.aleshin.core.utils.wrappers.FlowEitherWrapper
import javax.inject.Inject


interface MainEitherWrapper : FlowEitherWrapper<MainFailures> {

    class Base @Inject constructor(errorHandler: MainErrorHandler) : MainEitherWrapper,
        FlowEitherWrapper.Abstract<MainFailures>(errorHandler)
}
