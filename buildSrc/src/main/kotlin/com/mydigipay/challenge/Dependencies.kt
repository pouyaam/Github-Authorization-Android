object Dependencies {

    /* Kotlin */
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    /* Testing */
    val jUnit = "junit:junit:${Versions.jUnit}"
    val androidXTestRunner = "androidx.test:runner:${Versions.androidXTestRunner}"
    val androidXTestExt = "androidx.test.ext:junit:${Versions.androidXTestExt}"
    val espessoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"

    /* AndroidX Support */
    val androidXCoreKtx = "androidx.core:core-ktx:${Versions.androidXKtx}"
    val androidXAppCompat = "androidx.appcompat:appcompat:${Versions.androidXAppCompat}"
    val androidXConstraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.androidXConstraintLayout}"
    val androidXRecyclerView = "androidx.recyclerview:recyclerview:${Versions.androidXRecyclerView}"
    val androidXCardView = "androidx.cardview:cardview:${Versions.androidXCardView}"
    val androidXSwipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.androidXSwipeRefreshLayout}"
    val googleAndroidMaterial = "com.google.android.material:material:${Versions.googleAndroidMaterial}"

    /* Jetpack Lifecycle Component */
    val lifeCycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.jetpackLifeCycle}"
    val lifeCycleCommonJava8 = "androidx.lifecycle:lifecycle-common-java8:${Versions.jetpackLifeCycle}"

    /* Jetpack Room Component */
    val roomRuntime = "androidx.room:room-runtime:${Versions.jetpackRoom}"
    val roomCompiler = "androidx.room:room-compiler:${Versions.jetpackRoom}"
    val roomTesting = "androidx.room:room-testing:${Versions.jetpackRoom}"
    val roomRxJava = "androidx.room:room-rxjava2:${Versions.jetpackRoom}"

    /* Jetpack Preference Component */
    val preference = "androidx.preference:preference-ktx:${Versions.jetpackPreference}"

    /* Jetpack Navigation Component */
    val navigationFragment = "androidx.navigation:navigation-fragment:${Versions.jetpackNavigation}"
    val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.jetpackNavigation}"
    val navigationUi = "androidx.navigation:navigation-ui:${Versions.jetpackNavigation}"
    val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.jetpackNavigation}"
    val navigationDynamicFeaturesFragment = "androidx.navigation:navigation-dynamic-features-fragment:${Versions.jetpackNavigation}"
    val navigationTesting = "androidx.navigation:navigation-testing:${Versions.jetpackNavigation}"

    /* RxJava */
    val rxJva = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"
    val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"

    /* Kotlin Coroutines */
    val kotlinXCoroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinXCoroutines}"
    val kotlinXCoroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinXCoroutines}"

    /* Dependency Injection using Dagger2 */
    val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    val daggerAndroidSupport = "com.google.dagger:dagger-android-support:${Versions.dagger}"
    val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    val daggerAndroidProcessor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"


    val koin = "org.koin:koin-android:${Versions.koin}"
    val koinViewModel = "org.koin:koin-android-viewmodel:${Versions.koin}"

    /* Remote API call using Retrofit2 */
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val converterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    val adapterRxJava = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
    val gson = "com.google.code.gson:gson:${Versions.googleGson}"
    val adapterCoroutines = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.jakeWhartonCoroutineAdapter}"

    /* Other */

    // Media management and image loading framework
    val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"

    /**
     * To add an external maven dependency:
     * compile "groupId:artifactId:version"
     */

    const val excludeGroup = "com.android.support"
    const val excludeModule = "support-annotations"
}
