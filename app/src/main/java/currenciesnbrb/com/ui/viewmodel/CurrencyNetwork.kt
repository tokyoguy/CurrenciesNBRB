package currenciesnbrb.com.ui.viewmodel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.google.gson.Gson
import currenciesnbrb.com.R
import currenciesnbrb.com.model.CurrencyType
import currenciesnbrb.com.ui.view.CurrencyAdapter
import kotlinx.android.synthetic.main.activity_currency_network.*
import org.json.JSONArray

class CurrencyNetwork : AppCompatActivity() {

    private val dataList: MutableList<CurrencyType> = mutableListOf()
    private lateinit var currenciesAdapter: CurrencyAdapter
    val CURRENCIES_URL = "https://www.nbrb.by/API/ExRates/Currencies"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency_network)

        currenciesAdapter = CurrencyAdapter(dataList)

        currencies_recyclerview.layoutManager = LinearLayoutManager(this)
        currencies_recyclerview.addItemDecoration(DividerItemDecoration(this, OrientationHelper.VERTICAL))
        currencies_recyclerview.adapter = currenciesAdapter

        AndroidNetworking.initialize(this)
        AndroidNetworking.get(CURRENCIES_URL)
            .build()
            .getAsJSONArray(object : JSONArrayRequestListener {
                override fun onResponse(response: JSONArray?) {
                    response?.apply {
                        var position = 0
                        val currenciesList = ArrayList<CurrencyType>()
                        for (i in 0 until length()) {
                            val json = getJSONObject(position).toString()
                            val currency = Gson().fromJson(json, CurrencyType::class.java)
                            currenciesList.add(currency)
                            position += 1
                        }
                        dataList.addAll(currenciesList)
                        currenciesAdapter.notifyDataSetChanged()
                    }
                }

                override fun onError(anError: ANError?) {
                    anError?.printStackTrace()
                }
            })
    }
}