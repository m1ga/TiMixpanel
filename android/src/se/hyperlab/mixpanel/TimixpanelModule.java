/**
 * This file was auto-generated by the Titanium Module SDK helper for Android
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2013 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 *
 */
package se.hyperlab.mixpanel;

import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.TiApplication;
import org.appcelerator.kroll.common.Log;

import android.content.Context;
import android.app.Activity;

import org.json.JSONObject;
import java.util.HashMap;

import com.mixpanel.android.mpmetrics.MixpanelAPI;

@Kroll.module(name="Timixpanel", id="se.hyperlab.mixpanel")
public class TimixpanelModule extends KrollModule
{
	private static final String TAG = "TimixpanelModule";
	private MixpanelAPI mixpanel;

	public TimixpanelModule()
	{
		super();
	}

	@Kroll.onAppCreate
	public static void onAppCreate(TiApplication app)
	{
		// put module init code that needs to run when the application is created
	}

	// Flush when app goed into background
    @Override
	public void onPause(Activity activity)
	{
		// This method is called when the root context is being suspended
		mixpanel.flush();

		super.onPause(activity);
	}

    // Flush when app is closed
	@Override
	public void onDestroy(Activity activity)
	{
		// This method is called when the root context is being destroyed
		mixpanel.flush();

		super.onDestroy(activity);
	}

	// Methods
	@Kroll.method
	public void initWithToken(@Kroll.argument String token)
	{
		Log.d(TAG, "Mixpanel initWithToken: " + token);
		mixpanel = MixpanelAPI.getInstance(TiApplication.getInstance(), token);
	}

	@Kroll.method
	public void initPushHandling(@Kroll.argument String senderId)
	{
		Log.d(TAG, "Mixpanel initPushHandling: " + senderId);

		mixpanel.getPeople().initPushHandling(senderId);
	}

	@Kroll.method
	public void identify(@Kroll.argument String id) {        
		mixpanel.identify(id);
		mixpanel.getPeople().identify(id);
	}

	 @Kroll.method
	 public void createAlias(@Kroll.argument String alias) {
	 	mixpanel.alias(alias, null);
	 }
	
	 @Kroll.method
	 public void createAliasForId(@Kroll.argument String alias, @Kroll.argument String id) {
	 	mixpanel.alias(alias, id);
	 }

	@Kroll.method
	public void registerSuperProperties(@Kroll.argument HashMap map) {
		JSONObject props = new JSONObject(map);

		mixpanel.registerSuperPropertiesOnce(props);
	}

	@Kroll.method
	public void registerSuperPropertiesOnce(@Kroll.argument HashMap map) {
		JSONObject props = new JSONObject(map);

		mixpanel.registerSuperProperties(props);
	}

	@Kroll.method
	public void unregisterSuperProperty(@Kroll.argument String name) {
		mixpanel.unregisterSuperProperty(name);
	}

	@Kroll.method
	public void track(@Kroll.argument String name, @Kroll.argument(optional=true) HashMap map) {
		JSONObject props;

		if(map != null) {
			props = new JSONObject(map);
		} else {
			props = new JSONObject();
		}

		mixpanel.track(name, props);
	}

	@Kroll.method
	public void profileSet(@Kroll.argument HashMap map) {
		JSONObject props = new JSONObject(map);

		mixpanel.getPeople().set(props);
	}

	@Kroll.method
	public void profileSetOnce(@Kroll.argument HashMap map) {
		JSONObject props = new JSONObject(map);

		mixpanel.getPeople().setOnce(props);
	}

	@Kroll.method
	public void profileAppend(String name, Object value) {
		mixpanel.getPeople().append(name, value);
	}

	@Kroll.method
	public void profileIncrement(HashMap map) {
		mixpanel.getPeople().increment(map);
	}

	@Kroll.method
	public void profileTrackCharge(@Kroll.argument double amount, @Kroll.argument(optional=true) HashMap map) {
        JSONObject props;
        
        if(map != null) {
            props = new JSONObject(map);
        } else {
            props = new JSONObject();
        }
        
        mixpanel.getPeople().trackCharge(amount, props);
    }

	@Kroll.method
	public void profileClearCharges() {
        mixpanel.getPeople().clearCharges();
    }

	@Kroll.method
	public void profileDeleteUser() {
        mixpanel.getPeople().deleteUser();
    }

	@Kroll.method
	public void addPushDeviceToken(String token) {
		Log.d(TAG, "This function is not needed on Android");
	}
    
    @Kroll.method
    public void showNotificationIfAvailable() {
        mixpanel.getPeople().showNotificationIfAvailable(TiApplication.getAppCurrentActivity());
    }
    
    @Kroll.method
    public void showNotificationById(@Kroll.argument int id) {
        mixpanel.getPeople().showNotificationById(id, TiApplication.getAppCurrentActivity());
    }

    @Kroll.method
	public void flush() {
		mixpanel.flush();
	}

	@Kroll.method
	public void reset() {
		mixpanel.reset();
	}

	// Properties
	@Kroll.getProperty
	public String distinctId()
	{
		return mixpanel.getDistinctId();
	}

	// @Kroll.getProperty
	// public int flushInterval()
	// {
	// 	return MPConfig.getInstance(iApplication.getInstance()).getFlushInterval();
	// }

}
