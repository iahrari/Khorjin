package project.ui.main

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.core.view.get
import androidx.fragment.app.commitNow
import com.crashlytics.android.Crashlytics
import com.google.android.material.tabs.TabLayout
import com.google.firebase.iid.FirebaseInstanceId
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.view.visibility
import com.mikepenz.materialdrawer.AccountHeader
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.DividerDrawerItem
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import io.github.inflationx.calligraphy3.CalligraphyUtils
import ir.sinapp.sarnakh.BR
import ir.sinapp.sarnakh.R
import ir.sinapp.sarnakh.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.runBlocking
import org.greenrobot.eventbus.Subscribe
import project.ui.ar.ARActivity
import project.ui.base.BaseActivity
import project.ui.detail.DetailActivity
import project.ui.lucky.LuckyActivity
import project.ui.main.best.BestFragment
import project.ui.main.help.HelpFragment
import project.ui.main.home.HomeFragment
import project.ui.main.notification.NotificationFragment
import project.ui.main.setting.SettingFragment
import project.ui.main.shoppingList.ShoppingListFragment
import project.ui.map.MapActivity
import project.ui.profile.ProfileActivity
import project.ui.support.SupportActivity
import project.utils.*
import project.utils.navDrawer.MyDrawerItem
import javax.inject.Inject


class MainActivity :
    BaseActivity<ActivityMainBinding, MainViewModel>(ActivityMainBinding::class.java),
    MainNavigator {


    override val bindingVariable: Int get() = BR.viewModel

    @Inject
    override lateinit var viewModel: MainViewModel


    @Inject
    lateinit var drawerBuilder: DrawerBuilder


    @Inject
    lateinit var mPagerAdapter: MainPagerAdapter


    private lateinit var drawer: Drawer
    private lateinit var notificationItem: MyDrawerItem
    private lateinit var headerResult: AccountHeader
    private lateinit var itemProfile: ProfileDrawerItem


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.navigator = this
        currentLanguage


        showFragment(getString(R.string.home), 1)

        runBlocking {
            setUpText()
            setUpDrawer()
        }

        setUp()


        init()


    }

    private fun init() {

        val firstName = "سجاد"
        val lastName = "احمدی"
        val username = "sajjad.ahmadi.sh@gmail.com"
        val gender = true

        itemProfile.withName("$firstName $lastName")
        itemProfile.withEmail(username)
        itemProfile.withIcon(if (gender) R.drawable.ic_user_man else R.drawable.ic_user_woman)
        headerResult.updateProfile(itemProfile)

    }


    private fun setUpText() {

        binding.textSwitcher.setFactory {
            val t = TextView(this@MainActivity)
            t.gravity = Gravity.CENTER
            t.setTextColor(resources.getColor(R.color.colorPrimary))
            t.textSize = 21f
            t.typeface = CommonUtils.typefaceFromAsset("fonts/IRANYekanMobileBold.ttf", this)
            t
        }

        val `in` = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left)
        val out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right)

        binding.textSwitcher.inAnimation = `in`
        binding.textSwitcher.outAnimation = out

        binding.textSwitcher.setCurrentText(getString(R.string.app_name))
    }


    private fun setUpDrawer() {

        itemProfile = ProfileDrawerItem()
            .withIdentifier(1)
            .withName("profile")
            .withIcon(R.drawable.ic_user_man)


        headerResult = AccountHeaderBuilder()
            .withActivity(this)
            .withTextColorRes(R.color.white)
            .withAccountHeader(R.layout.drawer_header)
            .withHeaderBackground(R.drawable.back)
            .withSelectionListEnabledForSingleProfile(false)
            .withCloseDrawerOnProfileListClick(false)
            .addProfiles(itemProfile)
            .build()

        val font = CommonUtils.typefaceFromAsset("fonts/IRANYekanMobileBold.ttf", this)


        val itemHome = MyDrawerItem()
            .withSelected(true)
            .withIconTintingEnabled(true)
            .withName(R.string.home)
            .withIcon(R.drawable.ic_classmatelogo)
            .withTypeface(font)
            .withTextColorRes(R.color.grey_800)


        val itemBest = MyDrawerItem()
            .withSelected(false)
            .withIconTintingEnabled(true)
            .withName(R.string.best_farmer)
            .withIcon(R.drawable.ic_calendar)
            .withTypeface(font)
            .withTextColorRes(R.color.grey_800)

        val itemShoppingList = MyDrawerItem()
            .withSelected(false)
            .withIconTintingEnabled(true)
            .withName(R.string.shopping_list)
            .withIcon(R.drawable.ic_shopping_basket)
            .withTypeface(font)
            .withTextColorRes(R.color.grey_800)

        val itemLearnAR = MyDrawerItem()
            .withSelected(false)
            .withIconTintingEnabled(true)
            .withName(R.string.Learn_AR)
            .withIcon(R.drawable.ic_shopping_basket)
            .withTypeface(font)
            .withTextColorRes(R.color.grey_800)


//
        notificationItem = MyDrawerItem()
            .withName(R.string.notifications)
            .withSelected(false)
            .withIconTintingEnabled(true)
            .withIcon(R.drawable.ic_notification)
            .withTypeface(font)
            .withTextColorRes(R.color.grey_800)



        val itemSetting = MyDrawerItem()
            .withSelected(false)
            .withName(R.string.settings)
            .withIconTintingEnabled(true)
            .withIcon(R.drawable.ic_settings)
            .withTypeface(font)
            .withTextColorRes(R.color.grey_800)


        val itemHelp = MyDrawerItem()
            .withSelected(false)
            .withIconTintingEnabled(true)
            .withName(R.string.help)
            .withIcon(R.drawable.ic_help)
            .withTypeface(font)
            .withTextColorRes(R.color.grey_800)

        val itemInvite = MyDrawerItem()
            .withSelected(false)
            .withIconTintingEnabled(true)
            .withName(R.string.invite)
            .withIcon(R.drawable.ic_share)
            .withTypeface(font)
            .withTextColorRes(R.color.grey_800)

        drawer = drawerBuilder
            .withOnDrawerListener(object : Drawer.OnDrawerListener {
                override fun onDrawerSlide(drawerView: View?, slideOffset: Float) {
                    binding.content.translationX =
                        slideOffset * drawerView!!.width * if (isRtl()) -1 else 1
                }

                override fun onDrawerClosed(drawerView: View?) {
                }

                override fun onDrawerOpened(drawerView: View?) {
                }

            })
            .withAccountHeader(headerResult)
            .addDrawerItems(
                itemHome,
                itemShoppingList,
                itemLearnAR,
                itemBest,
                notificationItem,
                DividerDrawerItem().withSelectable(false),
                itemSetting,
                itemInvite,
                itemHelp
            )
            .withOnDrawerItemClickListener { _, position, drawerItem ->
                runBlocking {
                    if (viewModel.tab.get() != position) {
                        drawerItem as MyDrawerItem
                        showFragment(drawerItem.name.getText(this@MainActivity), position)
                    }
                }
                false
            }
            .withToolbar(binding.toolbar)
            .withActionBarDrawerToggleAnimated(true)
            .build()

        Tools.setSystemBarColor(this, R.color.colorPrimaryDark)
        Tools.changeNavigateionIconColor(
            binding.toolbar,
            resources.getColor(R.color.colorPrimaryDark)
        )

    }


    private fun setUp() {


        mPagerAdapter.count = 2

/*
        binding.viewPager.adapter = mPagerAdapter


        binding.viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tabs))


        binding.tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                EventBus.getDefault().post(ClassesAdapter.ToggleEvent(-1))

                viewModel.tab.set(tab.position != 0)

                if (binding.backDrop.visibility == View.VISIBLE)
                    toggleFabMode()

                val selectedLayout =
                    (binding.tabs.getChildAt(0) as ViewGroup).getChildAt(tab.position) as LinearLayout
                val selectedText = selectedLayout.getChildAt(1) as TextView
                CalligraphyUtils.applyFontToTextView(
                    binding.tabs.context,
                    selectedText,
                    "fonts/IRANYekanMobileBold.ttf"
                )
                selectedText.textSize = 26f

                binding.viewPager.setCurrentItem(tab.position, true)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                val selectedLayout =
                    (binding.tabs.getChildAt(0) as ViewGroup).getChildAt(tab.position) as LinearLayout
                val selectedText = selectedLayout.getChildAt(1) as TextView
                CalligraphyUtils.applyFontToTextView(
                    binding.tabs.context,
                    selectedText,
                    "fonts/IRANYekanMobileRegular.ttf"
                )
                selectedText.textSize = 26f
            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
*/


    }


    private var rotate = false
    private var flag = false



    @Subscribe
    fun event(event: EventAr) {
        openARActivity()
    }

    @Subscribe
    fun event(event: EventLang) {
        setLanguage(event.l, this)
    }


    @Subscribe
    fun event(event: EventProfile) {
        openProfileActivity()
    }


    override fun openARActivity() {
        launchActivity<ARActivity> {}
        Bungee.fade(this)
    }


    @Subscribe
    fun event(event: EventWheel) {
        openLuckyActivity()
    }

    override fun openLuckyActivity() {
        launchActivity<LuckyActivity> {}
        Bungee.fade(this)
    }

    override fun openProfileActivity() {
        launchActivity<ProfileActivity> {}
        Bungee.fade(this)
    }

    override fun openDetailActivity() {
        launchActivity<DetailActivity> {}
        Bungee.fade(this)
    }

    override fun openSupportActivity() {
        launchActivity<SupportActivity> {}
        Bungee.fade(this)
    }


    @Subscribe
    fun event(event: EventMap) {
        openMapActivity()
    }


    @Subscribe
    fun event(event: EventDetail) {
        openDetailActivity()
    }

    @Subscribe
    fun event(event: EventSupport) {
        openSupportActivity()
    }

    override fun openMapActivity() {
        launchActivity<MapActivity> {}
        Bungee.fade(this)
    }

    override fun onBackPressed() {
        when {
            drawer.isDrawerOpen -> drawer.closeDrawer()
            viewModel.tab.get() != 1 -> drawer.setSelectionAtPosition(1)
            else -> super.onBackPressed()
        }
    }


    private fun showFragment(name: String, position: Int) {
        viewModel.tab.set(position)

        try {
            binding.textSwitcher?.setText(name)
        } catch (e: Exception) {
        }

        val fragment = when (name) {
            getString(R.string.home) -> HomeFragment.newInstance()
            getString(R.string.shopping_list) -> ShoppingListFragment.newInstance()
            getString(R.string.best) -> BestFragment.newInstance()
            getString(R.string.notifications) -> NotificationFragment.newInstance()
            //            getString(R.string.archived_class) -> ArchiveFragment.newInstance()
            //            getString(R.string.invite) -> HideFragment.newInstance()
            getString(R.string.settings) -> SettingFragment.newInstance()
            getString(R.string.help) -> HelpFragment.newInstance()
            else -> null
        } ?: return
        supportFragmentManager.commitNow {
            replace(R.id.fragment, fragment)
        }
    }


}


