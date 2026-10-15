# ============================================================================
# R8 / ProGuard Configuration - AquaVille
# ============================================================================

# Preserve line numbers and source file names for readable Crashlytics stack traces
-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile
-keepattributes *Annotation*,Signature,InnerClasses,EnclosingMethod

# ----------------------------------------------------------------------------
# Gson Models (CRITICAL for JSON serialization / deserialization)
# ----------------------------------------------------------------------------
-keepclassmembers class * {
    @com.google.gson.annotations.SerializedName <fields>;
}
-keep class com.google.gson.** { *; }

# Keep all Game Models and State classes serialized with Gson
-keep class com.kukurodev.mykukuroaquarium.model.** { *; }
-keepclassmembers class com.kukurodev.mykukuroaquarium.model.** { *; }

# ----------------------------------------------------------------------------
# Firebase & Google Play Services
# ----------------------------------------------------------------------------
-keep class com.google.firebase.** { *; }
-dontwarn com.google.firebase.**

-keep class com.google.android.gms.** { *; }
-dontwarn com.google.android.gms.**

# ----------------------------------------------------------------------------
# Kotlin Coroutines & Datastore
# ----------------------------------------------------------------------------
-dontwarn kotlinx.coroutines.**
-keepclassmembers class kotlinx.coroutines.** { *; }

# ----------------------------------------------------------------------------
# Jetpack Compose & Android Support
# ----------------------------------------------------------------------------
-dontwarn androidx.compose.**
-dontwarn androidx.datastore.**
-dontwarn androidx.security.crypto.**

# Keep Enums
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
