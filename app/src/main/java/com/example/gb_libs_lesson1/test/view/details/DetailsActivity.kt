package com.example.gb_libs_lesson1.test.view.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gb_libs_lesson1.R
import com.example.gb_libs_lesson1.test.presenter.details.DetailsPresenter
import com.example.gb_libs_lesson1.test.presenter.details.PresenterDetailsContract
import kotlinx.android.synthetic.main.fragment_details.*
import java.util.*

class DetailsActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        supportFragmentManager.beginTransaction()
            .add(
                R.id.detailsFragmentContainer,
                DetailsFragment.newInstance(intent.getIntExtra(TOTAL_COUNT_EXTRA, 0))
            )
            .commitAllowingStateLoss()
    }

    companion object {

        private const val TOTAL_COUNT_EXTRA = "TOTAL_COUNT_EXTRA"

        fun getIntent(context: Context, totalCount: Int): Intent {
            return Intent(context, DetailsActivity::class.java).apply {
                putExtra(TOTAL_COUNT_EXTRA, totalCount)
            }
        }
    }
}
