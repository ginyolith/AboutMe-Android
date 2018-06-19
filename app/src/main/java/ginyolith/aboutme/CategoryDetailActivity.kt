package ginyolith.aboutme

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import ginyolith.aboutme.adapter.DetailListAdapter
import ginyolith.aboutme.databinding.ActivityCategoryDetailBinding

const val EXTRA_DETAIL_ID = "detailId"

class CategoryDetailActivity : AppCompatActivity() {

    lateinit var binding : ActivityCategoryDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_category_detail)

        val categoryId : Long = intent.getLongExtra(EXTRA_CATEGORY_ID, -1)
        binding.category =  getDatabase().getCategoryDAO().selectById(categoryId)

        setUpRecyclerView(categoryId)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        if (intent?.getBooleanExtra(KEY_INSERT_DETAIL_RESULT, false) == true) {
            toast("登録が完了しました。")

            val adapter = binding.detailList.adapter as DetailListAdapter
            val id = binding.category?.id
            if (id != null) {
                adapter.dataList = getDatabase().getDetailDAO().selectByCategoryId(id).toMutableList()
            }
            adapter.notifyDataSetChanged()
        }
    }


    private fun setUpRecyclerView(categoryId : Long) {
        val adapter = DetailListAdapter(
                dataList = getDatabase().getDetailDAO().selectByCategoryId(categoryId).toMutableList())

        binding.detailList.adapter = adapter
        binding.detailList.layoutManager = LinearLayoutManager(this)

        adapter.onClick = {
            val intent = Intent(CategoryDetailActivity@ this, EditDetailActivity::class.java)
            intent.putExtra(EXTRA_DETAIL_ID, it.id)
            startActivity(intent)
        }

        adapter.onDeleteButtonClick = {detail, position ->
            getDatabase().getDetailDAO().deleteById(detail.id)
            adapter.dataList.removeAt(position)
            adapter.notifyItemRemoved(position)
//            adapter.notifyItemRangeChanged(idx, adapter.dataList.size)
        }

        binding.detailList.adapter = adapter
        binding.detailList.layoutManager = LinearLayoutManager(this)

        // Listに区切り線を入れる
        binding.detailList.setDividerEnabled(this,true)
    }

    fun onClickFAB(v: View) {
        val intent = Intent(CategoryDetailActivity@ this, EditDetailActivity::class.java)
        intent.putExtra(EXTRA_CATEGORY_ID, binding.category?.id)
        startActivity(intent)
    }
}
