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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
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
fun sampleTasksFocused(): List<TaskData> = listOf(
    TaskData("Finish quarterly report", "Work", Tabler.Outline.Briefcase, focused = true, subtaskAmount = 2 to 4),
    TaskData("Book dentist appointment", "Health", Tabler.Outline.Heart, focused = true),
)

fun sampleTasksUnfocused(): List<TaskData> = listOf(
    TaskData("Buy groceries", "Home", Tabler.Outline.Home, focused = false, subtaskAmount = 0 to 6),
    TaskData("Reply to client emails", "Work", Tabler.Outline.Briefcase, focused = false),
    TaskData("Plan weekend hike", focused = false),
    TaskData("Read chapter 5", "Learning", Tabler.Outline.Book, focused = false, subtaskAmount = 1 to 2),
    TaskData("Fix leaking kitchen tap", "Home", Tabler.Outline.Home, focused = false),
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
    Scaffold(topBar = { FocusScreenToolbar() }) { paddingValues ->
        LazyColumn(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp).padding(top = paddingValues.calculateTopPadding()),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
                SectionHeader("Focused", sampleTasksFocused().size)
            }

            items(sampleTasksFocused()) {
                key(it.name) {
                    it.Task()
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                SectionHeader("Next Actions", sampleTasksUnfocused().size)
            }

            items(sampleTasksUnfocused()) {
                key(it.name) {
                    it.Task()
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
    val subtaskAmount: Pair<Int, Int>? = null,
) {
    @Composable
    fun Task() = FocusScreenTask(name, areaName, areaIcon, focused, subtaskAmount)
}

@Composable
fun FocusScreenTask(
    name: String = "Task",
    areaName: String? = "Area",
    areaIcon: ImageVector? = null,
    focused: Boolean = false,
    subtaskAmount: Pair<Int, Int>? = null,  // Pair of (completed, total). Total antipattern, of course, but it's fine for now.
) {
    val connectedCornerRadius = 4.dp

    val containerColor = if (focused) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceContainerHighest
    val contentColor = if (focused) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
    val chipsNeeded = subtaskAmount != null || areaName != null
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
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
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
                modifier = Modifier.fillMaxHeight().aspectRatio(1f).clickable { },
            ) {
                Box(modifier = Modifier.fillMaxHeight().aspectRatio(1f).padding(16.dp), contentAlignment = Alignment.Center) {
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
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (areaName != null) SuggestionChip(
                    onClick = { },
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

            }
        }
    }
}