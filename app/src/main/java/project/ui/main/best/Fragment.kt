package project.ui.main.best


import android.os.Bundle
import android.util.Log
import ir.sinapp.sarnakh.BR
import ir.sinapp.sarnakh.databinding.FragmentBestBinding
import project.ui.base.BaseFragment
import project.utils.widget.flipperclock.CountDownClock
import javax.inject.Inject



class BestFragment : BaseFragment<FragmentBestBinding, BestViewModel>(FragmentBestBinding::class.java) {

    override val bindingVariable: Int get() = BR.viewModel

    @Inject
    override lateinit var viewModel: BestViewModel


    companion object {

        fun newInstance(): BestFragment {
            val args = Bundle()
            val fragment = BestFragment()
            fragment.arguments = args
            return fragment
        }

    }



    override fun onResume() {
        super.onResume()


        binding.countdownClockFirst.setCountdownListener(object: CountDownClock.CountdownCallBack {
            override fun countdownAboutToFinish() {
                Log.d("here","Countdown first is about to finish")
            }

            override fun countdownFinished() {
                Log.d("here", "Countdown first finished")
                binding.countdownClockFirst.startCountDown(15000)
            }
        })

        binding.countdownClockFirst.startCountDown(15000)

    }

    override fun onPause() {
        super.onPause()

        binding.countdownClockFirst.resetCountdownTimer()
    }


}






