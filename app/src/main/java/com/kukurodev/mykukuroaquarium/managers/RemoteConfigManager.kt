package com.kukurodev.mykukuroaquarium.managers

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.remoteconfig.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.kukurodev.mykukuroaquarium.BuildConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class ForceUpdateInfo(
    val isUpdateRequired: Boolean = false,
    val updateUrl: String = "",
    val title: String = "",
    val message: String = ""
)

private data class ForceUpdateConfigJson(
    @SerializedName("min_required_version_code") val minRequiredVersionCode: Long? = null,
    @SerializedName("update_url") val updateUrl: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("message") val message: String? = null
)

object RemoteConfigManager {
    private const val TAG = "RemoteConfigManager"

    private const val KEY_FORCE_UPDATE_CONFIG = "force_update_config"
    private const val KEY_MIN_REQUIRED_VERSION_CODE = "min_required_version_code"
    private const val KEY_UPDATE_URL = "update_url"
    private const val KEY_FORCE_UPDATE_TITLE = "force_update_title"
    private const val KEY_FORCE_UPDATE_MESSAGE = "force_update_message"

    private val _forceUpdateInfo = MutableStateFlow(ForceUpdateInfo())
    val forceUpdateInfo: StateFlow<ForceUpdateInfo> = _forceUpdateInfo.asStateFlow()

    fun init() {
        try {
            val remoteConfig = Firebase.remoteConfig
            val configSettings = remoteConfigSettings {
                minimumFetchIntervalInSeconds = if (BuildConfig.DEBUG) 0L else 3600L
            }
            remoteConfig.setConfigSettingsAsync(configSettings)

            val defaults: Map<String, Any> = mapOf(
                KEY_MIN_REQUIRED_VERSION_CODE to 0L,
                KEY_UPDATE_URL to "https://play.google.com/store/apps/details?id=com.kukurodev.mykukuroaquarium",
                KEY_FORCE_UPDATE_TITLE to "",
                KEY_FORCE_UPDATE_MESSAGE to ""
            )
            remoteConfig.setDefaultsAsync(defaults)

            remoteConfig.fetchAndActivate()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "Remote config fetch succeeded")
                    } else {
                        Log.w(TAG, "Remote config fetch failed")
                    }
                    checkForceUpdate()
                }
        } catch (e: Exception) {
            Log.e(TAG, "Error initializing Remote Config", e)
        }
    }

    fun checkForceUpdate() {
        try {
            val remoteConfig = Firebase.remoteConfig
            val currentVersionCode = BuildConfig.VERSION_CODE.toLong()

            var minRequiredVersionCode = 0L
            var updateUrl = ""
            var title = ""
            var message = ""

            val jsonString = remoteConfig.getString(KEY_FORCE_UPDATE_CONFIG)
            if (jsonString.isNotEmpty()) {
                try {
                    val configJson = Gson().fromJson(jsonString, ForceUpdateConfigJson::class.java)
                    minRequiredVersionCode = configJson?.minRequiredVersionCode ?: 0L
                    updateUrl = configJson?.updateUrl.orEmpty()
                    title = configJson?.title.orEmpty()
                    message = configJson?.message.orEmpty()
                } catch (e: Exception) {
                    Log.e(TAG, "Error parsing force_update_config JSON", e)
                }
            }

            if (minRequiredVersionCode == 0L) {
                minRequiredVersionCode = remoteConfig.getLong(KEY_MIN_REQUIRED_VERSION_CODE)
            }
            if (updateUrl.isEmpty()) {
                updateUrl = remoteConfig.getString(KEY_UPDATE_URL)
            }
            if (title.isEmpty()) {
                title = remoteConfig.getString(KEY_FORCE_UPDATE_TITLE)
            }
            if (message.isEmpty()) {
                message = remoteConfig.getString(KEY_FORCE_UPDATE_MESSAGE)
            }

            if (updateUrl.isEmpty()) {
                updateUrl = "https://play.google.com/store/apps/details?id=com.kukurodev.mykukuroaquarium"
            }

            val isRequired = minRequiredVersionCode > currentVersionCode

            _forceUpdateInfo.value = ForceUpdateInfo(
                isUpdateRequired = isRequired,
                updateUrl = updateUrl,
                title = title,
                message = message
            )
            Log.d(TAG, "Force update check: isRequired=$isRequired, minRequired=$minRequiredVersionCode, current=$currentVersionCode")
        } catch (e: Exception) {
            Log.e(TAG, "Error checking force update", e)
        }
    }
}
