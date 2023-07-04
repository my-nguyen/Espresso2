package com.nguyen.espresso2

import androidx.test.espresso.Espresso
import androidx.test.espresso.accessibility.AccessibilityChecks
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.google.android.apps.common.testing.accessibility.framework.AccessibilityCheckResultUtils.matchesViews
import org.hamcrest.core.AnyOf.anyOf
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CounterInstrumentedTest {
    @Rule
    @JvmField
    var mActivityTestRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testIncrement() {
        // interacting with a view using a ViewAction triggers the Accessibility Test Framework (ATF)
        Espresso.onView(withId(R.id.add_button)).perform(ViewActions.click())
        // no ViewAction, no ATF
        Espresso.onView(withId(R.id.countTV)).check(matches(withText("1")))
    }

    companion object {
        @BeforeClass
        @JvmStatic
        fun enableAccessibilityChecks() {
            // All the accessibility checks you encountered so far were associated with the add
            // button, which is the view on which you performed a ViewAction. You'll now configure
            // your tests to examine other views in the hierarchy, without having to perform
            // additional ViewActions on those views.
            // AccessibilityChecks.enable().setRunChecksFromRootView(true)

            // suppress all test failures but log them instead
            // AccessibilityChecks.enable().setThrowExceptionForErrors(false)

            // suppress only tests on a view with id countTV (a TextView)
            AccessibilityChecks.enable().setRunChecksFromRootView(true)
                .setSuppressingResultMatcher(matchesViews(anyOf(withId(R.id.countTV))))
        }
    }
}
