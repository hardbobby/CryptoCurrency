package com.hardbobby.data.common

object Keys {
    init {
        System.loadLibrary("native-lib")
    }
    external fun apiKey(): String
    external fun baseUrl(): String
    external fun baseScarletUrl() : String
}