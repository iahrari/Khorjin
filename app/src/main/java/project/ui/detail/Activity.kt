package project.ui.detail

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import ir.sinapp.sarnakh.BR
import ir.sinapp.sarnakh.R
import ir.sinapp.sarnakh.databinding.ActivityDetailBinding
import project.ui.base.BaseActivity
import project.utils.Tools
import javax.inject.Inject


class DetailActivity : BaseActivity<ActivityDetailBinding, DetailViewModel>(ActivityDetailBinding::class.java), DetailNavigator {

    override val bindingVariable: Int
        get() = BR.viewModel

    @Inject
    override lateinit var viewModel: DetailViewModel

    private val parent_view: View? = null
    private lateinit var tv_qty: TextView

    private val array_size_bt = intArrayOf(
        R.id.bt_size_s,
        R.id.bt_size_m,
        R.id.bt_size_l,
        R.id.bt_size_xl
    )

    @SuppressLint("SetTextI18n")
    private fun initComponent() {
        Tools.displayImageOriginal(
            this,
            findViewById<View>(R.id.image_1) as ImageView,
            R.drawable.mahsol2
        )
        Tools.displayImageOriginal(
            this,
            findViewById<View>(R.id.image_2) as ImageView,
            R.drawable.mahsol1
        )
        Tools.displayImageOriginal(
            this,
            findViewById<View>(R.id.image_3) as ImageView,
            R.drawable.m1
        )
        Tools.displayImageOriginal(
            this,
            findViewById<View>(R.id.image_4) as ImageView,
            R.drawable.image_shop_12
        )
        Tools.displayImageOriginal(
            this,
            findViewById<View>(R.id.image_5) as ImageView,
            R.drawable.image_shop_13
        )
        tv_qty = findViewById<View>(R.id.tv_qty) as TextView
        (findViewById<View>(R.id.fab_qty_sub) as FloatingActionButton).setOnClickListener {
            var qty = tv_qty.getText().toString().toInt()
            if (qty > 1) {
                qty--
                tv_qty.text = qty.toString() + ""
            }
        }
        (findViewById<View>(R.id.fab_qty_add) as FloatingActionButton).setOnClickListener {
            var qty = tv_qty.getText().toString().toInt()
            if (qty < 10) {
                qty++
                tv_qty.text = qty.toString() + ""
            }
        }

        (findViewById<View>(R.id.bt_add_to_cart) as AppCompatButton).setOnClickListener {
            Snackbar.make(
                parent_view!!,
                "Add to Cart",
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.navigator = this
        initComponent()

    }

    fun setSize(v: View) {
        val bt = v as Button
        bt.setEnabled(false)
        bt.setTextColor(Color.WHITE)
        for (id in array_size_bt) {
            if (v.getId() != id) {
                val btUnselected: Button = findViewById<View>(id) as Button
                btUnselected.isEnabled = true
                btUnselected.setTextColor(Color.BLACK)
            }
        }
    }
}




