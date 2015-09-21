package com.lewi.demo;

import java.io.File;

import android.os.RemoteException;

import com.android.uiautomator.core.UiCollection;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.core.UiWatcher;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class testcasedemo extends UiAutomatorTestCase {
	
	private UiDevice mDevice;	
	
	/*
	 * Test Method: UiSelector().packageName, 
	 */
	public void testPackageName() throws UiObjectNotFoundException, InterruptedException, RemoteException {   
		init();
		
		UiObject clockValidation = new UiObject(new UiSelector().packageName("com.miui.home"));	
		assertTrue("could not find clock",clockValidation.exists());
	
	}
	
	/*
	 * Test Component: android.widget.ListView
	 * Test Method: childSelector, UiSelector().textContains, UiSelector().description
	 */
	public void testListViewComponent() throws RemoteException, UiObjectNotFoundException{
		init();
		
		mDevice = getUiDevice();
		getUiDevice().pressHome();
		UiObject settingButton = new UiObject(new UiSelector().description("设置"));
		settingButton.clickAndWaitForNewWindow();
		
		UiObject settingItem = new UiObject(new UiSelector()
				.className("android.widget.ListView").instance(0)
				.childSelector(new UiSelector().text("勿扰模式")));
		settingItem.clickAndWaitForNewWindow();
		
		UiObject checkText = new UiObject(new UiSelector().textContains("定时"));
		assertTrue("error appears",checkText.exists());				
	}
	
	/*
	 * Test Method: UiCollection
	 * Description: Go to XuQiu app and do operations.
	 */
	public void testCollection() throws RemoteException, UiObjectNotFoundException{
		
		init();
		mDevice = getUiDevice();
		mDevice.pressBack();
		
		UiObject myTools = new UiObject(new UiSelector()
		.className("android.widget.FrameLayout").description("常用工具"));
		myTools.clickAndWaitForNewWindow();
		
		UiObject xueQiu = new UiObject(new UiSelector()
		.className("android.widget.FrameLayout").description("雪球"));
		xueQiu.clickAndWaitForNewWindow();
		
		UiObject ops;
		ops = new UiObject(new UiSelector().text("买什么"));
		ops.click();
		ops = new UiObject(new UiSelector().text("自选"));
		ops.clickAndWaitForNewWindow();
		
		UiCollection items = new UiCollection(new UiSelector()
		   .className("android.widget.FrameLayout"));
		UiObject item = items.getChildByText(new UiSelector().className("android.widget.TextView"), "交运股份");
		item.clickAndWaitForNewWindow();
		
		ops = new UiObject(new UiSelector().textContains("更新"));
		assertTrue("error appears",ops.exists());				
	}
	
	/*
	 * Test Method: UiScrollable
	 * Description: Go to setting -> about phone -> phone name
	 */
	public void testScrollable() throws RemoteException, UiObjectNotFoundException{
		init();
		mDevice = getUiDevice();
		UiObject settingButton = new UiObject(new UiSelector().description("设置"));
		settingButton.clickAndWaitForNewWindow();		
		
		UiScrollable settingsItem = new UiScrollable(new UiSelector()
		   .className("android.widget.ListView"));
		UiObject about = settingsItem.getChildByText(new UiSelector()
			.className("android.widget.LinearLayout"), "关于手机");
		about.click();
		
		UiScrollable aboutItem = new UiScrollable(new UiSelector()
		   .className("android.widget.ListView"));	
		UiObject phoneName = aboutItem.getChildByText(new UiSelector()
			.className("android.widget.LinearLayout"), "手机名称");
		phoneName.clickAndWaitForNewWindow();
		
		UiObject mEdit = new UiObject(new UiSelector().text("红米手机"));	
		assertTrue("error",mEdit.exists());
	}
	
	
	/*
	 * Test Component: android.widget.EditText
	 * Test Method: setText
	 * Note: this test failed in my device, but passed in emulator. Guess it because device needs root for more permission.
	 * Description: Try to search contacts in emulator
	 */
//	public void testTextEdit() throws RemoteException, UiObjectNotFoundException{
//		init();
//		UiObject messageButton = new UiObject(new UiSelector().description("Contacts"));
//		messageButton.clickAndWaitForNewWindow();
//		
//		UiObject searchItem = new UiObject(new UiSelector().description("Search"));
//		searchItem.clickAndWaitForNewWindow();
//		
//		UiObject searchContent;
//		searchContent = new UiObject(new UiSelector().className("android.widget.EditText").textContains("Find"));
//		assertTrue("error",searchContent.exists());
//		searchContent.setText("lewi");
//		
//		UiObject searchResult;
//		searchResult = new UiObject(new UiSelector().className("android.widget.ListView")
//				.childSelector(new UiSelector().className("android.widget.TextView").text("Lewiyue")));
//		assertTrue("error",searchResult.exists());
//		
//		searchContent = new UiObject(new UiSelector().className("android.widget.EditText").text("lewi"));
//		searchContent.setText("la");
//		searchResult = new UiObject(new UiSelector().className("android.widget.ListView")
//				.childSelector(new UiSelector().className("android.widget.TextView").text("No contacts")));
//		assertTrue("error",searchResult.exists());		
//		
//	}
	
	/*
	 * Test Mothond: pressRecentApps(), takeScreenshot
	 * Test component that tagged as NAF with True in UI Automator Viewer
	 * Description: clear all app access record, and take screenshot.
	 */
	public void testNAF() throws RemoteException, UiObjectNotFoundException{
		init();
		//getUiDevice().pressMenu();
		getUiDevice().pressRecentApps(); 
		
		UiObject testTemp = new UiObject(new UiSelector().className("android.widget.ImageView").instance(0));
		assertTrue("test",testTemp.exists());
		testTemp.click();
		
		File path = new File("/sdcard/filename.png");
		getUiDevice().takeScreenshot(path);
	}
	
	/*
	 * Test Class: UiWatcher
	 * Description: Add one watch and trigger it
	 */
	public void testWatcher() throws RemoteException, UiObjectNotFoundException{
		init();
		mDevice = getUiDevice();
		mDevice.registerWatcher("clock", new UiWatcher(){
			
			public boolean checkForCondition(){
				getUiDevice().pressBack();
				return true;
			}
		});
		mDevice.runWatchers();
		
		UiObject clockButton = new UiObject(new UiSelector().description("时钟"));
		clockButton.clickAndWaitForNewWindow();
		
		//getUiDevice().pressBack();
		
		UiObject dailButton = new UiObject(new UiSelector().description("拨号"));
		dailButton.waitForExists(2000);
		assertTrue("could not find dail button",dailButton.exists());

	}
	
	/*
	 * Test Method: AssertFalse
	 * Description: Check one object does not appear.
	 */
	public void testAssertFalse() throws RemoteException{		
		init();
		UiObject clockButton = new UiObject(new UiSelector().description("时钟"));
		try {
			clockButton.click();
		} catch (UiObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		UiObject dailButton = new UiObject(new UiSelector().description("拨号"));
		assertFalse("could not find dail button",dailButton.exists());
	}
	
	/*
	 * Go back to home page for all test cases
	 */
	public void init() throws RemoteException{
		if(!getUiDevice().isScreenOn()){
			getUiDevice().wakeUp();
			getUiDevice().swipe(250, 850, 250, 100, 2);
		}
		
		getUiDevice().waitForWindowUpdate(null, 2000);
		getUiDevice().pressHome();
	}

}
