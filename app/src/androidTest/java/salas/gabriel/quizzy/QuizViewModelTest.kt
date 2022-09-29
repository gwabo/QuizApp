package salas.gabriel.quizzy
import androidx.lifecycle.SavedStateHandle
import org.junit.Test
//import org.junit.runner.RunWith

import org.junit.Assert.*
//import org.junit.jupiter.api.Assertions.*

internal class QuizViewModelTest{
    @Test
    fun provideExpectedQuestionText(){
        val savedStateHandle = SavedStateHandle()
        val quizViewModel = QuizViewModel(savedStateHandle)
        assertEquals(R.string.question_almond,quizViewModel.currentQuestionText)
    }
    @Test
    fun cyclesQuestionBank(){
        val savedStateHandle = SavedStateHandle(mapOf(CURRENT_INDEX_KEY to 4))
        val quizViewModel = QuizViewModel(savedStateHandle)
        assertEquals(R.string.question_programming,quizViewModel.currentQuestionText)
        quizViewModel.moveToNext()
        assertEquals(R.string.question_almond,quizViewModel.currentQuestionText)
    }
}