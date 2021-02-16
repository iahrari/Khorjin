package project.ui.main.shoppingList

import androidx.databinding.ObservableInt
import project.data.DataManager
import project.ui.base.BaseViewModel
import project.utils.rx.SchedulerProvider

class ShoppingListViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider)
    : BaseViewModel<ShoppingListNavigator>(dataManager, schedulerProvider) {

    //

    val i1 = ObservableInt(1)
    val i2 = ObservableInt(1)
    val i3 = ObservableInt(1)

    val sum = ObservableInt(27000)

    fun value(x: Int): String {
        return when (x) {
            1 -> i1.get().toString()
            2 -> i1.get().toString()
            3 -> i1.get().toString()
            else -> ""
        }
    }

    fun onAdd(x: Int) {
        sum.set(sum.get() + 9000)
        when (x) {
            1 -> i1.set(i1.get()+1)
            2 -> i2.set(i2.get()+1)
            3 -> i3.set(i3.get()+1)
        }
    }

    fun onSub(x: Int) {

        when (x) {
            1 -> {
                val a = i1.get() - 1
                if (a >= 1) {
                    sum.set(sum.get() - 9000)
                    i1.set(a)
                }
            }
            2 -> {
                val a = i2.get() - 1
                if (a >= 1) {
                    sum.set(sum.get() - 9000)
                    i2.set(a)
                }
            }
            3 -> {
                val a = i3.get() - 1
                if (a >= 1) {
                    sum.set(sum.get() - 9000)
                    i3.set(a)
                }
            }
        }
    }

}




