package com.umk.register;

import android.app.Activity;
import android.content.Context;
import android.widget.Button;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.umk.register.app.LoginActivity;
import com.umk.register.grades.GradesActivity;
import com.umk.register.menu.GradesListActivity;
import com.umk.register.menu.MenuActivity;
import com.umk.register.menu.ProfileActivity;
import com.umk.register.menu.settings.AboutActivity;
import com.umk.register.menu.settings.SelectStudentActivity;
import com.umk.register.menu.settings.SettingsActivity;
import com.umk.register.notes.NotesActivity;
import com.umk.register.schedules.MondayFragment;
import com.umk.register.schedules.ScheduleActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class UITest {

    @Rule
    public MainActivityTestRule<GradesActivity> gradesActivityMainActivityTestRule = new MainActivityTestRule<>(GradesActivity.class);

    @Rule
    public MainActivityTestRule<GradesListActivity> gradesListActivityMainActivityTestRule = new MainActivityTestRule<>(GradesListActivity.class);

    @Rule
    public MainActivityTestRule<MenuActivity> menuActivityMainActivityTestRule = new MainActivityTestRule<>(MenuActivity.class);

    @Rule
    public MainActivityTestRule<NotesActivity> notesActivityMainActivityTestRule = new MainActivityTestRule<>(NotesActivity.class);

    @Rule
    public MainActivityTestRule<ProfileActivity> profileActivityMainActivityTestRule = new MainActivityTestRule<>(ProfileActivity.class);

    @Rule
    public MainActivityTestRule<ScheduleActivity> scheduleActivityMainActivityTestRule = new MainActivityTestRule<>(ScheduleActivity.class);

    @Rule
    public MainActivityTestRule<AboutActivity> aboutActivityMainActivityTestRule = new MainActivityTestRule<>(AboutActivity.class);

    @Rule
    public MainActivityTestRule<SelectStudentActivity> selectStudentActivityMainActivityTestRule = new MainActivityTestRule<>(SelectStudentActivity.class);

    @Rule
    public MainActivityTestRule<SettingsActivity> settingsActivityMainActivityTestRule = new MainActivityTestRule<>(SettingsActivity.class);

    @Test
    public void useAppContext() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.umk.register", appContext.getPackageName());
    }

    @Test
    public void testGradesUI(){
        Activity activity = gradesActivityMainActivityTestRule.getActivity();
        assertNotNull(activity.findViewById(R.id.avg));
        assertNotNull(activity.findViewById(R.id.teacher));
        assertNotNull(activity.findViewById(R.id.listView));
    }

    @Test
    public void testGradesListUI(){
        Activity activity = gradesListActivityMainActivityTestRule.getActivity();
        assertNotNull(activity.findViewById(R.id.listView));
    }

    @Test
    public void testMenuUI(){
        Activity activity = menuActivityMainActivityTestRule.getActivity();
        assertNotNull(activity.findViewById(R.id.mainGrid));
    }

    @Test
    public void testNotesUI(){
        Activity activity = notesActivityMainActivityTestRule.getActivity();
        assertNotNull(activity.findViewById(R.id.listView));
    }

    @Test
    public void testProfileUI(){
        Activity activity = profileActivityMainActivityTestRule.getActivity();
        assertNotNull(activity.findViewById(R.id.container));
        assertNotNull(activity.findViewById(R.id.imageView));
        assertNotNull(activity.findViewById(R.id.nameTextView));
        assertNotNull(activity.findViewById(R.id.emailTextView));
        assertNotNull(activity.findViewById(R.id.phoneTextView));
    }

    @Test
    public void testScheduleUI(){
        Activity activity = scheduleActivityMainActivityTestRule.getActivity();
        assertNotNull(activity.findViewById(R.id.toolbar));
        assertNotNull(activity.findViewById(R.id.tabs));
        assertNotNull(activity.findViewById(R.id.view_pager));
    }

    @Test
    public void testAboutUI(){
        Activity activity = aboutActivityMainActivityTestRule.getActivity();
        assertNotNull(activity.findViewById(R.id.info));
        assertNotNull(activity.findViewById(R.id.info2));
        assertNotNull(activity.findViewById(R.id.info3));
        assertNotNull(activity.findViewById(R.id.link));
    }

    @Test
    public void testSelectStudentUI(){
        Activity activity = selectStudentActivityMainActivityTestRule.getActivity();
        assertNotNull(activity.findViewById(R.id.listView));
    }

    @Test
    public void testSettingsUI(){
        Activity activity = settingsActivityMainActivityTestRule.getActivity();
        assertNotNull(activity.findViewById(R.id.settings));
    }

}
