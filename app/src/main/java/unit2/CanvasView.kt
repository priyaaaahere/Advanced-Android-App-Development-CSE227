package unit2

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Color.BLACK
import android.graphics.Paint
import android.view.View

class CanvasView(context: Context): View(context){
    private val paint: Paint= Paint()

    override fun onDraw(canvas: Canvas){
        super.onDraw(canvas)

        val paint=Paint()
        paint.color= Color.BLACK
        paint.strokeWidth=8f
        //for line
        canvas.drawLine(100f,160f,650f,160f,paint)


        //for square
        canvas.drawLine(100f,200f, 700f, 200f, paint)
        canvas.drawLine(100f,200f,100f, 800f,paint)
        canvas.drawLine(100f,800f,700f,800f,paint)
        canvas.drawLine(700f,200f,700f,800f,paint)

        //for circle
        paint.color=Color.RED
        //Use the billow line if you don't want to fill the circle
        //paint.style=Paint.Style.STROKE
        //paint.strokeWidth=8f
        canvas.drawCircle(400f,1200f,200f,paint)

        //for triangle
        paint.color=Color.BLUE
        paint.strokeWidth=8f
        canvas.drawLine(400f,1500f,200f,1800f,paint)
        canvas.drawLine(200f,1800f,600f,1800f,paint)
        canvas.drawLine(600f,1800f,400f,1500f,paint)


        //Another way to draw a triangle
//        val path = android.graphics.Path()
//        path.moveTo(400f, 900f)     // Top point
//        path.lineTo(200f, 1200f)    // Bottom-left point
//        path.lineTo(600f, 1200f)    // Bottom-right point
//        path.close()                // Connect back to top
//
//        canvas.drawPath(path, paint)


        //Semicircle
        paint.color=Color.MAGENTA
        paint.style=Paint.Style.STROKE
        paint.strokeWidth=8f
        canvas.drawArc(100f,1900f,700f,2500f,180f,180f,true,paint)


    }
}