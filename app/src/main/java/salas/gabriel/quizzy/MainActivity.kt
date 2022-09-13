package salas.gabriel.quizzy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
//import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.SnackbarContentLayout
import salas.gabriel.quizzy.databinding.ActivityMainBinding

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
   //private lateinit var trueButton:Button
    //private lateinit var falseButton:Button

    private lateinit var binding:ActivityMainBinding
    private val quizViewModel: QuizViewModel by viewModels()


    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG, "Got a QuizViewModel: $quizViewModel")

        //trueButton = findViewById(R.id.trueButton)     Replaced by View Bindings
        //falseButton = findViewById(R.id.falseButton)

        binding.trueButton.setOnClickListener { view:View->
            /*val mySnack = Snackbar.make(view, R.string.correct_toast,Snackbar.LENGTH_LONG)

            mySnack.show()*/
            checkAnswer(true, view)
            /*Toast.makeText(
                this,
                R.string.correct_toast,
                Toast.LENGTH_SHORT
            ).show()*/
        }

        binding.questionText.setOnClickListener{ view:View->
            //currentIndex = (currentIndex + 1) % questionBank.size
            quizViewModel.moveToNext()
            updateQuestion()
        }

        binding.falseButton.setOnClickListener { view:View->
            val mySnack = Snackbar.make(view,R.string.incorrect_toast,Snackbar.LENGTH_SHORT)
            mySnack.show()
            checkAnswer(false, view)
        }

        binding.nextButton.setOnClickListener{
            //currentIndex = (currentIndex + 1) % questionBank.size
            quizViewModel.moveToNext()
            updateQuestion()
        }

        binding.previousButton.setOnClickListener{
            quizViewModel.moveToPrevious()
            updateQuestion()
        }

        updateQuestion()
    }

    override fun onStart(){
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    private fun updateQuestion(){
        //val questionTextResId = questionBank[currentIndex].questionText
        val questionTextResId = quizViewModel.currentQuestionText
        binding.questionText.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean, view: View){
        //val correctAnswer = questionBank[currentIndex].answer
        val correctAnswer = quizViewModel.currentQuestionAnswer
        val messageResId = if(userAnswer == correctAnswer){
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }

        val colorBackground = if(userAnswer == correctAnswer){
            R.color.green
        } else{
            R.color.red
        }
        //Toast.makeText(this, messageResId,Toast.LENGTH_SHORT).show()
        val snackAnswer = Snackbar.make(view, messageResId, Snackbar.LENGTH_SHORT)
        snackAnswer.setBackgroundTint(getColor(colorBackground))
        snackAnswer.show()
    }
}