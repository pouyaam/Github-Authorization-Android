package com.mydigipay.challenge.util

interface FragmentOnBackPressed {
    /**
     * If you return false the back press will not be taken into account, otherwise the activity will act naturally
     * @return false if your processing has priority if not true
     */
    fun onBackPressed(): Boolean
}