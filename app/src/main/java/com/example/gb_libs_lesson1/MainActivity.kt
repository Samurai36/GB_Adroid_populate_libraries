package com.example.gb_libs_lesson1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gb_libs_lesson1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainView {

    private val presenter = MainPresenter(this, CountersModel())

    private var _vb: ActivityMainBinding? = null

    private val vb
        get() = _vb!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb.root)

        vb.btnCounter1.setOnClickListener {
            presenter.counterClick1()
        }
        vb.btnCounter2.setOnClickListener {
            presenter.counterClick2()
        }
        vb.btnCounter3.setOnClickListener {
            presenter.counterClick3()
        }
    }

    override fun setButton1Text(text: String) {
        vb.btnCounter1.text = text
    }

    override fun setButton2Text(text: String) {
        vb.btnCounter2.text = text
    }

    override fun setButton3Text(text: String) {
        vb.btnCounter3.text = text
    }
}