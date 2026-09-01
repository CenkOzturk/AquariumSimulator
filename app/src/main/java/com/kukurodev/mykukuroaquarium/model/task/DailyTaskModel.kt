package com.kukurodev.mykukuroaquarium.model.task

data class DailyTaskModel(
    val tasks: List<TaskModel>,
    val claimed: Boolean = false,
    val resetAt: Long
) {
    val totalReward: Int
        get() = tasks.sumOf { it.reward }

    val isCompleted: Boolean
        get() = tasks.all { it.progress >= it.target }
}