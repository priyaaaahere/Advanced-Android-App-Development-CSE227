package unit2.transition

import android.os.Bundle
import android.transition.Slide
import android.transition.TransitionManager
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cse227etp.R

class TransitionMain : AppCompatActivity() {
    private var isVisible=true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_transition_main)
        val rootLayout=findViewById<LinearLayout>(R.id.rootLayout)
        val textView=findViewById<TextView>(R.id.transitionText)
        val button=findViewById<Button>(R.id.startTransition)
        button.setOnClickListener {
            //Choose Transition type
            val transition= Slide(Gravity.END)
            transition.duration=600
            TransitionManager.beginDelayedTransition(rootLayout,transition)
            textView.visibility=if(isVisible) TextView.GONE else TextView.VISIBLE
            isVisible=!isVisible
        }
    }
}