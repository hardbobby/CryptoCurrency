object Dependencies {

    //Androidx
    const val coreAndroidx = "androidx.core:core-ktx:${Version.androidxCoreKtx}"
    const val appCompactAndroidx = "androidx.appcompat:appcompat:${Version.androidxAppCompact}"
    const val lifeCycleCommonAndroidx = "androidx.lifecycle:lifecycle-common-java8:${Version.lifeCycleVersion}"
    const val lifeCycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.lifeCycleVersion}"
    const val lifeCycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.lifeCycleVersion}"
    const val activityKtx = "androidx.activity:activity-ktx:${Version.androidXActivity}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Version.androidXFragment}"
    const val legacySupport = "androidx.legacy:legacy-support-v4:${Version.legacySupport}"
    const val sharePref = "androidx.preference:preference-ktx:${Version.sharePref}"

    //Room
    const val runTimeRoom = "androidx.room:room-runtime:${Version.roomVersion}"
    const val compilerRoom = "androidx.room:room-compiler:${Version.roomVersion}"
    const val extensionRoom = "androidx.room:room-ktx:${Version.roomVersion}"


    //Hilt
    const val hiltDagger = "com.google.dagger:hilt-android:${Version.hiltAndroid}"
    const val hiltDaggerCompiler = "com.google.dagger:hilt-android-compiler:${Version.hiltAndroid}"
    const val hiltDaggerViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${Version.hiltViewModel}"
    const val hiltCompiler = "androidx.hilt:hilt-compiler:${Version.hiltCompiler}"

    //Material Design
    const val materialDesign = "com.google.android.material:material:${Version.materialDesign}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Version.constraintLayout}"


    //Retrofit
    const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
    const val retrofitGson = "com.squareup.retrofit2:converter-gson:${Version.retrofit}"
    const val okHttpInterceptor = "com.squareup.okhttp3:logging-interceptor:${Version.loggingInterceptor}"


    //Scarlet
    const val scarletBase = "com.tinder.scarlet:scarlet:${Version.scarletBundleVersion}"
    const val scarletStream = "com.tinder.scarlet:stream-adapter-coroutines:${Version.scarletBundleVersion}"
    const val scarletMessage = "com.tinder.scarlet:message-adapter-gson:${Version.scarletBundleVersion}"
    const val scarletLifeCycle = "com.tinder.scarlet:lifecycle-android:${Version.scarletBundleVersion}"
    const val scarletProtocol = "com.tinder.scarlet:protocol-websocket-okhttp:${Version.scarletBundleVersion}"

    //Navigation

    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Version.navigation}"
    const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Version.navigation}"

    //Coroutines
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutines}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutines}"

    //Shimmer
    const val shimmer = "com.facebook.shimmer:shimmer:${Version.shimmer}"

    //Vvalidator
    const val validator = "com.afollestad:vvalidator:${Version.vvalidator}"

    //Mockk
    const val mockK = "io.mockk:mockk:${Version.mockKVersion}"


}