package com.kukurodev.mykukuroaquarium.model.task

import com.kukurodev.mykukuroaquarium.R


object DailyTaskDatabase {
    private val tasks = listOf(
        DailyTaskModel(
            tasks = listOf(
                TaskModel(
                    id = 0,
                    nameResId = R.string.daily_task_feed_fish,
                    type = DailyTaskType.FEED_FISH,
                    target = 3,
                    progress = 0,
                    reward = 20,
                    useMultiplier = true
                ),
                TaskModel(
                    id = 1,
                    nameResId = R.string.daily_task_pop_bubble,
                    type = DailyTaskType.POP_BUBBLE,
                    target = 5,
                    progress = 0,
                    reward = 10,
                    useMultiplier = true
                ),
                TaskModel(
                    id = 2,
                    nameResId = R.string.daily_task_buy_fish,
                    type = DailyTaskType.BUY_FISH,
                    target = 1,
                    progress = 0,
                    reward = 50,
                    useMultiplier = false
                )
            ),
            claimed = false,
            resetAt = 20L
        )
    )

    fun getAllTasks() = tasks
}