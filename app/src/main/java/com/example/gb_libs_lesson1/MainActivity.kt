package com.example.gb_libs_lesson1

import android.os.Bundle
import com.example.gb_libs_lesson1.databinding.ActivityMainBinding
import com.example.gb_libs_lesson1.mvp.view.BackButtonListener
import com.example.gb_libs_lesson1.mvp.view.ui.MainView
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainView {

    @Inject lateinit var navigationHolder: NavigatorHolder

    private val navigator = SupportAppNavigator(this, R.id.container)

    private val presenter by moxyPresenter {
            App.instance.appComponent.presenter()
    }

    private var _vb: ActivityMainBinding? = null

    private val vb
        get() = _vb!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        App.instance.appComponent.inject(this)

        _vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigationHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigationHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }
        presenter.backPressed()
    }
}