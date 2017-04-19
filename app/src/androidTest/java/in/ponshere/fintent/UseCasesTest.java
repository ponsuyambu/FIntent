package in.ponshere.fintent;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.contrib.NavigationViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import junit.framework.Assert;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import in.ponshere.fintent.sample.R;
import in.ponshere.fintent.sample.activities.NavigationDrawerActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
/**
 * Espresso test case all use cases except animation use case.
 * @author Ponsuyambu
 * @since 19/4/17.
 */
@RunWith(AndroidJUnit4.class)
public class UseCasesTest {
    @Rule
    public ActivityTestRule<NavigationDrawerActivity> mTasksActivityTestRule =
            new ActivityTestRule<NavigationDrawerActivity>(NavigationDrawerActivity.class) {

                @Override
                protected void beforeActivityLaunched() {
                    super.beforeActivityLaunched();
                    // Doing this in @Before generates a race condition.
                }};

    @Test
    public void testBasicUseCase(){
        onView(withId(in.ponshere.fintent.sample.R.id.drawer_layout)).perform(DrawerActions.open());
        // Start the screen of your activity.
        onView(withId(in.ponshere.fintent.sample.R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(in.ponshere.fintent.sample.R.id.navBasic));
        onView(withId(in.ponshere.fintent.sample.R.id.btnNext)).perform(click());
        onView(withText("Fragment B")).check(matches(isDisplayed()));

    }

    @Test
    public void testClearHistoryUseCase(){
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        // Start the screen of your activity.
        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.navClearHistory));
        onView(withId(R.id.btnNext)).perform(click());
        onView(withText("Fragment B")).check(matches(isDisplayed()));
        onView(withId(R.id.btnNext)).perform(click());
        onView(withText("Fragment C")).check(matches(isDisplayed()));
        onView(withId(R.id.btnNext)).perform(click());
        onView(withText("Fragment D")).check(matches(isDisplayed()));
        //check the backs tack entry count
        Assert.assertTrue(mTasksActivityTestRule.getActivity().getSupportFragmentManager().getBackStackEntryCount() == 1);


    }

    @Test
    public void testNavigateTo(){
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        // Start the screen of your activity.
        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.navNavigateTo));


        onView(withId(R.id.btnNext)).perform(click());
        onView(withText("Fruits List(via Common List)")).check(matches(isDisplayed()));
        onView(withId(R.id.btnNext)).perform(click());
        onView(withText("Veg List(via Common List)")).check(matches(isDisplayed()));
        onView(withId(R.id.btnNext)).perform(click());
        onView(withText("Details")).check(matches(isDisplayed()));
        onView(withId(R.id.btnFrutisList)).perform(click());
        onView(withText("Fruits List(via Common List)")).check(matches(isDisplayed()));


        onView(withId(R.id.btnNext)).perform(click());
        onView(withText("Veg List(via Common List)")).check(matches(isDisplayed()));
        onView(withId(R.id.btnNext)).perform(click());
        onView(withText("Details")).check(matches(isDisplayed()));
        onView(withId(R.id.btnVegList)).perform(click());
        onView(withText("Veg List(via Common List)")).check(matches(isDisplayed()));

        onView(withId(R.id.btnNext)).perform(click());
        onView(withText("Details")).check(matches(isDisplayed()));
        onView(withId(R.id.btnFinish)).perform(click());
        onView(withText("Veg List(via Common List)")).check(matches(isDisplayed()));
    }

    @Test
    public void testReuseInstanceUseCase(){
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());

        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.navReuseFragInstance));
        onView(withId(R.id.edt)).perform(clearText(),typeText("test"));
        onView(withId(R.id.btnNext)).perform(click());
        onView(withText("Fragment B")).check(matches(isDisplayed()));

        onView(withId(R.id.btnNext)).perform(click());
        onView(withText("Fragment C")).check(matches(isDisplayed()));

        onView(withId(R.id.btnNext)).perform(click());
        onView(withId(R.id.edt)).check(matches(withText("test")));

        Espresso.pressBack();
        onView(withText("Fragment C")).check(matches(isDisplayed()));

        onView(withId(R.id.btnNewInstance)).perform(click());
        onView(withId(R.id.edt)).check(matches(withText("")));

    }

    @Test
    public void testStartFragmentForResult(){
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.navStartForResult));

        onView(withId(R.id.btnStartForResult)).perform(click());
        onView(withText("Fragment B")).check(matches(isDisplayed()));
        onView(withId(R.id.btnOK)).perform(click());
        onView(withText("Fragment A")).check(matches(isDisplayed()));
        onView(withId(R.id.edtResult)).check(matches(withText("OK")));

        onView(withId(R.id.btnStartForResult)).perform(click());
        onView(withText("Fragment B")).check(matches(isDisplayed()));
        onView(withId(R.id.btnCancel)).perform(click());
        onView(withText("Fragment A")).check(matches(isDisplayed()));
        onView(withId(R.id.edtResult)).check(matches(withText("CANCELLED")));

    }


}
