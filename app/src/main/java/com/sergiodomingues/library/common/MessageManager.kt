package com.sergiodomingues.library.common

import android.app.Activity
import android.content.res.Resources
import android.view.View
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class MessageManager @Inject constructor(private val activity: Activity, private val resources: Resources) {

    fun showError(@StringRes errorResId: Int) =
        showError(resources.getString(errorResId))

    private fun showError(error: String) =
        Snackbar
            .make(getTootView(), error, Snackbar.LENGTH_LONG)
            .show()

    private fun getTootView() = activity.findViewById<View>(android.R.id.content)
} 