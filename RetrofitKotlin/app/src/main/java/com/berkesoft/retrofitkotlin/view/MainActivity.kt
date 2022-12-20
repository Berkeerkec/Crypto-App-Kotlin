package com.berkesoft.retrofitkotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.berkesoft.retrofitkotlin.R
import com.berkesoft.retrofitkotlin.adapter.CyrptoAdapter
import com.berkesoft.retrofitkotlin.databinding.ActivityMainBinding
import com.berkesoft.retrofitkotlin.databinding.RecyclerRowBinding
import com.berkesoft.retrofitkotlin.model.CyrptoModel
import com.berkesoft.retrofitkotlin.services.CyrptoApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding
    private val baseUrl = "https://raw.githubusercontent.com/"
    private var cyrptoModels : ArrayList<CyrptoModel>? = null
    private lateinit var adapter : CyrptoAdapter
    private var compositeDisposable : CompositeDisposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        compositeDisposable = CompositeDisposable()
        //https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/master/crypto.json
        loadData()
    }

    private fun loadData(){
        val retrofit = Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(CyrptoApi::class.java)

        compositeDisposable?.add(retrofit.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::handleResponse))

    }
    private fun handleResponse(cyrptoList : List<CyrptoModel>){
        cyrptoModels = ArrayList(cyrptoList)
        cyrptoModels?.let {
            binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = CyrptoAdapter(it)
            binding.recyclerView.adapter = adapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable?.clear()
    }




}