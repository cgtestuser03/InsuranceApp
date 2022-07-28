package com.capg.insurance.ui.view

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.capg.insurance.R
import com.capg.insurance.databinding.FragmentProductBinding
import com.capg.insurance.ui.adapter.ProductAdapter
import com.capg.insurance.viewmodels.InsuranceViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ProductFragment : Fragment() {

    private var _binding: FragmentProductBinding? = null
    private var viewModel: InsuranceViewModel? = null
    private var mAdapter: ProductAdapter? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(InsuranceViewModel::class.java)

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    Log.e("dfgdfd", "dfgdfgdfgd")
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeRecyclerView()
        initializeObservers()
    }

    private fun initializeRecyclerView() {
        mAdapter = ProductAdapter()
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
    }

    private fun initializeObservers() {
        viewModel!!.getAllProducts(false).observe(viewLifecycleOwner, Observer { kt ->
            mAdapter!!.setProductList(kt)
        })
        viewModel!!.mShowApiError.observe(viewLifecycleOwner, Observer {
            AlertDialog.Builder(context).setMessage(it).show()
        })
        viewModel!!.mShowProgressBar.observe(viewLifecycleOwner, Observer { bt ->
            if (bt) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        })
        viewModel!!.mShowNetworkError.observe(viewLifecycleOwner, Observer {
            AlertDialog.Builder(context).setMessage(R.string.app_no_internet_msg).show()
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}