
package ru.aleshin.timeplanner.domain.common

import kotlinx.parcelize.Parcelize
import ru.aleshin.core.utils.functional.DomainFailures


@Parcelize
sealed class MainFailures : DomainFailures {
    data class OtherError(val throwable: Throwable) : MainFailures()
}
