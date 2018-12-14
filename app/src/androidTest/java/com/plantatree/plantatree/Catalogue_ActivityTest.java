package com.plantatree.plantatree;

import android.support.test.rule.ActivityTestRule;
import android.view.View;
import com.stream53.plantatree.plantatree.R;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class Catalogue_ActivityTest {

    @Rule
    public ActivityTestRule<Catalogue_Activity> activityTest = new ActivityTestRule<Catalogue_Activity>(Catalogue_Activity.class);

    private Catalogue_Activity mActivity = null;

    @Before
    public void setUp() throws Exception {

        mActivity = activityTest.getActivity();
    }

    @Test
    public void testActivity(){

        View view = mActivity.findViewById(R.id.text_view_welcome);

        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {

        mActivity = null;
    }
}