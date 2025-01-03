package com.angga.univlist.ui.presentation.utils

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent

fun openInAppBrowser(context: Context, url: String) {
    val customTabsIntent = CustomTabsIntent.Builder()
        .setShowTitle(true)
        .build()
    customTabsIntent.launchUrl(context, Uri.parse(url))
}