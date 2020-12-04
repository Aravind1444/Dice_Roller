package me.aravind.diceroller

import android.R.attr.name
import android.R.id
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.android.synthetic.main.activity_main.*


private var mFirebaseAnalytics: FirebaseAnalytics? = null
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollButton: Button = findViewById(R.id.button)
        rollButton.setOnClickListener {
            rollDice()
        }

        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, button.toString())
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, button.toString())
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image")
        mFirebaseAnalytics!!.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)
    }

    //this is the function to roll the dice
    private fun rollDice() {
        val dice = Dice(6)
        val diceRoll = dice.roll()

        // Find the ImageView in the layout
        val diceImage: ImageView = findViewById(R.id.imageView)

        // Determine which drawable resource ID to use based on the dice roll
        val drawableResource = when (diceRoll) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        // Update the ImageView with the correct drawable resource ID
        diceImage.setImageResource(drawableResource)

        val luckyNumber = 6
        if (diceRoll == luckyNumber){
            val toast = Toast.makeText(this, "You got Lucky!", Toast.LENGTH_SHORT).show()
        }
        else{
            val toast = Toast.makeText(this, "Dice Rolled!", Toast.LENGTH_SHORT).show()
        }

        //these lines update the textView with the result

        // Update the content description
        diceImage.contentDescription = diceRoll.toString()
    }
}

//calls this class or roll function to return a random value
class Dice(private val numSides: Int) {
    fun roll(): Int {
        return (1..numSides).random()
    }
}