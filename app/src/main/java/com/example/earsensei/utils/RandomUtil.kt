package com.example.earsensei.utils

inline fun <reified T : Enum<T>> randomEnum(): T = enumValues<T>().random()

