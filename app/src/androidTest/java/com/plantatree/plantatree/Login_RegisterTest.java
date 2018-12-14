package com.plantatree.plantatree;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;
import com.stream53.plantatree.plantatree.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertNotNull;

/*Tests the Login_Register Button on the register activity
 * to see if the button is indeed, working properly*/

public class Login_RegisterTest {

    @Rule
    public ActivityTestRule<Login_Register> sActivityTestRule = new ActivityTestRule<Login_Register>(Login_Register.class);

    private Login_Register sActivity = null;

    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(Login_Register.class.getName(),null,false);

    @Before
    public void setUp() throws Exception {

        sActivity = sActivityTestRule.getActivity();
    }

    @Test
    public void testButtonOnClick(){

        assertNotNull(sActivity.findViewById(R.id.button_register));
        onView(withId(R.id.button_register)).perform(click());
        Activity quizActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 3000);
        assertNotNull(quizActivity);
    }

    @After
    public void tearDown() throws Exception {

        sActivity = null;
    }
}