package com.recepyesilkaya.koin_sample.view.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.recepyesilkaya.koin_sample.R
import com.recepyesilkaya.koin_sample.data.model.Pray
import com.recepyesilkaya.koin_sample.util.Status
import com.recepyesilkaya.koin_sample.view.adapter.PrayAdapter
import com.recepyesilkaya.koin_sample.view.adapter.SelectPrayCallback
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment(), SelectPrayCallback {

    /*private val homeViewModel: HomeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }*/

    private val homeViewModel by viewModel<HomeViewModel>()

    private lateinit var prayAdapter: PrayAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prayAdapter = PrayAdapter(this)
        rvPray.adapter = prayAdapter

        observePrayLiveData(false)

        swipeRefresh.setOnRefreshListener {
            swipeRefresh.isRefreshing = false
            progressBar.visibility = View.VISIBLE
            textView.visibility = View.GONE
            rvPray.visibility = View.GONE
            observePrayLiveData(false)
        }
    }

    private fun observePrayLiveData(statusDataLocal: Boolean) {
        homeViewModel.getPrayData("9541", statusDataLocal).observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> {
                    Log.e("Data", "Loading")
                    progressBar.visibility = View.VISIBLE
                    textView.visibility = View.GONE
                    rvPray.visibility = View.GONE
                }
                Status.SUCCESS -> {
                    Log.e("Data", "Success")
                    rvPray.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                    textView.visibility = View.GONE
                    println(it.data)
                    it.data?.let { prayList -> prayAdapter.updatePrayList(prayList) }
                }
                Status.ERROR -> {
                    Log.e("Data", "Error")
                    progressBar.visibility = View.GONE
                    textView.visibility = View.VISIBLE
                    rvPray.visibility = View.GONE
                    textView.text = "Veriler Yüklenirken Hata Oluştu!"
                }
            }
        })
    }

    override fun onItemClick(pray: Pray) {
        val bundle = Bundle()
        bundle.putParcelable("pray", pray)
        findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundle)
    }
}