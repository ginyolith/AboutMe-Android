package ginyolith.aboutme

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import ginyolith.aboutme.adapter.CategoryListAdapter
import ginyolith.aboutme.data.AboutMeDatabase
import ginyolith.aboutme.databinding.ActivityMainBinding
import ginyolith.aboutme.model.Category
import ginyolith.aboutme.model.Detail

const val EXTRA_CATEGORY_ID = "categoryId"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var database: AboutMeDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        database = getDatabase()

        setUpRecyclerView()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        if (intent?.getBooleanExtra(KEY_INSERT_CATEGORY_RESULT, false) == true) {
            toast("登録が完了しました。")

            val adapter = binding.categoryList.adapter as CategoryListAdapter
            adapter.dataList = database.getCategoryDAO().selectAllOrderByIdDesc().toMutableList()
            adapter.notifyDataSetChanged()
        }
    }


    private fun setUpRecyclerView() {
        val categoryDAO = database.getCategoryDAO()

        val adapter = CategoryListAdapter(dataList = categoryDAO.selectAllOrderByIdDesc().toMutableList())

        adapter.onClick = {category: Category ->
            val intent = Intent(MainActivity@ this, CategoryDetailActivity::class.java)
            intent.putExtra(EXTRA_CATEGORY_ID, category.id)
            startActivity(intent)
        }

        adapter.onDeleteButtonClick = {
            database.getCategoryDAO().deleteById(it.id)
            val idx = adapter.dataList.indexOf(it)
            adapter.dataList.removeAt(idx)
            adapter.notifyItemRemoved(idx)
        }

        binding.categoryList.adapter = adapter
        binding.categoryList.layoutManager = LinearLayoutManager(this)

        // Listに区切り線を入れる
        binding.categoryList.setDividerEnabled(this,true)
    }

    fun onClickFAB(v: View) {
        val intent = Intent(MainActivity@ this, EditCategoryActivity::class.java)
        startActivity(intent)
    }
}