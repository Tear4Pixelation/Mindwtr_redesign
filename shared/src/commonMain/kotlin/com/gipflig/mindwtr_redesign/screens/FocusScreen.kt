package com.gipflig.mindwtr_redesign.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.composables.icons.tabler.Tabler
import com.composables.icons.tabler.outline.Bolt
import com.composables.icons.tabler.outline.Book
import com.composables.icons.tabler.outline.Briefcase
import com.composables.icons.tabler.outline.Eye
import com.composables.icons.tabler.outline.Filter
import com.composables.icons.tabler.outline.Focus2
import com.composables.icons.tabler.outline.Heart
import com.composables.icons.tabler.outline.Home
import com.composables.icons.tabler.outline.ListCheck
import com.composables.icons.tabler.outline.Selector
import com.gipflig.mindwtr_redesign.formatToday

// --- Generated code start ---
fun sampleTasks(): List<TaskData> = listOf(
    TaskData(
        name = "Finish quarterly report",
        areaName = "Work",
        areaIcon = Tabler.Outline.Briefcase,
        focused = true,
        contextName = "@computer",
        subtasks = listOf(
            "Gather sales figures" to true,
            "Draft summary" to true,
            "Create charts" to false,
            "Send to manager" to false,
        ),
    ),
    TaskData(
        name = "Book dentist appointment",
        areaName = "Health",
        areaIcon = Tabler.Outline.Heart,
        focused = true,
        contextName = "@phone",
    ),
    TaskData(
        name = "Buy groceries",
        areaName = "Home",
        areaIcon = Tabler.Outline.Home,
        focused = false,
        contextName = "@errands",
        subtasks = listOf(
            "Milk" to false,
            "Eggs" to false,
            "Bread" to false,
            "Vegetables" to false,
            "Coffee" to false,
            "Pasta" to false,
        ),
    ),
    TaskData(
        name = "Reply to client emails",
        areaName = "Work",
        areaIcon = Tabler.Outline.Briefcase,
        focused = false,
        contextName = "@computer",
    ),
    TaskData(
        name = "Plan weekend hike",
        focused = false,
    ),
    TaskData(
        name = "Read chapter 5",
        areaName = "Learning",
        areaIcon = Tabler.Outline.Book,
        focused = false,
        subtasks = listOf(
            "Read first half" to true,
            "Take notes" to false,
        ),
    ),
    TaskData(
        name = "Fix leaking kitchen tap",
        areaName = "Home",
        areaIcon = Tabler.Outline.Home,
        focused = false,
        contextName = "@home",
    ),
)
// --- Generated code end ---


@Composable
fun SectionHeader(sectionName: String, amount: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.height(IntrinsicSize.Min)
    ) {
        Text(
            sectionName,
            style = MaterialTheme.typography.headlineSmall
        )
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = MaterialTheme.colorScheme.surfaceContainer
        ) {
            Box(modifier = Modifier.aspectRatio(1f), contentAlignment = Alignment.Center) {
                Text(amount.toString())
            }
        }
    }
}

@Composable
fun FocusScreen() {
    val tasks = remember { sampleTasks().toMutableStateList() }
    val focusedTasks = tasks.filter { it.focused }
    val unfocusedTasks = tasks.filter { !it.focused }

    Scaffold(topBar = { FocusScreenToolbar() }) { paddingValues ->
        LazyColumn(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp).padding(top = paddingValues.calculateTopPadding()),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
                SectionHeader("Focused", focusedTasks.size)
            }

            items(focusedTasks) {
                key(it.name) {
                    it.Task(onFocusChange = { newFocused -> tasks[tasks.indexOf(it)] = it.copy(focused = newFocused)})
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                SectionHeader("Next Actions", unfocusedTasks.size)
            }

            items(unfocusedTasks) {
                key(it.name) {
                    it.Task(onFocusChange = { newFocused -> tasks[tasks.indexOf(it)] = it.copy(focused = newFocused)})
                }
            }
        }
    }
}

@Composable
fun FocusScreenToolbar() {
    Surface(
        shape = RoundedCornerShape(50),
        color = MaterialTheme.colorScheme.surfaceContainerHighest,
        modifier = Modifier.padding(horizontal = 8.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
        ) {
            Text(formatToday())
            Spacer(modifier = Modifier.weight(1f))
            Icon(Tabler.Outline.Eye, contentDescription = "View options")
            Icon(Tabler.Outline.Filter, contentDescription = "Filters")
            Icon(Tabler.Outline.Selector, contentDescription = "Collapse non-focused")
        }
    }
}


data class TaskData(
    val name: String,
    val areaName: String? = null,
    val areaIcon: ImageVector? = null,  // Mixing state and UI here needs changing
    val focused: Boolean,
    val contextName: String? = null,
    val subtasks: List<Pair<String, Boolean>>? = null,  // Pair of (name, completed)
) {
    @Composable
    fun Task(onClick: () -> Unit = {}, onFocusChange: (Boolean) -> Unit = {}, onAreaClick: () -> Unit = {}, onContextClick: () -> Unit = {}) = FocusScreenTask(
        name = name,
        areaName = areaName,
        areaIcon = areaIcon,
        contextName = contextName,
        focused = focused,
        subtaskAmount = subtasks?.let { Pair(subtasks.count { it.second }, subtasks.size) },
        onClick = onClick, onFocusChange = onFocusChange, onAreaClick = onAreaClick, onContextClick = onContextClick
    )
}

@Composable
fun FocusScreenTask(
    name: String = "Task",
    areaName: String? = "Area",
    areaIcon: ImageVector? = null,
    contextName: String? = null,
    focused: Boolean = false,
    subtaskAmount: Pair<Int, Int>? = null,  // Pair of (completed, total). Total antipattern, of course, but it's fine for now.
    onClick: () -> Unit = {}, onFocusChange: (Boolean) -> Unit = {}, onAreaClick: () -> Unit = {}, onContextClick: () -> Unit = {}
) {
    val connectedCornerRadius = 4.dp

    val containerColor = if (focused) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceContainerHighest
    val contentColor = if (focused) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
    val chipsNeeded = subtaskAmount != null || areaName != null || contextName != null
    val nameContainerShape = if (chipsNeeded) {
        RoundedCornerShape(16.dp, connectedCornerRadius, connectedCornerRadius, connectedCornerRadius)
    } else RoundedCornerShape(16.dp, connectedCornerRadius, connectedCornerRadius, 16.dp)
    val focusContainerShape = if (chipsNeeded) {
        RoundedCornerShape(connectedCornerRadius, 16.dp, connectedCornerRadius, connectedCornerRadius)
    } else RoundedCornerShape(connectedCornerRadius, 16.dp, 16.dp, connectedCornerRadius)

    Column(
        verticalArrangement = Arrangement.spacedBy(2.dp),
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(2.dp), modifier = Modifier.height(IntrinsicSize.Min)) {
            Surface(
                shape = nameContainerShape,
                color = containerColor,
                contentColor = contentColor,
                modifier = Modifier.weight(1f),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(16.dp).clickable(onClick = onClick),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (focused || areaIcon != null) {
                        Icon(areaIcon ?: Tabler.Outline.Bolt, contentDescription = null)
                    }
                    Text(
                        name,
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = if (focused) FontWeight.Bold else FontWeight.Normal)
                    )
                }
            }
            Surface(
                shape = focusContainerShape,
                color = if (focused) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.surfaceContainerHighest,
                modifier = Modifier.fillMaxHeight().aspectRatio(1f),
            ) {
                Box(modifier = Modifier.fillMaxHeight().aspectRatio(1f).clickable { onFocusChange(!focused) }.padding(16.dp), contentAlignment = Alignment.Center) {
                    Icon(Tabler.Outline.Focus2, contentDescription = "Focus Task")
                }
            }
        }

        if (chipsNeeded) Surface(
            shape = RoundedCornerShape(connectedCornerRadius, connectedCornerRadius, 16.dp, 16.dp),
            color = containerColor,
            contentColor = contentColor,
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).clickable(onClick = onClick),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (areaName != null) SuggestionChip(
                    onClick = onAreaClick,
                    label = { Text(areaName, color = contentColor) },
                )

                if (subtaskAmount != null) SuggestionChip(
                    onClick = { },
                    label = {
                        Text(
                            "${subtaskAmount.first}/${subtaskAmount.second}",
                            color = contentColor
                        )
                    },
                    icon = {
                        Icon(
                            Tabler.Outline.ListCheck,
                            contentDescription = "Subtasks completed",
                            tint = contentColor
                        )
                    }
                )

                if (contextName != null) SuggestionChip(
                    onClick = onContextClick,
                    label = { Text(contextName, color = SuggestionChipDefaults.elevatedSuggestionChipColors().labelColor) },
                    colors = SuggestionChipDefaults.elevatedSuggestionChipColors(),
                )
            }
        }
    }
}