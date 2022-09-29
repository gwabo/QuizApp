package salas.gabriel.quizzy

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.regex.Pattern.matches

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setUp() {
        scenario = launch(MainActivity::class.java)
    }

    @After
    fun tearDown() {
        scenario.close()
    }

    @Test
    fun showFirstQuestionOnLaunch(){
        onView(withId(R.id.question_text))
            .check(matches(withText(R.string.question_almond)))
    }

    @Test
    fun showSecondQuestionAfterNextPress(){
        onView(withId(R.id.next_button)).perform(click())
        onView(withId(R.id.question_text))
            .check(matches(withText(R.string.question_choco)))
    }

    @Test
    fun verifyMaintainStateOfUI(){
        onView(withId(R.id.next_button)).perform(click())
        scenario.recreate()
        onView(withId(R.id.question_text))
            .check(matches(withText(R.string.question_choco)))
    }
}