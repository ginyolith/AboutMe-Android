package ginyolith.aboutme.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import android.databinding.BaseObservable
import android.databinding.Bindable
import ginyolith.aboutme.BR
import java.net.NoRouteToHostException

@Entity(tableName = "detail")
class Detail : BaseObservable() {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    var id : Long = 0

    @ForeignKey(
            entity = Category::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"]
    ) var categoryId : Long = 0

    @ColumnInfo(name ="title")
    @Bindable
    var title: String = ""

    @ColumnInfo(name = "description")
    @Bindable
    var description: String = ""
}