package com.recepyesilkaya.koin_sample.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.recepyesilkaya.koin_sample.R
import com.recepyesilkaya.koin_sample.data.model.Pray
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var pray: Pray? = null

        arguments?.let {
            pray = DetailFragmentArgs.fromBundle(it).pray
        }
        pray?.let { pray ->
            tvSabah.text = pray.Imsak
            tvAksam.text = pray.Aksam
            tvOgle.text = pray.Ogle
            tvYatsi.text = pray.Yatsi
            tvIkindi.text = pray.Ikindi
            tvTarih.text = pray.MiladiTarihUzun
        }

    }
}