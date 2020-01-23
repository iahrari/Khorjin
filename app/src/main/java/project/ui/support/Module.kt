package project.ui.support

import dagger.Module
import dagger.Provides
import androidx.lifecycle.ViewModelProviders
import project.ViewModelProviderFactory


@Module
class SupportActivityModule {

    @Provides
    fun provideSupportViewModel(
        factory: ViewModelProviderFactory,
        activity: SupportActivity
    ): SupportViewModel =
        ViewModelProviders.of(activity, factory)[SupportViewModel::class.java]

}




