package com.islam.task.ui.paymentMethods

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.islam.task.data.Resource
import com.islam.task.data.network.response.Applicable
import com.islam.task.databinding.MainFragmentBinding
import com.islam.task.ui.BaseFragment
import com.islam.task.ui.adapters.PaymentAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PaymentFragment : BaseFragment<MainFragmentBinding>() {

    private val viewModel: PaymentViewModel by viewModels()

    private lateinit var paymentAdapter: PaymentAdapter

    private lateinit var applicableList: MutableList<Applicable>

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> MainFragmentBinding
        get() = MainFragmentBinding::inflate

    override fun setupOnViewCreated(view: View) {

        viewModel.getPaymentMethods()

        startObserver()

        binding.toolbar.backBtn.setOnClickListener {
            requireActivity().onBackPressed()
        }

    }

    private fun startObserver() {

        viewModel.methods.observe(viewLifecycleOwner, Observer {
            it?.getContentIfNotHandled()?.let { result ->
                when (result) {
                    is Resource.Success -> {

                        binding.listLayout.emptyList.visibility = View.GONE
                        binding.listLayout.loadingProgressBar.visibility = View.GONE

                        applicableList = result.data.networks.applicable

                        if (applicableList.isEmpty()) {
                            binding.listLayout.emptyList.visibility = View.VISIBLE
                            return@Observer
                        }

                        initRecyclerView()

                    }
                    is Resource.Error -> {

                        binding.listLayout.loadingProgressBar.visibility = View.GONE
                        binding.listLayout.emptyList.visibility = View.VISIBLE
                        binding.listLayout.emptyList.text = result.exception

                    }
                    is Resource.Loading -> {

                        binding.listLayout.loadingProgressBar.visibility = View.VISIBLE

                    }
                }
            }
        })

    }

    private fun initRecyclerView() {

        binding.listLayout.list.apply {
            paymentAdapter = PaymentAdapter(applicableList)
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = paymentAdapter
        }

    }

}