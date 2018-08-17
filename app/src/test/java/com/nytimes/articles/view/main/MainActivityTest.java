package com.nytimes.articles.view.main;

import com.nytimes.articles.BuildConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class MainActivityTest {

    private MainActivity activity;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(MainActivity.class).create()
                .resume().get();
    }

    @Test
    public void testToolbarTitle() {
        assertEquals("NY Times Most Popular", activity.getSupportActionBar().getTitle().toString());
    }

    @Test
    public void testShowProgress() {
        activity.showProgressBar();
        assertEquals(true, activity.progressBarDialog.isShowing());
    }


    @Test
    public void testHideProgress() {
        activity.hideProgressBar();
        assertEquals(false, activity.progressBarDialog.isShowing());
    }

    @Test
    public void testCloseActivity() {
        activity.onBackPressed();
        assertEquals(true, activity.isFinishing());
    }
}