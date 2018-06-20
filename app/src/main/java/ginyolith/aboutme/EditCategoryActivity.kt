package ginyolith.aboutme

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ginyolith.aboutme.databinding.ActivityEditCategoryBinding
import ginyolith.aboutme.model.Category

class EditCategoryActivity : AppCompatActivity() {

    lateinit var binding : ActivityEditCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_edit_category)
        binding.category = binding.category ?: Category()

        binding.buttonSubmitEditCategory.setOnClickListener {
            val category = binding.category
            if (category != null) {
                getDatabase().getCategoryDAO().insertAll(category)
            }

            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}
