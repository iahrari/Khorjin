package project.ui.support.shipping

import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ShippingFragmentProvider {

    @ContributesAndroidInjector(modules = [ShippingFragmentModule::class])
    internal abstract fun provideOpenSourceFragmentFactory(): ShippingFragment
}


