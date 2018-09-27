package edu.gatech.seclass.sdpscramble;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class RecordDataTest {

    @Rule public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void recordDataTest() {
        ViewInteraction appCompatEditText = onView(allOf(withId(R.id.etUsername), isDisplayed()));
        appCompatEditText.perform(replaceText("x"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(allOf(withId(R.id.etPassword), isDisplayed()));
        appCompatEditText2.perform(replaceText("a"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(allOf(withId(R.id.bLogin), withText("Login or Signup"), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatEditText3 = onView(allOf(withId(R.id.etFirstName), isDisplayed()));
        appCompatEditText3.perform(replaceText("a"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(allOf(withId(R.id.etLastName), isDisplayed()));
        appCompatEditText4.perform(replaceText("b"), closeSoftKeyboard());

        ViewInteraction appCompatEditText5 = onView(allOf(withId(R.id.etDUsername), isDisplayed()));
        appCompatEditText5.perform(replaceText("testusername"), closeSoftKeyboard());

        ViewInteraction appCompatEditText6 = onView(allOf(withId(R.id.etEmail), isDisplayed()));
        appCompatEditText6.perform(replaceText("d"), closeSoftKeyboard());

        ViewInteraction appCompatButton2 = onView(allOf(withId(R.id.bSave), withText("Save"), isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction textView = onView(allOf(withId(R.id.tsolved), withText("# of Scrambled Solved: 0"), childAtPosition(childAtPosition(IsInstanceOf
                .<View>instanceOf(android.widget.ScrollView.class), 0), 3), isDisplayed()));
        textView.check(matches(withText("# of Scrambled Solved: 0")));

        ViewInteraction textView2 = onView(allOf(withId(R.id.tcreated), withText("# of Scrambled Created: 0"), childAtPosition(childAtPosition(IsInstanceOf
                .<View>instanceOf(android.widget.ScrollView.class), 0), 4), isDisplayed()));
        textView2.check(matches(withText("# of Scrambled Created: 0")));

        ViewInteraction button = onView(allOf(withId(R.id.bCreateS), childAtPosition(childAtPosition(IsInstanceOf.<View>instanceOf(android.widget
                .LinearLayout.class), 6), 0), isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction button2 = onView(allOf(withId(R.id.bChooseS), childAtPosition(childAtPosition(IsInstanceOf.<View>instanceOf(android.widget
                .LinearLayout.class), 6), 1), isDisplayed()));
        button2.check(matches(isDisplayed()));

        ViewInteraction button3 = onView(allOf(withId(R.id.bViewPStat), childAtPosition(childAtPosition(IsInstanceOf.<View>instanceOf(android.widget
                .LinearLayout.class), 7), 0), isDisplayed()));
        button3.check(matches(isDisplayed()));

        ViewInteraction button4 = onView(allOf(withId(R.id.bViewSStat), childAtPosition(childAtPosition(IsInstanceOf.<View>instanceOf(android.widget
                .LinearLayout.class), 7), 1), isDisplayed()));
        button4.check(matches(isDisplayed()));

        ViewInteraction button5 = onView(allOf(withId(R.id.bLogout), childAtPosition(childAtPosition(IsInstanceOf.<View>instanceOf(android.widget.ScrollView
                .class), 0), 8), isDisplayed()));
        button5.check(matches(isDisplayed()));

        ViewInteraction button6 = onView(allOf(withId(R.id.bLogout), childAtPosition(childAtPosition(IsInstanceOf.<View>instanceOf(android.widget.ScrollView
                .class), 0), 8), isDisplayed()));
        button6.check(matches(isDisplayed()));

        ViewInteraction appCompatButton3 = onView(allOf(withId(R.id.bCreateS), withText("Create Scramble")));
        appCompatButton3.perform(scrollTo(), click());

        ViewInteraction appCompatEditText7 = onView(allOf(withId(R.id.etPhrase), isDisplayed()));
        appCompatEditText7.perform(replaceText("world"), closeSoftKeyboard());

        ViewInteraction appCompatEditText8 = onView(allOf(withId(R.id.etClue), isDisplayed()));
        appCompatEditText8.perform(replaceText("es"), closeSoftKeyboard());

        ViewInteraction appCompatButton4 = onView(allOf(withId(R.id.bViewS), withText("View Scramble"), isDisplayed()));
        appCompatButton4.perform(click());

        ViewInteraction appCompatButton5 = onView(allOf(withId(R.id.bRescramble), withText("Rescramble"), isDisplayed()));
        appCompatButton5.perform(click());

        ViewInteraction appCompatButton6 = onView(allOf(withId(R.id.bRescramble), withText("Rescramble"), isDisplayed()));
        appCompatButton6.perform(click());

        ViewInteraction appCompatButton7 = onView(allOf(withId(R.id.bAccept), withText("Accept"), isDisplayed()));
        appCompatButton7.perform(click());

        ViewInteraction textView3 = onView(allOf(withId(R.id.tcreated), withText("# of Scrambled Created: 1"), childAtPosition(childAtPosition(IsInstanceOf
                .<View>instanceOf(android.widget.ScrollView.class), 0), 4), isDisplayed()));
        textView3.check(matches(withText("# of Scrambled Created: 1")));

        ViewInteraction textView4 = onView(allOf(withId(R.id.tsolved), withText("# of Scrambled Solved: 0"), childAtPosition(childAtPosition(IsInstanceOf
                .<View>instanceOf(android.widget.ScrollView.class), 0), 3), isDisplayed()));
        textView4.check(matches(withText("# of Scrambled Solved: 0")));

        ViewInteraction appCompatButton8 = onView(allOf(withId(R.id.bViewPStat), withText("View Player Stat")));
        appCompatButton8.perform(scrollTo(), click());

        ViewInteraction appCompatButton9 = onView(allOf(withId(R.id.bBack), withText("Back AccountView"), isDisplayed()));
        appCompatButton9.perform(click());

        ViewInteraction appCompatButton10 = onView(allOf(withId(R.id.bViewSStat), withText("View Scramble Stat")));
        appCompatButton10.perform(scrollTo(), click());

        ViewInteraction appCompatButton11 = onView(allOf(withId(R.id.bBack), withText("Back AccountView"), isDisplayed()));
        appCompatButton11.perform(click());

        ViewInteraction appCompatButton12 = onView(allOf(withId(R.id.bChooseS), withText("Choose Scramble")));
        appCompatButton12.perform(scrollTo(), click());

        ViewInteraction appCompatTextView = onView(allOf(withId(android.R.id.text1), withText(containsString("created by: testusername")), isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction appCompatEditText9 = onView(allOf(withId(R.id.etAnswer), isDisplayed()));
        appCompatEditText9.perform(replaceText("world"), closeSoftKeyboard());

        ViewInteraction appCompatButton13 = onView(allOf(withId(R.id.bSubmit), withText("Submit"), isDisplayed()));
        appCompatButton13.perform(click());

        ViewInteraction textView5 = onView(allOf(withId(R.id.tsolved), withText("# of Scrambled Solved: 1"), childAtPosition(childAtPosition(IsInstanceOf
                .<View>instanceOf(android.widget.ScrollView.class), 0), 3), isDisplayed()));
        textView5.check(matches(withText("# of Scrambled Solved: 1")));

        ViewInteraction textView6 = onView(allOf(withId(R.id.tcreated), withText("# of Scrambled Created: 1"), childAtPosition(childAtPosition(IsInstanceOf
                .<View>instanceOf(android.widget.ScrollView.class), 0), 4), isDisplayed()));
        textView6.check(matches(withText("# of Scrambled Created: 1")));

    }

    private static Matcher<View> childAtPosition(final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent) && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
