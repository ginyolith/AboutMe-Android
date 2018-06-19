package ginyolith.aboutme.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.databinding.BaseObservable
import android.databinding.Bindable

@Entity(tableName = "category")
class Category : BaseObservable() {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    var id : Long = 0

    @ColumnInfo(name ="title")
    @Bindable
    var title: String = ""

    @ColumnInfo(name = "description")
    @Bindable
    var description: String = ""

    override fun toString(): String {
        return "id=$id, title=$title, description=$description"
    }


}