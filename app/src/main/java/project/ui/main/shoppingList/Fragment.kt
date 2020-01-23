package project.ui.main.shoppingList


import android.os.Bundle
import ir.sinapp.sarnakh.BR
import ir.sinapp.sarnakh.databinding.FragmentShoppingListBinding
import project.ui.base.BaseFragment
import javax.inject.Inject



class ShoppingListFragment : BaseFragment<FragmentShoppingListBinding, ShoppingListViewModel>(FragmentShoppingListBinding::class.java) {

    override val bindingVariable: Int get() = BR.viewModel

    @Inject
    override lateinit var viewModel: ShoppingListViewModel


    companion object {

        fun newInstance(): ShoppingListFragment {
            val args = Bundle()
            val fragment = ShoppingListFragment()
            fragment.arguments = args
            return fragment
        }

    }


}






