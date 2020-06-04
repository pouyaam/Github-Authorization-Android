package com.mydigipay.challenge.infrastructure.data

import com.mydigipay.challenge.infrastructure.data.local.preference.PreferenceHelper
import com.mydigipay.challenge.infrastructure.data.remote.ApiHelper
import javax.inject.Inject

/**
 * Created by Mahdi Zare Tahghigh Doost on 6/3/2020.
 *
 * mahdiZTD@gmail.com
 */

interface DataManager : ApiHelper, PreferenceHelper {
}

class DataManagerImp @Inject constructor(
    private val apiHelper: ApiHelper,
    private val preferenceHelper: PreferenceHelper
) : DataManager {



}