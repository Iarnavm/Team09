
package ru.aleshin.timeplanner.presentation.ui.tabs.views

import androidx.compose.foundation.layout.width
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import ru.aleshin.core.ui.theme.TimePlannerRes
import ru.aleshin.core.ui.views.DrawerItems
import ru.aleshin.core.ui.views.DrawerLogoSection
import ru.aleshin.core.utils.managers.DrawerItem
import ru.aleshin.core.utils.managers.DrawerManager
import ru.aleshin.core.utils.managers.LocalDrawerManager

/**
 * @author Stanislav Aleshin on 03.05.2023.
 */
@Composable
fun HomeNavigationDrawer(
    drawerState: DrawerState,
    drawerManager: DrawerManager,
    isAlwaysSelected: Boolean,
    onItemSelected: (HomeDrawerItems) -> Unit,
    content: @Composable () -> Unit,
) {
    val selectedItem by drawerManager.selectedItem.collectAsState(0)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = drawerState.isOpen,
        drawerContent = {
            ModalDrawerSheet {
                DrawerLogoSection(
                    logoIcon = painterResource(id = TimePlannerRes.icons.logo),
                    description = TimePlannerRes.strings.appName,
                )
                DrawerItems(
                    modifier = Modifier.width(300.dp),
                    selectedItemIndex = selectedItem,
                    items = HomeDrawerItems.values(),
                    isAlwaysSelected = isAlwaysSelected,
                    onItemSelected = { item ->
                        onItemSelected(item)
                        scope.launch { drawerState.close() }
                    },
                )
            }
        },
    ) {
        CompositionLocalProvider(
            LocalDrawerManager provides drawerManager,
            content = content,
        )
    }
}

enum class HomeDrawerItems : DrawerItem {
    MAIN {
        override val icon: Int @Composable get() = TimePlannerRes.icons.schedulerIcon
        override val title: String @Composable get() = TimePlannerRes.strings.mainDrawerTitle
    },
    TEMPLATES {
        override val icon: Int @Composable get() = TimePlannerRes.icons.template
        override val title: String @Composable get() = TimePlannerRes.strings.templateDrawerTitle
    },
    CATEGORIES {
        override val icon: Int @Composable get() = TimePlannerRes.icons.categoriesIcon
        override val title: String @Composable get() = TimePlannerRes.strings.categoriesDrawerTitle
    },
}
