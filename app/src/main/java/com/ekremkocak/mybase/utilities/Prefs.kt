package com.ekremkocak.mybase.utilities

import android.content.Context
import androidx.core.content.edit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Prefs {

    companion object Prefs {
        fun setStringListSharedPreferences(
            context: Context?,
            value: MutableSet<String>,
            key: String
        ) {

            context!!.applicationContext.getSharedPreferences(context.packageName,Context.MODE_PRIVATE).edit {
                putStringSet(key, value)
            }
        }

        fun setIntArrayListSharedPreferences(
            context: Context?,
            key: String,
            value: ArrayList<Int>
        ) {
            val sharedPreferences = context!!.applicationContext.getSharedPreferences(context.packageName,Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            val gson = Gson()
            val json = gson.toJson(value)
            editor.putString(key, json)
            editor.apply()
        }

        fun getIntArrayListSharedPreferences(context: Context?, key: String): ArrayList<Int>? {
            val sharedPreferences = context!!.applicationContext.getSharedPreferences(context.packageName,Context.MODE_PRIVATE)
            val json = sharedPreferences.getString(key, null)
            val gson = Gson()
            val type = object : TypeToken<ArrayList<Int>>() {}.type
            return gson.fromJson(json, type)
        }


        fun getStringListSharedPreferences(context: Context?, key: String): MutableSet<String>? {
            return context!!.applicationContext.getSharedPreferences(context.packageName,Context.MODE_PRIVATE).getStringSet(key, null)
        }

        fun setKeySharedPreferences(context: Context, key: String, value: String) {
            context.applicationContext.getSharedPreferences(context.packageName,Context.MODE_PRIVATE).edit {
                putString(key, value).apply()
            }
        }

        fun getKeySharedPreferences(context: Context, key: String): String {
            return context.applicationContext.getSharedPreferences(context.packageName,Context.MODE_PRIVATE).getString(key, null) ?: ""
        }

        fun setKeySharedPreferencesBoolean(context: Context, key: String, value: Boolean) {
            context.applicationContext.getSharedPreferences(context.packageName,Context.MODE_PRIVATE).edit {
                putBoolean(key, value).apply()
            }
        }

        fun getKeySharedPreferencesBoolean(context: Context, key: String): Boolean {
            return context.applicationContext.getSharedPreferences(context.packageName,Context.MODE_PRIVATE).getBoolean(key, false)
        }


        fun setKeySharedPreferencesInt(context: Context, key: String, value: Int) {
            context.applicationContext.getSharedPreferences(context.packageName,Context.MODE_PRIVATE).edit {
                putInt(key, value).apply()
            }
        }

        fun getKeySharedPreferencesInt(context: Context, key: String): Int {
            return context.applicationContext.getSharedPreferences(context.packageName,Context.MODE_PRIVATE).getInt(key, 0)
        }

        fun removeSharedPreferences(context: Context) {
            context.applicationContext.getSharedPreferences(context.packageName,Context.MODE_PRIVATE).edit().clear().apply()
        }
    }
}