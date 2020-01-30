import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.mohammadsianaki.core.extenstion.hide
import com.github.mohammadsianaki.core.extenstion.show
import com.github.mohammadsianaki.core.ui.BaseFragment
import com.mydigipay.challenge.github.R
import com.mydigipay.challenge.presentation.core.BaseRecyclerAdapter
import com.mydigipay.challenge.presentation.core.ItemModel
import kotlinx.android.synthetic.main.list_fragment.*

abstract class ListFragment<Item : ItemModel, VM : ListViewModel<Item>> :
    BaseFragment() {

    override val layoutId: Int = R.layout.list_fragment
    private var recyclerView: RecyclerView? = null
    protected lateinit var recyclerAdapter: BaseRecyclerAdapter<Item>
    protected lateinit var viewModel: VM


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
    }

    private fun initUi() {
        recyclerAdapter = BaseRecyclerAdapter()
        recyclerView = recycler_view
        recyclerView?.apply {
            adapter = recyclerAdapter
            layoutManager = LinearLayoutManager(requireContext())
            itemAnimator = DefaultItemAnimator()
        }
    }

    override fun onDestroyView() {
        recyclerView?.adapter = null
        recyclerView = null
        super.onDestroyView()
    }

    protected open fun handleData(data: List<ItemModel>?) {
        hideLoading()
        showRecyclerView()
    }

    protected fun hideRecyclerView() = recyclerView?.hide()
    protected fun showRecyclerView() = recyclerView?.show()
}