package me.aravind.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollButton: Button = findViewById(R.id.button)
        rollButton.setOnClickListener {
            rollDice()
            val toast = Toast.makeText(this, "Dice Rolled!", Toast.LENGTH_SHORT).show()
        }
    }

    //this is the function to roll the dice
    private fun rollDice() {
        val dice = Dice(6)
        val diceRoll = dice.roll()

        //these lines update the textView with the result
        val resultTextView: TextView = findViewById(R.id.textView)
        resultTextView.text = diceRoll.toString()
    }
}

//calls this class or roll function to return a random value
class Dice(private val numSides: Int) {
    fun roll(): Int {
        return (1..numSides).random()
    }
}