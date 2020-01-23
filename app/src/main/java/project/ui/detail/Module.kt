package project.ui.detail

import dagger.Module
import dagger.Provides
import androidx.lifecycle.ViewModelProviders
import project.ViewModelProviderFactory


@Module
class DetailActivityModule {

    @Provides
    fun provideDetailViewModel(
        factory: ViewModelProviderFactory,
        activity: DetailActivity
    ): DetailViewModel =
        ViewModelProviders.of(activity, factory)[DetailViewModel::class.java]

}




