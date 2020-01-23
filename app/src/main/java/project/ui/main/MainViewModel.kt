package project.ui.main

import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableInt
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.plusAssign
import org.greenrobot.eventbus.EventBus
import project.data.DataManager
import project.ui.base.BaseViewModel
import project.utils.*
import project.utils.extension.forDatabase
import project.utils.extension.forIo
import project.utils.extension.onUi
import project.utils.rx.SchedulerProvider
import java.util.concurrent.TimeUnit


class MainViewModel(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) :
    BaseViewModel<MainNavigator>(dataManager, schedulerProvider) {

    val tab = ObservableInt(1)
    val netView = ObservableBoolean(false)

    //

}




