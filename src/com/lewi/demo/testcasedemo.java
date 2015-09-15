package com.lewi.demo;

import android.os.RemoteException;

import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;

public class testcasedemo extends UiAutomatorTestCase {

	public void testDemo() throws UiObjectNotFoundException, InterruptedException, RemoteException {   
		if(!getUiDevice().isScreenOn()){
			getUiDevice().wakeUp();
			getUiDevice().swipe(250, 850, 250, 100, 2);
		}
		
		getUiDevice().waitForWindowUpdate(null, 2000);
		getUiDevice().pressHome();
		
		UiObject settingButton = new UiObject(new UiSelector().description("设置"));
		settingButton.clickAndWaitForNewWindow();
		
		UiObject titleTextView = new UiObject(new UiSelector().textContains("设置"));
		assertTrue("could not find clock",titleTextView.exists());
	/*	
		getUiDevice().pressHome();
		UiObject clockValidation = new UiObject(new UiSelector().packageName("com.miui.home"));	
		assertTrue("could not find clock",clockValidation.exists());
	*/
	}
}
