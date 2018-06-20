package ginyolith.aboutme.fragment

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import ginyolith.aboutme.*
import ginyolith.aboutme.adapter.CategoryListAdapter
import ginyolith.aboutme.adapter.DetailListAdapter
import ginyolith.aboutme.databinding.FragmentDetailListBinding


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [DetailListFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [DetailListFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class DetailListFragment : Fragment() {
    private lateinit var binding: FragmentDetailListBinding

    private var categoryId: Long = -1


    private fun setUpRecyclerView(categoryId : Long) {
        val adapter = DetailListAdapter(
                dataList = getDatabase().getDetailDAO().selectByCategoryId(categoryId).toMutableList())

        binding.detailList.adapter = adapter
        binding.detailList.layoutManager = LinearLayoutManager(activity)

        adapter.onClick = {
            startActivity(Intent(activity, EditDetailActivity::class.java).apply {
                putExtra(EXTRA_CATEGORY_ID, categoryId)
            })
        }

        adapter.onDeleteButtonClick = {detail, position ->
            getDatabase().getDetailDAO().deleteById(detail.id)
            adapter.dataList.removeAt(position)
            adapter.notifyItemRemoved(position)
        }

        binding.detailList.adapter = adapter
        binding.detailList.layoutManager = LinearLayoutManager(requireContext())

        // Listに区切り線を入れる
        binding.detailList.setDividerEnabled(requireContext(),true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            categoryId = it.getLong(EXTRA_CATEGORY_ID)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_REFRESH_DETAIL_LIST) {
            val adapter = binding.detailList.adapter as DetailListAdapter
            adapter.dataList = getDatabase().getDetailDAO()
                    .selectByCategoryId(categoryId)
                    .toMutableList()
            adapter.notifyDataSetChanged()
            activity?.toast("詳細が登録されました。")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_list, container, false)
        binding.category =  getDatabase().getCategoryDAO().selectById(categoryId)
        setUpRecyclerView(categoryId)

        binding.fabCreateDetail.setOnClickListener{
            startActivity(Intent(activity, EditDetailActivity::class.java).apply {
                putExtra(EXTRA_CATEGORY_ID, categoryId)
            })
        }

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param categoryId Parameter 2.
         * @return A new instance of fragment DetailListFragment.
         */
        @JvmStatic
        fun newInstance(categoryId: Long) =
                DetailListFragment().apply {
                    arguments = Bundle().apply {
                        putLong(EXTRA_CATEGORY_ID, categoryId)
                    }
                }
    }
}
