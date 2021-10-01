package com.example.hellokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var diceImage : ImageView

    //private lateinit var diceImage2 : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val rollButton = findViewById<Button>(R.id.bt_roll)
        rollButton.setOnClickListener { rollDice() }


        diceImage = findViewById(R.id.iv_dice)

        //diceImage2 = findViewById(R.id.iv_dice2)

    }

    private fun rollDice(){

        //resultText.text = "Dice Rolled!"
        /*val drawableDice = when((1..6).random()){
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }*/

        diceImage.setImageResource(getRandomDiceImage())

        //diceImage2.setImageResource(getRandomDiceImage())

        Toast.makeText(this, getString(R.string.dice_rolled), Toast.LENGTH_SHORT).show()

        //diceImage.setImageResource(drawableDice)

    }

    private fun getRandomDiceImage() : Int {

        return when ((1..6).random()) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
    }

/* Used this function to get a count of dice

    private fun countUp(){

        //MyButton
        if (resultText.text == "Hello World!") {
            resultText.text = "1"
        } else {

            var resultInt = resultText.text.toString().toInt()

            if (resultInt < 6) {
                resultInt++
                resultText.text = resultInt.toString()
            }
        }
    }**/


}