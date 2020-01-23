package project.ui.support.configuration

import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ConfigurationFragmentProvider {

    @ContributesAndroidInjector(modules = [ConfigurationFragmentModule::class])
    internal abstract fun provideOpenSourceFragmentFactory(): ConfigurationFragment
}


