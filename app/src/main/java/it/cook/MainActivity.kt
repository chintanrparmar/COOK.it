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

class MainActivity : AppCompatActivity() {

    private lateinit var container: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        val button = findViewById<Button>(R.id.button)
        container = findViewById(R.id.motionLayout1)
        val drawerLayout = findViewById<DrawerLayout>(R.id.motionLayout)


        button.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
            changeState(container)
        }
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