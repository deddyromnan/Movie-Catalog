package com.romnan.moviecatalog.ui.favorite.tvseries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.romnan.moviecatalog.R
import com.romnan.moviecatalog.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_favorite_tv_series.*

class FavoriteTvSeriesFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_favorite_tv_series, container, false)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel =
                ViewModelProvider(requireActivity(), factory)[FavoriteTvSeriesViewModel::class.java]

            val tvSeriesAdapter = FavoriteTvSeriesAdapter()

            progress_bar_favorite_tv_series.visibility = View.VISIBLE

            viewModel.getFavoriteTvSeries().observe(viewLifecycleOwner, { tvSeries ->
                tvSeriesAdapter.setTvSeries(tvSeries)
                progress_bar_favorite_tv_series.visibility = View.GONE
            })

            with(rv_favorite_tv_series) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvSeriesAdapter
            }
        }
    }
}