package project.ui.main.shoppingList

import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ShoppingListFragmentProvider {

    @ContributesAndroidInjector(modules = [ShoppingListFragmentModule::class])
    internal abstract fun provideOpenSourceFragmentFactory(): ShoppingListFragment
}


