package com.example.aviatanews.utils.extensions

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

/**
 * Разделяемый объект для работы с JSON
 * @author Didar Ospanov on 28.09.2021
 */
val gson by lazy { GsonBuilder().serializeNulls().create() }
inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object: TypeToken<T>() {}.type)