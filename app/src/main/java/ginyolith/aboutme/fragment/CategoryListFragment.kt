package ginyolith.aboutme.fragment

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import ginyolith.aboutme.*
import ginyolith.aboutme.adapter.CategoryListAdapter
import ginyolith.aboutme.databinding.FragmentCategoryListBinding
import ginyolith.aboutme.model.Category


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [CategoryListFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [CategoryListFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class CategoryListFragment : Fragment() {
    private lateinit var binding: FragmentCategoryListBinding

    private fun setUpRecyclerView() {
        val categoryDAO = getDatabase().getCategoryDAO()
        val adapter = CategoryListAdapter(dataList = categoryDAO.selectAllOrderByIdDesc().toMutableList())

        adapter.onClick = {
            view?.findNavController()?.navigate(R.id.toDetailList, Bundle().apply {
                putLong(EXTRA_CATEGORY_ID, it.id)
            })
        }

        adapter.onDeleteButtonClick = {
            getDatabase().getCategoryDAO().deleteById(it.id)
            val idx = adapter.dataList.indexOf(it)
            adapter.dataList.removeAt(idx)
            adapter.notifyItemRemoved(idx)
        }

        binding.categoryList.adapter = adapter
        binding.categoryList.layoutManager = LinearLayoutManager(requireContext())

        // Listに区切り線を入れる
        binding.categoryList.setDividerEnabled(requireContext(),true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_category_list, container, false)
        setUpRecyclerView()

        binding.fabCreateCategory.setOnClickListener {
            startActivity(Intent(activity, EditCategoryActivity::class.java))
        }

        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("hoge","hogehoge")
        if (requestCode == REQUEST_CODE_REFRESH_CATEGORY_LIST) {
            Log.d("hoge","hogehoge")
            val adapter = binding.categoryList.adapter as CategoryListAdapter
            adapter.dataList = getDatabase().getCategoryDAO()
                    .selectAllOrderByIdDesc()
                    .toMutableList()
            adapter.notifyDataSetChanged()
            activity?.toast("カテゴリーが登録されました。")
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment CategoryListFragment.
         */
        @JvmStatic
        fun newInstance() = CategoryListFragment()
    }
}
