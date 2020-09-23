package com.example.a3coin

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.View
import android.widget.*
import androidx.gridlayout.widget.GridLayout

class MainActivity : AppCompatActivity() {

    //0-> Red , 1-> Blue  and 2-> Empty
    var activePlayer=0

    var gamestate= arrayOf(2,2,2,2,2,2,2,2,2)

    var winPosition= arrayOf(intArrayOf(0,1,2), intArrayOf(3,4,5), intArrayOf(6,7,8), intArrayOf(0,3,6),
        intArrayOf(1,4,7), intArrayOf(2,5,8), intArrayOf(0,4,8), intArrayOf(2,4,6))

    var gameActive=true

    fun drop(androidView: View) {
        var position = (androidView as ImageView)

        var playagain=findViewById<Button>(R.id.playagain)

        var result=findViewById<TextView>(R.id.result)

        Log.i("Position",position.getTag().toString())

        var tapCounter:Int=position.getTag().toString().toInt()

        if(gamestate[tapCounter]==2 && gameActive) {
            gamestate[tapCounter] = activePlayer

          //  gameActive=false

            if (activePlayer == 0) {
                position.setImageResource(R.drawable.r1)
                activePlayer = 1
            } else{
                position.setImageResource(R.drawable.b2)
                activePlayer = 0
            }
            position.animate().alpha(1f).setDuration(200)


            for (win: IntArray in winPosition) {
                if ((gamestate[win[0]] == gamestate[win[1]]) && (gamestate[win[1]] == gamestate[win[2]]) && (gamestate[win[0]] != 2)) {
                    //Someone has one
                    gameActive=false

                    var winner = ""
                    if (activePlayer == 1) {
                        winner = "Red"
                        result.setTextColor(Color.RED)
                    } else{
                        winner = "Blue"
                        result.setTextColor(Color.BLUE)
                    }
                    result.visibility=View.VISIBLE
                    result.setText(winner+" has won ")
                    result.animate().rotation(1080f).scaleY(2f).scaleX(2f).setDuration(300)
                    playagain.animate().alpha(1f)
                }
               // else if((gamestate[win[0]] == gamestate[win[1]]) && (gamestate[win[1]] == gamestate[win[2]]) && (gamestate[win[0]] == 2))
                //{   result.visibility=View.VISIBLE
                  //  result.setText("DRAW")
                    //result.animate().scaleY(3f).scaleX(3f).setDuration(300)
                   // playagain.animate().alpha(1f)
                //}
            }
        }
    }

   fun again(androidView: View) {
       var playagain = findViewById<Button>(R.id.playagain)
       var result = findViewById<TextView>(R.id.result)

       result.visibility = View.INVISIBLE
       playagain.animate().alpha(0f)

       var grid = findViewById<GridLayout>(R.id.grid)

       var count = grid.getChildCount();
       var i=0
       for( i in 0..count-1){
           var child = grid.getChildAt(i) as ImageView
           child.setImageResource(0)

           activePlayer=0
            var j=0
           for(j in 0..gamestate.size-1)
           {
               gamestate[j]=2
           }

           gameActive=true

       }
   }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}