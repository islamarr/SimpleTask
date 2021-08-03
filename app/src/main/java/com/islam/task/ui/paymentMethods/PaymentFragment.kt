package com.islam.task.ui.paymentMethods

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.islam.task.R
import com.islam.task.data.network.response.Applicable
import com.islam.task.databinding.MainFragmentBinding
import com.islam.task.generalUtils.ApiException
import com.islam.task.generalUtils.Coroutines
import com.islam.task.generalUtils.NoInternetException
import com.islam.task.ui.BaseFragment
import com.islam.task.ui.adapters.PaymentAdapter
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import timber.log.Timber


class PaymentFragment : BaseFragment<MainFragmentBinding>(), KodeinAware {

    private lateinit var viewModel: PaymentViewModel
    private val factory: PaymentViewModelFactory by instance()
    private lateinit var paymentAdapter: PaymentAdapter
    private lateinit var applicableList: MutableList<Applicable>

    override val kodein by kodein()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> MainFragmentBinding
        get() = MainFragmentBinding::inflate

    override fun setupOnViewCreated(view: View) {

        viewModel = ViewModelProvider(this, factory).get(PaymentViewModel::class.java)

        Coroutines.main {
            try {
                applicableList = viewModel.getPaymentMethods().networks.applicable

                binding.listLayout.emptyList.visibility = View.GONE
                binding.listLayout.loadingProgressBar.visibility = View.GONE

                if (applicableList.isEmpty()) {
                    binding.listLayout.emptyList.visibility = View.VISIBLE
                    return@main
                }

                initRecyclerView()

            } catch (e: ApiException) {
                binding.listLayout.loadingProgressBar.visibility = View.GONE
                binding.listLayout.emptyList.visibility = View.VISIBLE
                binding.listLayout.emptyList.text = getString(R.string.error)
                Timber.e(e)
            } catch (ne: NoInternetException) {
                binding.listLayout.loadingProgressBar.visibility = View.GONE
                binding.listLayout.emptyList.visibility = View.VISIBLE
                binding.listLayout.emptyList.text = getString(R.string.no_internet_connection)
                Timber.w(ne)
            }
        }

        binding.toolbar.backBtn.setOnClickListener {
            requireActivity().finish()
        }

    }

    private fun initRecyclerView() {
        binding.listLayout.list.apply {
            paymentAdapter = PaymentAdapter(applicableList)
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = paymentAdapter
        }
    }

}