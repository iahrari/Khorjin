package project.ui.detail

import android.os.Bundle
import project.ui.base.BaseActivity
import javax.inject.Inject

import ir.sinapp.sarnakh.BR
import ir.sinapp.sarnakh.databinding.ActivityDetailBinding


class DetailActivity : BaseActivity<ActivityDetailBinding, DetailViewModel>(ActivityDetailBinding::class.java), DetailNavigator {

    override val bindingVariable: Int
        get() = BR.viewModel

    @Inject
    override lateinit var viewModel: DetailViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.navigator = this

    }

}




