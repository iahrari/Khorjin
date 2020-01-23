package project.ui.support.configuration

import dagger.Module
import dagger.Provides
import androidx.lifecycle.ViewModelProviders
import project.ViewModelProviderFactory


@Module
class ConfigurationFragmentModule {

    @Provides
    fun provideConfigurationViewModel(
        factory: ViewModelProviderFactory,
        fragment: ConfigurationFragment
    ): ConfigurationViewModel =
        ViewModelProviders.of(fragment, factory)[ConfigurationViewModel::class.java]

}




