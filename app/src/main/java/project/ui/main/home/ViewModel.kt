package project.ui.main.home

import project.data.DataManager
import project.ui.base.BaseViewModel
import project.utils.rx.SchedulerProvider

class HomeViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider)
    : BaseViewModel<HomeNavigator>(dataManager, schedulerProvider) {

    //
    val list = listOf(
//        "https://workshop.hostiran.net/file/2018/07/01-2.png",
        "https://dkstatics-public.digikala.com/digikala-products/3731812.jpg",
        "https://agrofoodnews.com/wp-content/uploads/2017/05/%D8%B3%D8%AD%D8%B1%D8%AE%DB%8C%D8%B2.jpg",
        "https://static1.borna.news/servev2/4h25lJZC3Kk2/KxuoffTghAA,/%D8%AF%DA%A9%D8%AA%D8%B1+%D9%81%D8%B1%D9%87%D8%A7%D8%AF+%D8%B3%D8%AD%D8%B1+%D8%AE%DB%8C%D8%B2+%D8%B9%D8%B6%D9%88+%D9%87%DB%8C%D8%A3%D8%AA+%D9%85%D8%AF%DB%8C%D8%B1%D9%87+%DA%AF%D8%B1%D9%88%D9%87+%DA%A9%D8%B4%D8%A7%D9%88%D8%B1%D8%B2%DB%8C+%D8%B5%D9%86%D8%B9%D8%AA%DB%8C+%D8%B3%D8%AD%D8%B1%D8%AE%DB%8C%D8%B2.jpg",
        "https://banner-design.ir/wp-content/uploads/POST.jpg",
        "https://banner-design.ir/wp-content/uploads/nowruz-post-offer.jpg",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ8dVYL-1SYWM9-XC-z0bEso6zEhqVVM0_1DZmHHuuTVWiy7Bmk",
        "https://banner-design.ir/wp-content/uploads/eid-post-blog-fetr96.jpg)"
    )

    fun support() {
        navigator.openSupport()
    }

    fun detail() {
        navigator.openDetail()
    }

    fun profile() {
        navigator.openProfile()
    }



}




