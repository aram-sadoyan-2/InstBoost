package com.ins.engage.controller

import android.content.Context
import androidx.preference.PreferenceManager

class FileDataUtils {
    companion object {
        private const val EXTRA_TOKEN = "extra.nts.lng.token"
        private const val EXTRA_USERNAME = "extra.nts.name"
        private const val EXTRA_ID = "extra.nts.id"


        fun saveTokenIntoLocal(context: Context, token: String) {
            val sharedPreference = PreferenceManager.getDefaultSharedPreferences(context)
            sharedPreference.edit().putString(EXTRA_TOKEN, token)
                .apply()
        }

        fun getTokenFromLocal(context: Context): String {
            return PreferenceManager.getDefaultSharedPreferences(context).getString(EXTRA_TOKEN, "")
                .orEmpty()
        }

        fun removeTokenFromLocal(context: Context) {
            PreferenceManager.getDefaultSharedPreferences(context).edit().putString(EXTRA_TOKEN, "")
                .apply()
        }


        fun saveUsNmAndId(context: Context, id: String, usNm: String) {
            val sharedPreference = PreferenceManager.getDefaultSharedPreferences(context)
            sharedPreference.edit().putString(EXTRA_USERNAME, usNm).putString(EXTRA_ID, id).apply()
        }

        fun getUsNameFromLocal(context: Context): String {
            return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(EXTRA_USERNAME, "").orEmpty()
        }

        fun getUsIdFromLocal(context: Context): String {
            return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(EXTRA_ID, "").orEmpty()
        }

        fun logoutUserFromLocal(context: Context) {
            PreferenceManager.getDefaultSharedPreferences(context).edit()
                .putString(EXTRA_USERNAME, "")
                .putString(EXTRA_ID, "")
                .putString(EXTRA_TOKEN, "")
                .apply()
        }

    }
}