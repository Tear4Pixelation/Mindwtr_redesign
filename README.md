## Mindwtr Material 3 redesign concepts
This repo consists of screens and components for a possible redesign of Mindwtr in Material 3 (expressive).

There is no backend or connection between these screens, just sketches of UI.

This is almost entirely handwritten with generated sections being mostly example data and are marked as follows:

```kotlin
// --- Generated code start ---
fun sampleTasks(): List<TaskData> = listOf(
    // Focused
    TaskData("Finish quarterly report", "Work", Tabler.Outline.Briefcase, focused = true, subtaskAmount = 2 to 4),
    TaskData("Book dentist appointment", "Health", Tabler.Outline.Heart, focused = true),

    // Not focused
    TaskData("Buy groceries", "Home", Tabler.Outline.ShoppingCart, focused = false, subtaskAmount = 0 to 6),
    TaskData("Reply to client emails", "Work", Tabler.Outline.Briefcase, focused = false),
    TaskData("Read chapter 5", "Learning", Tabler.Outline.Book, focused = false, subtaskAmount = 1 to 2),
    TaskData("Fix leaking kitchen tap", "Home", Tabler.Outline.Home, focused = false),
    TaskData("Plan weekend hike", "Leisure", Tabler.Outline.Mountain, focused = false),
)
// --- Generated code end ---
```