package project.ui.support.payment

import dagger.Module
import dagger.Provides
import androidx.lifecycle.ViewModelProviders
import project.ViewModelProviderFactory


@Module
class PaymentFragmentModule {

    @Provides
    fun providePaymentViewModel(
        factory: ViewModelProviderFactory,
        fragment: PaymentFragment
    ): PaymentViewModel =
        ViewModelProviders.of(fragment, factory)[PaymentViewModel::class.java]

}




