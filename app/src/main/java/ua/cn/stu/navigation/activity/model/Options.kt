package ua.cn.stu.navigation.activity.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Options(
    val thimbleCount: Int,
    val isTimerEnabled: Boolean
) : Parcelable {

    companion object {
        @JvmStatic val DEFAULT = Options(thimbleCount = 3, isTimerEnabled = false)
    }
}