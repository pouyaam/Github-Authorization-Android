package com.mydigipay.challenge.common

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.ContextMenu.ContextMenuInfo
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.mydigipay.challenge.R
import com.mydigipay.challenge.presentation.design.TAG
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    /****************************************************
     * ACTIVITY LIFECYCLE
     ***************************************************/

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        // DEBUG
        Log.d(TAG, "Lifecycle - onCreate")

        // FIRST, initialize the Activity with [savedInstanceState]
        initializeActivity(savedInstanceState)

        // Setup Views
        setupViews()

        // Set Action Bar
        setupActionBar()

        // Init fragments and then, open home fragment
        setupNavigation()

        // Setup Observers
        setupObservers()

        // LAST, extract required params from incoming Intent
        extractIntentParams(intent)
    }

    override fun onStart() {
        super.onStart()

        // DEBUG
        Log.d(TAG, "Lifecycle - onStart")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        // DEBUG
        Log.d(TAG, "Lifecycle - onRestoreInstanceState")
    }

    override fun onResume() {
        super.onResume()

        // DEBUG
        Log.d(TAG, "Lifecycle - onResume")
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)

        // DEBUG
        Log.d(TAG, String.format("Lifecycle - onWindowFocusChanged(%s)", hasFocus))
    }

    override fun onBackPressed() {
        // DEBUG
        Log.d(TAG, "Lifecycle - onBackPressed")
        super.onBackPressed()
    }

    override fun onPause() {
        // DEBUG
        Log.d(TAG, "Lifecycle - onPause")
        super.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        // DEBUG
        Log.d(TAG, "Lifecycle - onSaveInstanceState")
        super.onSaveInstanceState(outState)
    }

    override fun onStop() {
        // DEBUG
        Log.d(TAG, "Lifecycle - onStop")
        super.onStop()
    }

    override fun onRestart() {
        super.onRestart()

        // DEBUG
        Log.d(TAG, "Lifecycle - onRestart")
    }

    override fun onDestroy() {
        // DEBUG
        Log.d(TAG, "Lifecycle - onDestroy")
        super.onDestroy()
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)

        // DEBUG
        Log.d(TAG, "Lifecycle - onNewIntent")
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)

        // DEBUG
        Log.d(TAG, "Lifecycle - onActivityResult")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> {
                // Nothing
                return true
            }
            android.R.id.home -> {
                // App icon in action bar clicked, so go home or finish this activity.
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return true
    }

    /****************************************************
     * ACTIVITY STATE
     ***************************************************/

    /**
     * If [savedInstanceState] equals to null, initialize state from incoming Intent,
     * else restore state from savedInstanceState.
     */
    protected abstract fun initializeActivity(savedInstanceState: Bundle?)

    /**
     * Extract data params from incoming Intent.
     */
    protected abstract fun extractIntentParams(data: Intent?)

    /****************************************************
     * VIEW/DATA BINDING
     ***************************************************/

    /**
     * Set Content View,
     * Set Action Bar,
     * Init fragments and then, open HomeFragment as default,
     * Bottom Navigation View,
     * ...
     */
    protected abstract fun setupViews()
    protected abstract fun setupActionBar()
    protected abstract fun setupNavigation()

    /****************************************************
     * OBSERVERS
     ***************************************************/

    protected open fun setupObservers() {
        // Nothing
    }

    /****************************************************
     * SERVICE BINDING
     ***************************************************/

    // Nothing

    /****************************************************
     * FUNCTIONALITY
     ***************************************************/

    // Nothing

    companion object {
        val TAG = BaseActivity::class.java.simpleName
    }
}
