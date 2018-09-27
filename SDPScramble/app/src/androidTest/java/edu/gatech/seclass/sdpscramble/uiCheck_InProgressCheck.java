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
import static android.support.test.espresso.Espresso.pressBack;
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
public class uiCheck_InProgressCheck {

    @Rule public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void uiCheck_InProgressCheck() {
        ViewInteraction textView = onView(allOf(withText("Username"), childAtPosition(childAtPosition(withId(android.R.id.content), 0), 1), isDisplayed()));
        textView.check(matches(withText("Username")));

        ViewInteraction textView2 = onView(allOf(withText("The Best Word Scramble Game!"), childAtPosition(childAtPosition(withId(android.R.id.content), 0),
                0), isDisplayed()));
        textView2.check(matches(withText("The Best Word Scramble Game!")));

        ViewInteraction textView3 = onView(allOf(withText("Password (optional)"), childAtPosition(childAtPosition(withId(android.R.id.content), 0), 3),
                isDisplayed()));
        textView3.check(matches(withText("Password (optional)")));

        ViewInteraction appCompatEditText = onView(allOf(withId(R.id.etUsername), isDisplayed()));
        appCompatEditText.perform(replaceText("x"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(allOf(withId(R.id.etPassword), isDisplayed()));
        appCompatEditText2.perform(replaceText("x"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(allOf(withId(R.id.bLogin), withText("Login or Signup"), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatEditText3 = onView(allOf(withId(R.id.etFirstName), isDisplayed()));
        appCompatEditText3.perform(replaceText("a"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(allOf(withId(R.id.etLastName), isDisplayed()));
        appCompatEditText4.perform(replaceText("b"), closeSoftKeyboard());

        ViewInteraction appCompatEditText5 = onView(allOf(withId(R.id.etDUsername), isDisplayed()));
        appCompatEditText5.perform(replaceText("testusername"), closeSoftKeyboard());

        ViewInteraction appCompatEditText6 = onView(allOf(withId(R.id.etEmail), isDisplayed()));
        appCompatEditText6.perform(replaceText("dd"), closeSoftKeyboard());

        ViewInteraction textView4 = onView(allOf(withText("Sign Up"), childAtPosition(childAtPosition(withId(android.R.id.content), 0), 0), isDisplayed()));
        textView4.check(matches(withText("Sign Up")));

        ViewInteraction textView5 = onView(allOf(withText("First Name"), childAtPosition(childAtPosition(withId(android.R.id.content), 0), 1), isDisplayed()));
        textView5.check(matches(withText("First Name")));

        ViewInteraction textView6 = onView(allOf(withText("Last Name"), childAtPosition(childAtPosition(withId(android.R.id.content), 0), 3), isDisplayed()));
        textView6.check(matches(withText("Last Name")));

        ViewInteraction textView7 = onView(allOf(withText("Desired Username"), childAtPosition(childAtPosition(withId(android.R.id.content), 0), 5),
                isDisplayed()));
        textView7.check(matches(withText("Desired Username")));

        ViewInteraction textView8 = onView(allOf(withText("Email"), childAtPosition(childAtPosition(withId(android.R.id.content), 0), 7), isDisplayed()));
        textView8.check(matches(withText("Email")));

        ViewInteraction button = onView(allOf(withId(R.id.bSave), childAtPosition(childAtPosition(withId(android.R.id.content), 0), 9), isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction button2 = onView(allOf(withId(R.id.bCancel), childAtPosition(childAtPosition(withId(android.R.id.content), 0), 10), isDisplayed()));
        button2.check(matches(isDisplayed()));

        ViewInteraction appCompatButton2 = onView(allOf(withId(R.id.bSave), withText("Save"), isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction textView11 = onView(allOf(withText("Your Current Record: "), childAtPosition(childAtPosition(IsInstanceOf.<View>instanceOf(android
                .widget.ScrollView.class), 0), 2), isDisplayed()));
        textView11.check(matches(withText("Your Current Record: ")));

        ViewInteraction textView12 = onView(allOf(withId(R.id.tsolved), withText("# of Scrambled Solved: 0"), childAtPosition(childAtPosition(IsInstanceOf
                .<View>instanceOf(android.widget.ScrollView.class), 0), 3), isDisplayed()));
        textView12.check(matches(withText("# of Scrambled Solved: 0")));

        ViewInteraction textView13 = onView(allOf(withId(R.id.tcreated), withText("# of Scrambled Created: 0"), childAtPosition(childAtPosition(IsInstanceOf
                .<View>instanceOf(android.widget.ScrollView.class), 0), 4), isDisplayed()));
        textView13.check(matches(withText("# of Scrambled Created: 0")));

        ViewInteraction textView14 = onView(allOf(withId(R.id.average), withText("Average # of Times Scrambles Solved by Others: 0"), childAtPosition
                (childAtPosition(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class), 0), 5), isDisplayed()));
        textView14.check(matches(withText("Average # of Times Scrambles Solved by Others: 0")));

        ViewInteraction button3 = onView(allOf(withId(R.id.bCreateS), childAtPosition(childAtPosition(IsInstanceOf.<View>instanceOf(android.widget
                .LinearLayout.class), 6), 0), isDisplayed()));
        button3.check(matches(isDisplayed()));

        ViewInteraction button4 = onView(allOf(withId(R.id.bChooseS), childAtPosition(childAtPosition(IsInstanceOf.<View>instanceOf(android.widget
                .LinearLayout.class), 6), 1), isDisplayed()));
        button4.check(matches(isDisplayed()));

        ViewInteraction button5 = onView(allOf(withId(R.id.bViewPStat), childAtPosition(childAtPosition(IsInstanceOf.<View>instanceOf(android.widget
                .LinearLayout.class), 7), 0), isDisplayed()));
        button5.check(matches(isDisplayed()));

        ViewInteraction button6 = onView(allOf(withId(R.id.bViewSStat), childAtPosition(childAtPosition(IsInstanceOf.<View>instanceOf(android.widget
                .LinearLayout.class), 7), 1), isDisplayed()));
        button6.check(matches(isDisplayed()));

        ViewInteraction button7 = onView(allOf(withId(R.id.bLogout), childAtPosition(childAtPosition(IsInstanceOf.<View>instanceOf(android.widget.ScrollView
                .class), 0), 8), isDisplayed()));
        button7.check(matches(isDisplayed()));

        ViewInteraction appCompatButton3 = onView(allOf(withId(R.id.bCreateS), withText("Create Scramble")));
        appCompatButton3.perform(scrollTo(), click());

        ViewInteraction textView15 = onView(allOf(withText("Create Scramble"), childAtPosition(childAtPosition(withId(android.R.id.content), 0), 0),
                isDisplayed()));
        textView15.check(matches(withText("Create Scramble")));

        ViewInteraction textView16 = onView(allOf(withText("Phrase"), childAtPosition(childAtPosition(withId(android.R.id.content), 0), 1), isDisplayed()));
        textView16.check(matches(withText("Phrase")));

        ViewInteraction textView17 = onView(allOf(withText("clue"), childAtPosition(childAtPosition(withId(android.R.id.content), 0), 3), isDisplayed()));
        textView17.check(matches(withText("clue")));

        ViewInteraction textView18 = onView(allOf(withText("View Scramble"), childAtPosition(childAtPosition(withId(android.R.id.content), 0), 5),
                isDisplayed()));
        textView18.check(matches(withText("View Scramble")));

        ViewInteraction button8 = onView(allOf(withId(R.id.bViewS), childAtPosition(childAtPosition(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout
                .class), 6), 0), isDisplayed()));
        button8.check(matches(isDisplayed()));

        ViewInteraction button9 = onView(allOf(withId(R.id.bRescramble), childAtPosition(childAtPosition(IsInstanceOf.<View>instanceOf(android.widget
                .LinearLayout.class), 6), 1), isDisplayed()));
        button9.check(matches(isDisplayed()));

        ViewInteraction button10 = onView(allOf(withId(R.id.bBack), childAtPosition(childAtPosition(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout
                .class), 8), 0), isDisplayed()));
        button10.check(matches(isDisplayed()));

        ViewInteraction button11 = onView(allOf(withId(R.id.bAccept), childAtPosition(childAtPosition(IsInstanceOf.<View>instanceOf(android.widget
                .LinearLayout.class), 8), 1), isDisplayed()));
        button11.check(matches(isDisplayed()));

        ViewInteraction button12 = onView(allOf(withId(R.id.bChooseS), childAtPosition(childAtPosition(IsInstanceOf.<View>instanceOf(android.widget
                .LinearLayout.class), 9), 0), isDisplayed()));
        button12.check(matches(isDisplayed()));

        ViewInteraction button13 = onView(allOf(withId(R.id.bViewSStat), childAtPosition(childAtPosition(IsInstanceOf.<View>instanceOf(android.widget
                .LinearLayout.class), 9), 1), isDisplayed()));
        button13.check(matches(isDisplayed()));

        ViewInteraction button14 = onView(allOf(withId(R.id.btn_back_to_account_view), childAtPosition(childAtPosition(IsInstanceOf.<View>instanceOf(android
                .widget.LinearLayout.class), 10), 0), isDisplayed()));
        button14.check(matches(isDisplayed()));

        ViewInteraction button15 = onView(allOf(withId(R.id.bLogout), childAtPosition(childAtPosition(IsInstanceOf.<View>instanceOf(android.widget
                .LinearLayout.class), 10), 1), isDisplayed()));
        button15.check(matches(isDisplayed()));

        ViewInteraction appCompatButton4 = onView(allOf(withId(R.id.btn_back_to_account_view), withText("Goto AccountView"), isDisplayed()));
        appCompatButton4.perform(click());

        ViewInteraction appCompatButton5 = onView(allOf(withId(R.id.bChooseS), withText("Choose Scramble")));
        appCompatButton5.perform(scrollTo(), click());

        ViewInteraction textView19 = onView(allOf(withText("Choose Scramble"), childAtPosition(childAtPosition(withId(android.R.id.content), 0), 0),
                isDisplayed()));
        textView19.check(matches(withText("Choose Scramble")));

        ViewInteraction textView20 = onView(allOf(withText("Choose Scramble"), childAtPosition(childAtPosition(withId(android.R.id.content), 0), 0),
                isDisplayed()));
        textView20.check(matches(withText("Choose Scramble")));

        ViewInteraction textView21 = onView(allOf(withText("note: Select One To Solve. This is the unsolved scramble list for you."), childAtPosition
                (childAtPosition(withId(android.R.id.content), 0), 1), isDisplayed()));
        textView21.check(matches(withText("note: Select One To Solve. This is the unsolved scramble list for you.")));

        pressBack();

        ViewInteraction appCompatButton6 = onView(allOf(withId(R.id.bViewPStat), withText("View Player Stat")));
        appCompatButton6.perform(scrollTo(), click());

        ViewInteraction button16 = onView(allOf(withId(R.id.bCreateS), childAtPosition(childAtPosition(IsInstanceOf.<View>instanceOf(android.widget
                .LinearLayout.class), 1), 0), isDisplayed()));
        button16.check(matches(isDisplayed()));

        ViewInteraction button17 = onView(allOf(withId(R.id.bChooseS), childAtPosition(childAtPosition(IsInstanceOf.<View>instanceOf(android.widget
                .LinearLayout.class), 1), 1), isDisplayed()));
        button17.check(matches(isDisplayed()));

        ViewInteraction button18 = onView(allOf(withId(R.id.bBack), childAtPosition(childAtPosition(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout
                .class), 1), 2), isDisplayed()));
        button18.check(matches(isDisplayed()));


        ViewInteraction appCompatButton7 = onView(allOf(withId(R.id.bBack), withText("Back AccountView"), isDisplayed()));
        appCompatButton7.perform(click());

        ViewInteraction appCompatButton8 = onView(allOf(withId(R.id.bViewSStat), withText("View Scramble Stat")));
        appCompatButton8.perform(scrollTo(), click());

        ViewInteraction textView23 = onView(allOf(withText("Scramble Statistics"), childAtPosition(childAtPosition(withId(android.R.id.content), 0), 0),
                isDisplayed()));
        textView23.check(matches(withText("Scramble Statistics")));

        ViewInteraction button19 = onView(allOf(withId(R.id.bCreateS), childAtPosition(childAtPosition(IsInstanceOf.<View>instanceOf(android.widget
                .LinearLayout.class), 1), 0), isDisplayed()));
        button19.check(matches(isDisplayed()));

        ViewInteraction button20 = onView(allOf(withId(R.id.bChooseS), childAtPosition(childAtPosition(IsInstanceOf.<View>instanceOf(android.widget
                .LinearLayout.class), 1), 1), isDisplayed()));
        button20.check(matches(isDisplayed()));

        ViewInteraction button21 = onView(allOf(withId(R.id.bBack), childAtPosition(childAtPosition(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout
                .class), 1), 2), isDisplayed()));
        button21.check(matches(isDisplayed()));

        ViewInteraction textView24 = onView(allOf(withId(R.id.name), withText(" ScrambleID "), childAtPosition(childAtPosition(IsInstanceOf.<View>instanceOf
                (android.widget.TableLayout.class), 0), 0), isDisplayed()));
        textView24.check(matches(withText(" ScrambleID ")));

        ViewInteraction textView25 = onView(allOf(withId(R.id.name1), withText("TotalSolved"), childAtPosition(childAtPosition(IsInstanceOf.<View>instanceOf
                (android.widget.TableLayout.class), 0), 1), isDisplayed()));
        textView25.check(matches(withText("TotalSolved")));

        ViewInteraction textView26 = onView(allOf(withId(R.id.name2), withText(" isSolved "), childAtPosition(childAtPosition(IsInstanceOf.<View>instanceOf
                (android.widget.TableLayout.class), 0), 2), isDisplayed()));
        textView26.check(matches(withText(" isSolved ")));

        ViewInteraction textView27 = onView(allOf(withId(R.id.name3), withText(" isCreator "), childAtPosition(childAtPosition(IsInstanceOf.<View>instanceOf
                (android.widget.TableLayout.class), 0), 3), isDisplayed()));
        textView27.check(matches(withText(" isCreator ")));

        ViewInteraction textView28 = onView(allOf(withId(R.id.name4), withText(" isProgress "), childAtPosition(childAtPosition(IsInstanceOf.<View>instanceOf
                (android.widget.TableLayout.class), 0), 4), isDisplayed()));
        textView28.check(matches(withText(" isProgress ")));

        ViewInteraction appCompatButton9 = onView(allOf(withId(R.id.bBack), withText("Back AccountView"), isDisplayed()));
        appCompatButton9.perform(click());

        ViewInteraction appCompatButton10 = onView(allOf(withId(R.id.bCreateS), withText("Create Scramble")));
        appCompatButton10.perform(scrollTo(), click());

        ViewInteraction appCompatEditText7 = onView(allOf(withId(R.id.etPhrase), isDisplayed()));
        appCompatEditText7.perform(replaceText("team"), closeSoftKeyboard());

        ViewInteraction appCompatEditText8 = onView(allOf(withId(R.id.etClue), isDisplayed()));
        appCompatEditText8.perform(replaceText("work"), closeSoftKeyboard());

        ViewInteraction appCompatButton11 = onView(allOf(withId(R.id.bViewS), withText("View Scramble"), isDisplayed()));
        appCompatButton11.perform(click());

        ViewInteraction appCompatButton12 = onView(allOf(withId(R.id.bRescramble), withText("Rescramble"), isDisplayed()));
        appCompatButton12.perform(click());

        ViewInteraction appCompatButton13 = onView(allOf(withId(R.id.bAccept), withText("Accept"), isDisplayed()));
        appCompatButton13.perform(click());

        ViewInteraction appCompatButton14 = onView(allOf(withId(R.id.bChooseS), withText("Choose Scramble")));
        appCompatButton14.perform(scrollTo(), click());

        ViewInteraction appCompatTextView = onView(allOf(withId(android.R.id.text1), withText(containsString("created by: testusername")), isDisplayed(),
                withText(containsString("N"))));
        appCompatTextView.perform(click());

        ViewInteraction appCompatEditText9 = onView(allOf(withId(R.id.etAnswer), isDisplayed()));
        appCompatEditText9.perform(replaceText("work"), closeSoftKeyboard());

        ViewInteraction appCompatButton15 = onView(allOf(withId(R.id.bSubmit), withText("Submit"), isDisplayed()));
        appCompatButton15.perform(click());

        ViewInteraction appCompatButton16 = onView(allOf(withId(R.id.bExit), withText("Exit"), isDisplayed()));
        appCompatButton16.perform(click());

        ViewInteraction appCompatTextView2 = onView(allOf(withId(android.R.id.text1), withText(containsString("testusername")), isDisplayed()));
        appCompatTextView2.perform(click());

        ViewInteraction editText = onView(allOf(withId(R.id.etAnswer), withText("work"), childAtPosition(childAtPosition(withId(android.R.id.content), 0), 4)
                , isDisplayed()));
        editText.check(matches(withText("work")));

        ViewInteraction appCompatButton17 = onView(allOf(withId(R.id.bSubmit), withText("Submit"), isDisplayed()));
        appCompatButton17.perform(click());

        ViewInteraction appCompatEditText10 = onView(allOf(withId(R.id.etAnswer), isDisplayed()));
        appCompatEditText10.perform(replaceText("team"), closeSoftKeyboard());

        ViewInteraction appCompatButton18 = onView(allOf(withId(R.id.bSubmit), withText("Submit"), isDisplayed()));
        appCompatButton18.perform(click());

        ViewInteraction textView29 = onView(allOf(withId(R.id.tsolved), withText("# of Scrambled Solved: 1"), childAtPosition(childAtPosition(IsInstanceOf
                .<View>instanceOf(android.widget.ScrollView.class), 0), 3), isDisplayed()));
        textView29.check(matches(withText("# of Scrambled Solved: 1")));

        ViewInteraction textView30 = onView(allOf(withId(R.id.tcreated), withText("# of Scrambled Created: 1"), childAtPosition(childAtPosition(IsInstanceOf
                .<View>instanceOf(android.widget.ScrollView.class), 0), 4), isDisplayed()));
        textView30.check(matches(withText("# of Scrambled Created: 1")));

        ViewInteraction appCompatButton19 = onView(allOf(withId(R.id.bLogout), withText("Log Out")));
        appCompatButton19.perform(scrollTo(), click());

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
