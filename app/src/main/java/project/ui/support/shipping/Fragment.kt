package project.ui.support.shipping


import android.os.Bundle
import ir.sinapp.sarnakh.BR
import ir.sinapp.sarnakh.databinding.FragmentShippingBinding
import project.ui.base.BaseFragment
import javax.inject.Inject



class ShippingFragment : BaseFragment<FragmentShippingBinding, ShippingViewModel>(FragmentShippingBinding::class.java) {

    override val bindingVariable: Int get() = BR.viewModel

    @Inject
    override lateinit var viewModel: ShippingViewModel


    companion object {

        fun newInstance(): ShippingFragment {
            val args = Bundle()
            val fragment = ShippingFragment()
            fragment.arguments = args
            return fragment
        }

    }


}






