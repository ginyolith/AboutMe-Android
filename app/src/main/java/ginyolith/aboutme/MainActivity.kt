package ginyolith.aboutme

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.util.DiffUtil
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigator
import ginyolith.aboutme.adapter.CategoryListAdapter
import ginyolith.aboutme.data.AboutMeDatabase
import ginyolith.aboutme.databinding.ActivityMainBinding
import ginyolith.aboutme.model.Category
import ginyolith.aboutme.model.Detail

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        toast("登録が完了しました。")
        if (intent?.getBooleanExtra(KEY_INSERT_CATEGORY_RESULT, false) == true) {
            toast("登録が完了しました。")
        }

    }

}