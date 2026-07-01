package com.lingos.app.utils

import android.util.Log
import com.lingos.app.BuildConfig   // 确保 applicationId = "com.lingos.app"

object Logger {
    private val isEnabled: Boolean = BuildConfig.DEBUG
    private var isVerbose: Boolean = BuildConfig.DEBUG

    fun setVerbose(verbose: Boolean) { isVerbose = verbose }

    fun d(tag: String, message: String) { if (isEnabled) Log.d(tag, message) }
    fun d(tag: String, format: String, vararg args: Any) { if (isEnabled) Log.d(tag, String.format(format, *args)) }
    fun i(tag: String, message: String) { if (isEnabled) Log.i(tag, message) }
    fun i(tag: String, format: String, vararg args: Any) { if (isEnabled) Log.i(tag, String.format(format, *args)) }
    fun w(tag: String, message: String) { if (isEnabled) Log.w(tag, message) }
    fun w(tag: String, format: String, vararg args: Any) { if (isEnabled) Log.w(tag, String.format(format, *args)) }
    fun e(tag: String, message: String, throwable: Throwable? = null) {
        if (isEnabled) { if (throwable != null) Log.e(tag, message, throwable) else Log.e(tag, message) }
    }
    fun e(tag: String, throwable: Throwable? = null, format: String, vararg args: Any) {
        if (isEnabled) { val msg = String.format(format, *args); if (throwable != null) Log.e(tag, msg, throwable) else Log.e(tag, msg) }
    }
    fun v(tag: String, message: String) { if (isEnabled && isVerbose) Log.v(tag, message) }
    fun enter(tag: String, methodName: String = Thread.currentThread().stackTrace[2].methodName) { if (isEnabled && isVerbose) Log.d(tag, "▶ Enter: $methodName") }
    fun exit(tag: String, methodName: String = Thread.currentThread().stackTrace[2].methodName) { if (isEnabled && isVerbose) Log.d(tag, "◀ Exit: $methodName") }

    fun json(tag: String, json: String) {
        if (isEnabled) {
            try { Log.d(tag, "JSON:\n" + org.json.JSONObject(json).toString(2)) }
            catch (e: Exception) { try { Log.d(tag, "JSON Array:\n" + org.json.JSONArray(json).toString(2)) } catch (e2: Exception) { Log.d(tag, "Raw: $json") } }
        }
    }

    fun long(tag: String, message: String) {
        if (isEnabled) {
            val max = 4000
            if (message.length <= max) Log.d(tag, message)
            else { var start = 0; while (start < message.length) { val end = (start + max).coerceAtMost(message.length); Log.d(tag, message.substring(start, end)); start = end } }
        }
    }

    fun time(tag: String, block: () -> Unit): Long {
        if (isEnabled) { val start = System.currentTimeMillis(); block(); val elapsed = System.currentTimeMillis() - start; Log.d(tag, "⏱ Elapsed: ${elapsed}ms"); return elapsed }
        block(); return 0L
    }

    fun cond(tag: String, condition: Boolean, message: String) { if (isEnabled && condition) Log.d(tag, message) }
}
