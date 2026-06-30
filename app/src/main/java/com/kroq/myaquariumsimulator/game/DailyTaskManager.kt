package com.kroq.myaquariumsimulator.game

import com.kroq.myaquariumsimulator.R
import com.kroq.myaquariumsimulator.model.PlayerTier
import com.kroq.myaquariumsimulator.model.task.DailyTaskDatabase
import com.kroq.myaquariumsimulator.model.task.DailyTaskModel
import com.kroq.myaquariumsimulator.model.task.DailyTaskType
import com.kroq.myaquariumsimulator.utils.Utils

object DailyTaskManager {
    fun refreshIfNeeded(playerTier: PlayerTier) {
        val current = GameManager.state.dailyTask

        if (current == null || Utils.isExpired(current.resetAt)) {
            createDailyTask(playerTierMultiplier(playerTier))
        }
    }

    fun addProgress(
        type: DailyTaskType,
        amount: Int = 1
    ) {
        val dailyTask = GameManager.state.dailyTask ?: return

        if (dailyTask.claimed) return

        GameManager.update {
            it.copy(
                dailyTask = dailyTask.copy(
                    tasks = dailyTask.tasks.map { task ->

                        if (task.type != type) {
                            task
                        } else {
                            task.copy(
                                progress = (task.progress + amount)
                                    .coerceAtMost(task.target)
                            )
                        }
                    }
                )
            )
        }
    }

    fun claimReward() {
        val dailyTask = GameManager.state.dailyTask ?: return

        if (dailyTask.claimed) return
        if (!dailyTask.isCompleted) return

        CoinManager.addCoins(dailyTask.totalReward)

        GameManager.update {
            it.copy(
                dailyTask = dailyTask.copy(
                    claimed = true
                )
            )
        }

        Utils.showToast(R.string.daily_task_reward_claimed)
    }

    private fun createDailyTask(multiplier: Int = 1) {
        val template = DailyTaskDatabase.getAllTasks().random()
        val dailyTask = createFromTemplate(template, multiplier)

        GameManager.update {
            it.copy(dailyTask = dailyTask)
        }
    }

    private fun createFromTemplate(
        template: DailyTaskModel,
        multiplier: Int
    ): DailyTaskModel {
        return template.copy(
            tasks = template.tasks.map {

                if (!it.useMultiplier) {
                    it
                } else {
                    it.copy(
                        target = it.target * multiplier,
                        reward = it.reward * multiplier
                    )
                }
            },
            claimed = false,
            resetAt = Utils.tomorrowAtMidnight()
        )
    }

    private fun playerTierMultiplier(playerTier: PlayerTier): Int {
        return when (playerTier) {
            PlayerTier.FREE -> 1
            PlayerTier.BRONZE -> 2
            PlayerTier.SILVER -> 3
            PlayerTier.GOLD -> 5
        }
    }
}