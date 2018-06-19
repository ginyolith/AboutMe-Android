package ginyolith.aboutme

import android.app.Activity
import android.content.Context
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import ginyolith.aboutme.application.CustomApplication
import ginyolith.aboutme.data.AboutMeDatabase

/** Toastの簡略化関数 */
fun Activity.toast(msg : String, length : Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, msg, length).show()
}

/** RoomのDatabaseインスタンス取得の簡略化関数 */
fun Activity.getDatabase() : AboutMeDatabase{
    return (this.application as CustomApplication).database
}

/** RecyclerViewに区切り線を入れる関数 */
fun RecyclerView.setDividerEnabled(context : Context, enabled : Boolean) {
    // 区切り線のスタイル設定を取得
    val dividerItemDecoration = DividerItemDecoration(
            context,
            LinearLayoutManager(context).orientation)

    // 追加 or 削除
    if (enabled) {
        this.addItemDecoration(dividerItemDecoration)
    } else {
        this.removeItemDecoration(dividerItemDecoration)
    }
}