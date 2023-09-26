
package ru.aleshin.timeplanner.presentation.ui.main.contract

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ru.aleshin.core.ui.theme.material.ThemeUiType
import ru.aleshin.core.ui.theme.tokens.LanguageUiType
import ru.aleshin.core.utils.platform.screenmodel.contract.*

/**
 * @author Stanislav Aleshin on 14.02.2023.
 */
@Parcelize
data class MainViewState(
    val language: LanguageUiType = LanguageUiType.DEFAULT,
    val colors: ThemeUiType = ThemeUiType.DEFAULT,
    val isEnableDynamicColors: Boolean = false,
) : BaseViewState, Parcelable

sealed class MainEvent : BaseEvent {
    object Init : MainEvent()
}

sealed class MainEffect : EmptyUiEffect

sealed class MainAction : MainEffect(), BaseAction {
    object Navigate : MainAction()
    data class ChangeThemeSettings(
        val language: LanguageUiType,
        val themeColors: ThemeUiType,
        val enableDynamicColors: Boolean,
    ) : MainAction()
}
