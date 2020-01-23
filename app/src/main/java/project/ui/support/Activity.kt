package project.ui.support

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.View
import androidx.fragment.app.*
import ir.sinapp.sarnakh.BR
import ir.sinapp.sarnakh.R
import ir.sinapp.sarnakh.databinding.ActivitySupportBinding
import project.ui.base.BaseActivity
import project.ui.base.BaseFragment
import project.ui.support.configuration.ConfigurationFragment
import project.ui.support.payment.PaymentFragment
import project.ui.support.shipping.ShippingFragment
import project.utils.Tools
import javax.inject.Inject


class SupportActivity :
    BaseActivity<ActivitySupportBinding, SupportViewModel>(ActivitySupportBinding::class.java),
    SupportNavigator {

    override val bindingVariable: Int
        get() = BR.viewModel

    @Inject
    override lateinit var viewModel: SupportViewModel

    enum class State {
        SHIPPING, PAYMENT, CONFIRMATION
    }

    private var arrayState =
        arrayOf(State.SHIPPING, State.PAYMENT, State.CONFIRMATION)


    private var idxState = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.navigator = this

        initToolbar()
        initComponent()

        displayFragment(State.SHIPPING)
    }


    private fun initComponent() {

        binding.imagePayment.setColorFilter(
            resources.getColor(R.color.grey_10),
            PorterDuff.Mode.SRC_ATOP
        )

        binding.imageConfirm.setColorFilter(
            resources.getColor(R.color.grey_10),
            PorterDuff.Mode.SRC_ATOP
        )

        binding.lytNext.setOnClickListener(View.OnClickListener {
            if (idxState == arrayState.size - 1) return@OnClickListener
            idxState++
            displayFragment(arrayState[idxState])
        })

        binding.lytPrevious.setOnClickListener(View.OnClickListener {
            if (idxState < 1) return@OnClickListener
            idxState--
            displayFragment(arrayState[idxState])
        })
    }

    private fun initToolbar() {
        binding.toolbar.setNavigationIcon(R.drawable.ic_close)
        binding.toolbar.navigationIcon?.setColorFilter(
            resources.getColor(R.color.grey_60),
            PorterDuff.Mode.SRC_ATOP
        )
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.title = null
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        Tools.setSystemBarColor(this, R.color.white)
        Tools.setSystemBarLight(this)
    }

    private fun displayFragment(state: State) {
        var fragment: BaseFragment<*,*>? = null

        refreshStepTitle()

        when {
            state.name.equals(State.SHIPPING.name, ignoreCase = true) -> {
                fragment = ShippingFragment.newInstance()
                binding.tvShipping.setTextColor(resources.getColor(R.color.grey_90))
                binding.imageShipping.clearColorFilter()
            }
            state.name.equals(State.PAYMENT.name, ignoreCase = true) -> {
                fragment = PaymentFragment.newInstance()
                binding.lineFirst.setBackgroundColor(resources.getColor(R.color.colorPrimary))
                binding.imageShipping.setColorFilter(
                    resources.getColor(R.color.colorPrimary),
                    PorterDuff.Mode.SRC_ATOP
                )
                binding.imagePayment.clearColorFilter()
                binding.tvPayment.setTextColor(resources.getColor(R.color.grey_90))
            }
            state.name.equals(State.CONFIRMATION.name, ignoreCase = true) -> {
                fragment = ConfigurationFragment.newInstance()
                binding.lineSecond.setBackgroundColor(resources.getColor(R.color.colorPrimary))
                binding.imagePayment.setColorFilter(
                    resources.getColor(R.color.colorPrimary),
                    PorterDuff.Mode.SRC_ATOP
                )
                binding.imageConfirm.clearColorFilter()
                binding.tvConfirm.setTextColor(resources.getColor(R.color.grey_90))
            }
        }
        if (fragment == null) return

        supportFragmentManager.commit {
            replace(R.id.frame_content, fragment)
        }
    }

    private fun refreshStepTitle() {
        binding.tvShipping.setTextColor(resources.getColor(R.color.grey_20))
        binding.tvPayment.setTextColor(resources.getColor(R.color.grey_20))
        binding.tvConfirm.setTextColor(resources.getColor(R.color.grey_20))
    }

}




