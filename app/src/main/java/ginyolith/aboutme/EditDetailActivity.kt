package ginyolith.aboutme

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ginyolith.aboutme.databinding.ActivityEditDetailBinding
import ginyolith.aboutme.model.Detail

const val KEY_INSERT_DETAIL_RESULT = "INSERT_DETAIL_RESULT"

class EditDetailActivity : AppCompatActivity() {
    lateinit var binding : ActivityEditDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_edit_detail)
        binding.detail = binding.detail ?: Detail()
        val categoryId = intent?.getLongExtra(EXTRA_CATEGORY_ID, -1)

        binding.buttonSubmitEditDetail.setOnClickListener {
            val detail = binding.detail
            if (detail != null) {
                detail.categoryId = categoryId!!
                getDatabase().getDetailDAO().insertAll(detail)
            }

            val intent = Intent(this, CategoryDetailActivity::class.java)
            intent.putExtra(KEY_INSERT_DETAIL_RESULT, true)
            startActivity(intent)
            finish()
        }
    }
}
