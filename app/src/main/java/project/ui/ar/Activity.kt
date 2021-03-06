package project.ui.ar

import android.Manifest
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import cn.easyar.Engine
import project.ui.base.BaseActivity
import javax.inject.Inject

import ir.sinapp.sarnakh.BR
import ir.sinapp.sarnakh.R
import ir.sinapp.sarnakh.databinding.ActivityArBinding
import project.utils.AppLogger
import project.utils.ar.GLView
import android.view.ViewGroup
import android.view.Window
import com.jakewharton.rxbinding3.view.clicks
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.RuntimePermissions
import studio.carbonylgroup.textfieldboxes.ExtendedEditText

@RuntimePermissions
class ARActivity : BaseActivity<ActivityArBinding, ARViewModel>(ActivityArBinding::class.java),
    ARNavigator {

    override val bindingVariable: Int
        get() = BR.viewModel

    @Inject
    override lateinit var viewModel: ARViewModel

    private lateinit var glView: GLView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.navigator = this

        showCameraWithPermissionCheck()

        if (!Engine.initialize(this, getString(R.string.ar_api_key))) {
            AppLogger.e("HelloAR", "Initialization Failed.");
            Toast.makeText(this, Engine.errorMessage(), Toast.LENGTH_LONG).show();
            return;
        }


        viewModel += binding.btnAnswer.clicks().subscribe {
            openAnswerActivity()
        }

    }

    @NeedsPermission(Manifest.permission.CAMERA)
    fun showCamera() {

        glView = GLView(this)

        binding.preview.addView(
            glView, ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        )

    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResult(requestCode, grantResults)
    }



    override fun openAnswerActivity() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) // before
        dialog.setContentView(R.layout.dialog_question)
        val editText = dialog.findViewById<ExtendedEditText>(R.id.answer)
        val btn = dialog.findViewById<View>(R.id.bt_try)
        viewModel += btn.clicks().subscribe {
            dialog.dismiss()
            if (editText.text.toString() == "علی بابا") {
                Toast.makeText(
                    this,
                    " :) تبریک میگم... شما قاتل بروسلی رو به همین راحتی پیدا کردین ",
                    Toast.LENGTH_LONG
                ).show()
                finish()
            } else {
                Toast.makeText(this, "wrong", Toast.LENGTH_LONG).show()
            }
        }
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(true)
        dialog.show()
    }

}




