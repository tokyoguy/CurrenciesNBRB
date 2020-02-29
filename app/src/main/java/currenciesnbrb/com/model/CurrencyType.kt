package currenciesnbrb.com.model

import com.google.gson.annotations.SerializedName

data class CurrencyType(
    @SerializedName("Cur_ID")
    val id: String,
    @SerializedName("Cur_Name")
    val name: String
)