package com.kukurodev.mykukuroaquarium.model.task

data class TaskModel(
    val id: Int,
    val nameResId: Int,
    val type: DailyTaskType,
    val target: Int,
    val progress: Int = 0,
    val reward: Int,
    val useMultiplier: Boolean = true
)