package com.lingos.app.data.repository

import com.lingos.app.data.local.PreferencesManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsRepository @Inject constructor(private val preferencesManager: PreferencesManager) {
    fun getHost(): Flow<String> = preferencesManager.hostFlow
    suspend fun setHost(host: String) = preferencesManager.setHost(host)
    fun getPort(): Flow<Int> = preferencesManager.portFlow
    suspend fun setPort(port: Int) = preferencesManager.setPort(port)
    suspend fun setAuthCode(code: String) = preferencesManager.setAuthCode(code)
    fun getAuthCode(): String? = preferencesManager.getAuthCode()
    suspend fun setConnectionCode(code: String) = preferencesManager.setConnectionCode(code)
    fun getSessionId(): Flow<String?> = preferencesManager.sessionIdFlow
    suspend fun setSessionId(sessionId: String) = preferencesManager.setSessionId(sessionId)
    fun getLastDeviceId(): Flow<String?> = preferencesManager.lastDeviceIdFlow
    suspend fun setLastDeviceId(deviceId: String) = preferencesManager.setLastDeviceId(deviceId)
    fun getUserName(): Flow<String> = preferencesManager.userNameFlow
    suspend fun setUserName(name: String) = preferencesManager.setUserName(name)
    fun getLanguage(): Flow<String> = preferencesManager.languageFlow
    suspend fun setLanguage(lang: String) = preferencesManager.setLanguage(lang)
    fun getTheme(): Flow<String> = preferencesManager.themeFlow
    suspend fun setTheme(theme: String) = preferencesManager.setTheme(theme)
    suspend fun clearAll() = preferencesManager.clearAll()
}
