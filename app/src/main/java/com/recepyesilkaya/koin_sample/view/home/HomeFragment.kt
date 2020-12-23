package com.recepyesilkaya.koin_sample.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.recepyesilkaya.koin_sample.R
import com.recepyesilkaya.koin_sample.view.adapter.PrayAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

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

        prayAdapter = PrayAdapter {
            val bundle = Bundle()
            bundle.putParcelable("pray", it)
            findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundle)
        }

        rvPray.adapter = prayAdapter

        observePrayLiveData()
        observePrayFlow("9541", false)

        swipeRefresh.setOnRefreshListener {
            swipeRefresh.isRefreshing = false
            progressBar.visibility = View.VISIBLE
            textView.visibility = View.GONE
            rvPray.visibility = View.GONE
            observePrayFlow("9541", false)
        }
    }

    private fun observePrayFlow(id: String, statusDataLocal: Boolean) {
        homeViewModel.getPrayData(id, statusDataLocal).observe(viewLifecycleOwner, Observer {
            homeViewModel.resourceStatusData(it)
        })
    }

    private fun observePrayLiveData() {
        homeViewModel.loadingValue.observe(viewLifecycleOwner, Observer {
            progressBar.isVisible = it
        })
        homeViewModel.successValue.observe(viewLifecycleOwner, Observer {
            rvPray.isVisible = it
        })
        homeViewModel.errorValue.observe(viewLifecycleOwner, Observer {
            textView.isVisible = it
        })
        homeViewModel.prays.observe(viewLifecycleOwner, Observer {
            prayAdapter.updatePrayList(it)
        })
        homeViewModel.error.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
    }
}