package com.sdk.jetcongrats.data.manager

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

class MyClipBoardManager(
    private val context: Context
) {
    fun copyText(text: String) {
        val clipBoardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("text", text)
        clipBoardManager.setPrimaryClip(clip)
    }
}