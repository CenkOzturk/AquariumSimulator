package com.kroq.myaquariumsimulator.model.task

object DailyTaskDatabase {

    private val tasks = listOf(
        DailyTaskModel(
            tasks = listOf(
                TaskModel(
                    id = 0,
                    type = DailyTaskType.FEED_FISH,
                    target = 3,
                    progress = 2,
                    reward = 20,
                    useMultiplier = true
                ),
                TaskModel(
                    id = 1,
                    type = DailyTaskType.POP_BUBBLE,
                    target = 5,
                    progress = 4,
                    reward = 10,
                    useMultiplier = true
                ),
                TaskModel(
                    id = 2,
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