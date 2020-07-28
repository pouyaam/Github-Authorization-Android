package com.mydigipay.challenge.common

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.ContextMenu.ContextMenuInfo
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment : Fragment() {

    /****************************************************
     * FRAGMENT LIFECYCLE
     ***************************************************/

    /**
     * Is called when a fragment is connected to an activity.
     */
    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)

        // DEBUG
        Log.d(TAG, "Lifecycle - onAttach");
    }

    /**
     * Is called to do initial creation of the fragment.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // DEBUG
        Log.d(TAG, "Lifecycle - onCreate");

        /* TODO("Initialize ViewModel here") */
    }

    /**
     * Is called by Android once the Fragment should inflate a view.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // DEBUG
        Log.d(TAG, "Lifecycle - onCreateView");

        /* TODO("Defines the xml file for the fragment here") */

        return null
    }

    /**
     * Is called after onCreateView() and ensures that the fragment's root view is non-null.
     * Any view setup should happen here. E.g., view lookups, attaching listeners.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // DEBUG
        Log.d(TAG, "Lifecycle - onViewCreated");

        /* TODO("Setup any handles to view objects here") */

        setupViews()
        setupObservers()
    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return false
    }

    /**
     * Is called when host activity has completed its onCreate() method.
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // DEBUG
        Log.d(TAG, "Lifecycle - onActivityCreated");
    }

    /**
     * Is called once the fragment is ready to be displayed on screen.
     */
    override fun onStart() {
        super.onStart()

        // DEBUG
        Log.d(TAG, "Lifecycle - onStart");
    }

    /**
     * Allocate “expensive” resources such as registering for location, sensor updates, etc.
     */
    override fun onResume() {
        super.onResume()

        // DEBUG
        Log.d(TAG, "Lifecycle - onResume");
    }

    /**
     * Release “expensive” resources. Commit any changes.
     */
    override fun onPause() {
        // DEBUG
        Log.d(TAG, "Lifecycle - onPause");

        super.onPause()
    }

    /**
     * All resources released.
     */
    override fun onStop() {
        // DEBUG
        Log.d(TAG, "Lifecycle - onStop");
        super.onStop()
    }

    /**
     * Is called when fragment's view is being destroyed, but the fragment is still kept around.
     */
    override fun onDestroyView() {
        // DEBUG
        Log.d(TAG, "Lifecycle - onDestroyView");

        super.onDestroyView()
    }

    /**
     * Is called when fragment is no longer in use.
     */
    override fun onDestroy() {
        // DEBUG
        Log.d(TAG, "Lifecycle - onDestroy");

        super.onDestroy()
    }

    /**
     * Is called when fragment is no longer connected to the activity.
     */
    override fun onDetach() {
        // DEBUG
        Log.d(TAG, "Lifecycle - onDetach");

        super.onDetach()
    }

    /****************************************************
     * VIEW/DATA BINDING
     ***************************************************/

    protected abstract fun setupViews()

    protected abstract fun setupItemsListView()

    protected abstract fun setupErrorAnnounce()

    protected fun showErrorToast(rootLayout: View, message: String?) {
        message?.let {
            Snackbar.make(rootLayout, message, Snackbar.LENGTH_LONG)
                .show()
        }
    }

    /****************************************************
     * OBSERVERS
     ***************************************************/

    protected open fun setupObservers() {
        // Nothing
    }

    companion object {
        val TAG = BaseFragment::class.java.simpleName
        const val FIRST_PAGE = 1
    }
}
