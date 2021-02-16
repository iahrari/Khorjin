package project.ui.main.home


import android.os.Bundle
import android.text.SpannableString
import android.text.TextUtils
import android.view.View
import androidx.fragment.app.commitNow
import cn.nekocode.badge.BadgeDrawable
import com.jakewharton.rxbinding3.view.clicks
import ir.sinapp.sarnakh.BR
import ir.sinapp.sarnakh.R
import ir.sinapp.sarnakh.databinding.FragmentHomeBinding
import org.greenrobot.eventbus.EventBus
import project.adapter.DetailSliderAdapter
import project.ui.base.BaseFragment
import project.ui.main.shoppingList.ShoppingListFragment
import project.utils.*
import project.utils.widget.toolbar.Dp
import project.utils.widget.toolbar.Px
import ss.com.bannerslider.Slider
import javax.inject.Inject


class HomeFragment :
    BaseFragment<FragmentHomeBinding, HomeViewModel>(FragmentHomeBinding::class.java), HomeNavigator {

    override val bindingVariable: Int
        get() = BR.viewModel

    @Inject
    override lateinit var viewModel: HomeViewModel


    companion object {

        fun newInstance(): HomeFragment {
            val args = Bundle()
            val fragment = HomeFragment()
            fragment.arguments = args
            return fragment
        }

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.navigator = this

        Slider.init(GlideImageLoadingService(baseActivity))

        binding.slider.setAdapter(DetailSliderAdapter(viewModel.list))

        viewModel += binding.lytMap.clicks().subscribe {
            EventBus.getDefault().post(EventMap())
        }

        viewModel += binding.lytWheel.clicks().subscribe {
            EventBus.getDefault().post(EventWheel())
        }

        val font = CommonUtils.typefaceFromAsset("fonts/IRANYekanMobileBold.ttf", requireContext())

        val drawable = BadgeDrawable.Builder()
            .type(BadgeDrawable.TYPE_WITH_TWO_TEXT_COMPLEMENTARY)
            .text1("45%")
            .text2("حمایت شده")
            .typeFace(font)
            .padding(
                30f.dp(),
                30f.dp(),
                30f.dp(),
                30f.dp(),
                30f.dp()
            )
            .strokeWidth(2).build()

        val drawable2 = BadgeDrawable.Builder()
            .type(BadgeDrawable.TYPE_WITH_TWO_TEXT_COMPLEMENTARY)
            .text1("27%")
            .text2("حمایت شده")
            .typeFace(font)
            .padding(
                30f.dp(),
                30f.dp(),
                30f.dp(),
                30f.dp(),
                30f.dp()
            )
            .strokeWidth(2).build()

        val drawable3 = BadgeDrawable.Builder()
            .type(BadgeDrawable.TYPE_WITH_TWO_TEXT_COMPLEMENTARY)
            .text1("95%")
            .text2("حمایت شده")
            .typeFace(font)
            .padding(
                30f.dp(),
                30f.dp(),
                30f.dp(),
                30f.dp(),
                30f.dp()
            )
            .strokeWidth(2).build()


        binding.badge1.text = drawable.toSpannable()
        binding.badge2.text = drawable2.toSpannable()
        binding.badge3.text = drawable3.toSpannable()
        binding.badge4.text = drawable.toSpannable()
        binding.badge5.text = drawable2.toSpannable()
        binding.badge6.text = drawable3.toSpannable()

    }

    override fun openSupport() {
        EventBus.getDefault().post(EventSupport())
    }

    override fun openProfile() {
        EventBus.getDefault().post(EventProfile())
    }

    override fun openDetail() {
        EventBus.getDefault().post(EventDetail())
    }

    override fun openShoppingList(v: View) {
        requireActivity().supportFragmentManager.commitNow {
            replace(R.id.fragment, ShoppingListFragment.newInstance())
        }
    }

}






