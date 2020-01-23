package project.ui.support.payment

import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class PaymentFragmentProvider {

    @ContributesAndroidInjector(modules = [PaymentFragmentModule::class])
    internal abstract fun provideOpenSourceFragmentFactory(): PaymentFragment
}


