package salas.gabriel.quizzy

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"
const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"


class QuizViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel(){

    private val questionBank = listOf(
        Question(R.string.question_almond, answer = true),
        Question(R.string.question_choco, answer = true),
        Question(R.string.question_white_choco, answer = true),
        Question(R.string.question_art, answer = false),
        Question(R.string.question_programming, answer = true)
    )

    private var currentIndex: Int
        get() = savedStateHandle.get(CURRENT_INDEX_KEY) ?:0
        set(value) = savedStateHandle.set(CURRENT_INDEX_KEY, value)

    val currentQuestionAnswer:Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText:Int
        get() = questionBank[currentIndex].questionText

    fun moveToNext(){
        currentIndex = (currentIndex + 1) % questionBank.size
    }

    fun moveToPrevious(){
        currentIndex = if((currentIndex - 1) < 0){
            currentIndex % questionBank.size
        } else {
            (currentIndex - 1) % questionBank.size
        }
    }
}