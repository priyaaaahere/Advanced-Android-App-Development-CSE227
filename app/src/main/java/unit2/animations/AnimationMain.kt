package unit2.animations

import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cse227etp.R

class AnimationMain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_animation_main)
        val image=findViewById<ImageView>(R.id.img)
        val fadeBtn=findViewById<Button>(R.id.fade)
        val moveBtn=findViewById<Button>(R.id.move)
        val bounceBtn=findViewById<Button>(R.id.bounce)

        fadeBtn.setOnClickListener {
            val anim= AnimationUtils.loadAnimation(this,R.anim.fade_out)
            image.startAnimation(anim)
        }
        moveBtn.setOnClickListener{
            val anim=AnimationUtils.loadAnimation(this,R.anim.move)
            image.startAnimation(anim)
        }
        bounceBtn.setOnClickListener{
            val anim=AnimationUtils.loadAnimation(this,R.anim.bounce)
            image.startAnimation(anim)
        }

    }
}