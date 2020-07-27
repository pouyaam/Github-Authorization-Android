object Configs {
    const val applicationId = "com.mydigipay.challenge"
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val consumerProguardFiles = "consumer-rules.pro"

    const val minSdkVersion = 21
    const val targetSdkVersion = 29
    const val compileSdkVersion = 29

    const val versionMajor = 1
    const val versionMinor = 0
    const val versionPatch = 0
    const val versionClassifier = ""

    val versionCode = generateVersionCode()
    val versionName = generateVersionName()

    private fun generateVersionCode(): Int {
        return minSdkVersion * 10000000 + versionMajor * 10000 + versionMinor * 100 + versionPatch
    }

    private fun generateVersionName(): String {
        var versionName = "${versionMajor}.${versionMinor}.${versionPatch}"
        if (versionClassifier != null && versionClassifier.isNotEmpty()) {
            versionName = versionName + "-" + versionClassifier
        }
        return versionName;
    }
}
