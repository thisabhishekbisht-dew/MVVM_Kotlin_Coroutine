package com.abhi.mvvm;

import static org.junit.Assert.assertEquals;

import android.content.Intent;

import com.abhi.mvvm.view.MainActivity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.android.controller.ActivityController;

@RunWith(RobolectricTestRunner.class)
 class MainActivityTest {

    @Test
    public void clickingLogin_shouldStartLoginActivity() {
        try (ActivityController<MainActivity> controller = Robolectric.buildActivity(MainActivity.class)) {
            controller.setup(); // Moves Activity to RESUMED state
            MainActivity activity = controller.get();
/*
            activity.findViewById(R.id.recyclerview).performClick();
            Intent expectedIntent = new Intent(activity, LoginActivity.class);
            Intent actual = shadowOf(RuntimeEnvironment.application).getNextStartedActivity();
            assertEquals(expectedIntent.getComponent(), actual.getComponent());*/
        }
    }
}