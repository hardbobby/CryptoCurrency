#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring

JNICALL
Java_com_hardbobby_data_common_Keys_apiKey(JNIEnv *env, jobject instance) {
    std::string api_key = "4b2fe1fead3d87f601b35a3f3c0a5975d5017d12c4203497364609d03efe9a58";
    return env->NewStringUTF(api_key.c_str());
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_hardbobby_data_common_Keys_baseUrl(JNIEnv *env, jobject thiz) {
    std::string base_url = "https://min-api.cryptocompare.com/";
    return env->NewStringUTF(base_url.c_str());
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_hardbobby_data_common_Keys_baseScarletUrl(JNIEnv *env, jobject thiz) {
    std::string base_scarlet_url = "wss://streamer.cryptocompare.com/v2";
    return env->NewStringUTF(base_scarlet_url.c_str());
}