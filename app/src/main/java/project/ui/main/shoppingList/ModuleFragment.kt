package project.ui.main.shoppingList

import dagger.Module
import dagger.Provides
import androidx.lifecycle.ViewModelProviders
import project.ViewModelProviderFactory


@Module
class ShoppingListFragmentModule {

    @Provides
    fun provideShoppingListViewModel(
        factory: ViewModelProviderFactory,
        fragment: ShoppingListFragment
    ): ShoppingListViewModel =
        ViewModelProviders.of(fragment, factory)[ShoppingListViewModel::class.java]

}




