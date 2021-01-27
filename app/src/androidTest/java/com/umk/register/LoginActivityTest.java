package com.umk.register;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.widget.Button;

import androidx.test.filters.RequiresDevice;
import androidx.test.filters.SdkSuppress;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.umk.register.app.LoginActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    @Rule
    public MainActivityTestRule<LoginActivity> loginActivityMainActivityTestRule = new MainActivityTestRule<>(LoginActivity.class);

    @Test
    public void useAppContext() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.umk.register", appContext.getPackageName());
    }

    @Test
    public void testUI(){
        Activity activity = loginActivityMainActivityTestRule.getActivity();
        assertNotNull(activity.findViewById(R.id.emailET));
        assertNotNull(activity.findViewById(R.id.passwordET));
        assertNotNull(activity.findViewById(R.id.forgotpwd));
        Button loginButton = activity.findViewById(R.id.loginButton);
        assertNotNull(loginButton);
    }

    @Test
    @RequiresDevice
    public void testRequiresDevice() {
        Log.d("Test Filters", "This test requires a device");
        Activity activity = loginActivityMainActivityTestRule.getActivity();
        assertNotNull("MainActivity is not available", activity);
    }

    @Test
    @SdkSuppress(minSdkVersion = 23)
    public void testMinSdkVersion() {
        Log.d("Test Filters", "Checking for min sdk >= 23");
        Activity activity = loginActivityMainActivityTestRule.getActivity();
        assertNotNull("MainActivity is not available", activity);
    }

    @Test
    @SdkSuppress(minSdkVersion = Build.VERSION_CODES.M)
    public void testMinBuild() {
        Log.d("Test Filters", "Checking for min build > Marshmallow");
        Activity activity = loginActivityMainActivityTestRule.getActivity();
        assertNotNull("MainActivity is not available", activity);
    }

}
