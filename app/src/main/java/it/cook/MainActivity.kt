package it.cook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatDelegate
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import it.cook.network.ApiHelper
import it.cook.network.RetrofitBuilder
import it.cook.utils.ViewModelFactory
import it.cook.viewmodel.MainViewModel
import it.cook.viewmodel.NavDrawerState

class MainActivity : AppCompatActivity() {

    private lateinit var container: View
    private lateinit var navDrawerState: NavDrawerState
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

//        val button = findViewById<Button>(R.id.button)
        container = findViewById(R.id.motionLayout1)
        val drawerLayout = findViewById<DrawerLayout>(R.id.motionLayout)


/*
        button.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
            changeState(container)
        }
*/
        drawerLayout.setScrimColor(resources.getColor(android.R.color.transparent))

        navDrawerState = ViewModelProvider(this@MainActivity).get(NavDrawerState::class.java)
        navDrawerState.isOpen.observe(this, Observer {
            if (it) {
                drawerLayout.openDrawer(GravityCompat.START)
                changeState(container)
            }
        })
    }

    fun changeState(v: View?) {
        val motionLayout = container as? MotionLayout ?: return
//        if (motionLayout.progress > 0.5f) {
        motionLayout.transitionToEnd()
//        } else {
//            motionLayout.transitionToEnd()
//        }
    }
}