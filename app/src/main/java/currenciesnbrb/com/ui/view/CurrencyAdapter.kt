package currenciesnbrb.com.ui.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import currenciesnbrb.com.R
import currenciesnbrb.com.model.CurrencyType
import kotlinx.android.synthetic.main.item_currency.view.*

class CurrencyAdapter(private val typeList: MutableList<CurrencyType>): RecyclerView.Adapter<HolderHelpAdapter>(){

    private lateinit var context: Context

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): HolderHelpAdapter {
        context = p0.context
        return HolderHelpAdapter(
            LayoutInflater.from(
                context
            ).inflate(R.layout.item_currency, p0, false)
        )
    }

    override fun getItemCount(): Int = typeList.size

    override fun onBindViewHolder(p0: HolderHelpAdapter, p1: Int) {
        val type = typeList[p1]

        val currencyID = p0.itemView.currency_id_textview
        val currencyName = p0.itemView.currency_name_textview

        currencyID.text = type.id
        currencyName.text = type.name


    }
}