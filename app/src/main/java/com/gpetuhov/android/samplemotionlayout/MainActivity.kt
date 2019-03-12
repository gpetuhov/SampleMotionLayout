package com.gpetuhov.android.samplemotionlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.motion.widget.MotionLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // This is how we trigger our animation
        startAnimation.setOnClickListener { motionContainer.transitionToEnd() }

        // Note that ImageView click and drag listeners are set inside the motion_scene.xml file

        motionContainer.setTransitionListener(object: MotionLayout.TransitionListener {
            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {
                // Show transition progress inside SeekBar
                seekbar.progress = ceil(p3 * 100).toInt()
            }

            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
                if (p1 == R.id.ending_set) {
                    // Return to original constraint set
                    motionContainer.transitionToStart()
                }
            }

            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {
                // Do nothing
            }

            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
                // Do nothing
            }
        })
    }
}
