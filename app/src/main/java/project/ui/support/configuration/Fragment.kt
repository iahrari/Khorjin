package project.ui.support.configuration


import android.os.Bundle
import ir.sinapp.sarnakh.BR
import ir.sinapp.sarnakh.databinding.FragmentConfigurationBinding
import project.ui.base.BaseFragment
import javax.inject.Inject



class ConfigurationFragment : BaseFragment<FragmentConfigurationBinding, ConfigurationViewModel>(FragmentConfigurationBinding::class.java) {

    override val bindingVariable: Int get() = BR.viewModel

    @Inject
    override lateinit var viewModel: ConfigurationViewModel


    companion object {

        fun newInstance(): ConfigurationFragment {
            val args = Bundle()
            val fragment = ConfigurationFragment()
            fragment.arguments = args
            return fragment
        }

    }


}






