package tech.penser.rhinokm.core.domain.model

import android.os.Parcel
import android.os.Parcelable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
@JvmInline
value class SafeUuid (val uuid: Uuid): Parcelable {
    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(uuid.toString())
    }

    companion object CREATOR : Parcelable.Creator<SafeUuid> {
        fun random(): SafeUuid = SafeUuid(Uuid.random())

        override fun createFromParcel(parcel: Parcel): SafeUuid? {
            val uuid = parcel.readString()
            return SafeUuid(Uuid.parse(uuid!!))
        }

        override fun newArray(size: Int): Array<out SafeUuid?>? = arrayOfNulls(size)
    }
}