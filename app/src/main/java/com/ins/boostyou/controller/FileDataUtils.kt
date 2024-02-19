package com.ins.boostyou.controller

import android.content.Context
import androidx.preference.PreferenceManager
import com.google.gson.Gson


class FileDataUtils {
    companion object {
        private const val EXTRA_TOKEN = "extra.nts.lng.token"
        private const val EXTRA_USERNAME = "extra.nts.name"
        private const val EXTRA_ID = "extra.nts.id"

        private const val EXTRA_USERNAME_LIST = "extra.usname.list"

        var gson = Gson()
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

        fun saveUserNameToList(context: Context, userName: String) {
            var existingList = getUserNameList(context)?.toMutableSet()
            if (existingList.isNullOrEmpty()) {
                existingList = mutableSetOf(userName)
            } else {
                if (existingList.contains(userName)) {
                    existingList.remove(userName)
                }
                existingList.add(userName)
            }
            saveListToLocal(context, existingList)
        }

        private fun saveListToLocal(context: Context, existingList: MutableSet<String>){
            val jsonText = gson.toJson(existingList)
            val sharedPreference = PreferenceManager.getDefaultSharedPreferences(context)
            sharedPreference.edit().putString(EXTRA_USERNAME_LIST, jsonText).apply()
        }

        fun getUserNameList(context: Context): Array<String>? {
            val jsonText = PreferenceManager.getDefaultSharedPreferences(context)
                .getString(EXTRA_USERNAME_LIST, "")
            return gson.fromJson(
                jsonText,
                Array<String>::class.java
            )
        }

        fun removeAccountFromSavedList(accountToRemove: String, context: Context) {
            val savedList = getUserNameList(context)?.toMutableList()
            if (savedList?.contains(accountToRemove) == true){
                savedList.remove(accountToRemove)
                saveListToLocal(context, savedList.toMutableSet())
            }
        }

//        fun getUsIdFromLocal(context: Context): String {
//            return PreferenceManager.getDefaultSharedPreferences(context)
//                .getString(EXTRA_ID, "").orEmpty()
//        }

        fun logoutUserFromLocal(context: Context) {
            PreferenceManager.getDefaultSharedPreferences(context).edit()
                .putString(EXTRA_USERNAME, "")
                .putString(EXTRA_ID, "")
                .putString(EXTRA_TOKEN, "")
                .apply()
        }


    }
}