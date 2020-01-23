package project.ui.support.shipping

import dagger.Module
import dagger.Provides
import androidx.lifecycle.ViewModelProviders
import project.ViewModelProviderFactory


@Module
class ShippingFragmentModule {

    @Provides
    fun provideShippingViewModel(
        factory: ViewModelProviderFactory,
        fragment: ShippingFragment
    ): ShippingViewModel =
        ViewModelProviders.of(fragment, factory)[ShippingViewModel::class.java]

}




