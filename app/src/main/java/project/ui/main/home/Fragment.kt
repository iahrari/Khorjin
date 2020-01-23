package project.ui.main.home


import android.os.Bundle
import android.text.SpannableString
import android.text.TextUtils
import android.view.View
import cn.nekocode.badge.BadgeDrawable
import com.jakewharton.rxbinding3.view.clicks
import ir.sinapp.sarnakh.BR
import ir.sinapp.sarnakh.databinding.FragmentHomeBinding
import org.greenrobot.eventbus.EventBus
import project.adapter.DetailSliderAdapter
import project.ui.base.BaseFragment
import project.utils.*
import project.utils.widget.toolbar.Dp
import project.utils.widget.toolbar.Px
import ss.com.bannerslider.Slider
import javax.inject.Inject


class HomeFragment :
    BaseFragment<FragmentHomeBinding, HomeViewModel>(FragmentHomeBinding::class.java) {

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

        Slider.init(GlideImageLoadingService(baseActivity))
        val list = listOf(
            "https://workshop.hostiran.net/file/2018/07/01-2.png",
            "https://banner-design.ir/wp-content/uploads/POST.jpg",
            "https://banner-design.ir/wp-content/uploads/nowruz-post-offer.jpg",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ8dVYL-1SYWM9-XC-z0bEso6zEhqVVM0_1DZmHHuuTVWiy7Bmk",
            "https://banner-design.ir/wp-content/uploads/eid-post-blog-fetr96.jpg)"
        )
        binding.slider.setAdapter(DetailSliderAdapter(list))

        viewModel += binding.lytMap.clicks().subscribe {
            EventBus.getDefault().post(EventMap())
        }

        viewModel += binding.lytWheel.clicks().subscribe {
            EventBus.getDefault().post(EventWheel())
        }

        val font = CommonUtils.typefaceFromAsset("fonts/IRANYekanMobileBold.ttf", context!!)

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


        viewModel += binding.lytFarmer1.clicks().subscribe {
            EventBus.getDefault().post(EventProfile())
        }
        viewModel += binding.lytFarmer2.clicks().subscribe {
            EventBus.getDefault().post(EventProfile())
        }
        viewModel += binding.lytFarmer3.clicks().subscribe {
            EventBus.getDefault().post(EventProfile())
        }



    }

}






