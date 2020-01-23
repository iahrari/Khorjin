package project.ui.support.payment


import android.os.Bundle
import ir.sinapp.sarnakh.BR
import ir.sinapp.sarnakh.databinding.FragmentPaymentBinding
import project.ui.base.BaseFragment
import javax.inject.Inject



class PaymentFragment : BaseFragment<FragmentPaymentBinding, PaymentViewModel>(FragmentPaymentBinding::class.java) {

    override val bindingVariable: Int get() = BR.viewModel

    @Inject
    override lateinit var viewModel: PaymentViewModel


    companion object {

        fun newInstance(): PaymentFragment {
            val args = Bundle()
            val fragment = PaymentFragment()
            fragment.arguments = args
            return fragment
        }

    }


}






