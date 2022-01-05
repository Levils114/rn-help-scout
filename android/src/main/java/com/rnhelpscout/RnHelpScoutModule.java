package com.rnhelpscout;

import androidx.annotation.NonNull;

import android.app.Application;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.bridge.ReadableMap;

import com.helpscout.beacon.Beacon;
import com.helpscout.beacon.model.BeaconScreens;
import com.helpscout.beacon.ui.BeaconActivity;
import com.helpscout.beacon.ui.BeaconEventLifecycleHandler;
import com.helpscout.beacon.ui.BeaconOnClosedListener;
import com.helpscout.beacon.ui.BeaconOnOpenedListener;

import java.util.ArrayList;

@ReactModule(name = RnHelpScoutModule.NAME)
public class RnHelpScoutModule extends ReactContextBaseJavaModule {
    private final ReactApplicationContext reactContext;

    public static final String NAME = "RnHelpScout";

    public RnHelpScoutModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;

		BeaconEventLifecycleHandler eventLifecycleHandler = new BeaconEventLifecycleHandler(
            new BeaconOnOpenedListener() {
                @Override
                public void onOpened() {
                    reactContext
                            .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                            .emit("open", null);
                }
            },
            new BeaconOnClosedListener() {
                @Override
                public void onClosed() {
                    reactContext
                            .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                            .emit("close", null);
                }
            }
		);

		Application application = (Application) reactContext.getApplicationContext();
		application.registerActivityLifecycleCallbacks(eventLifecycleHandler);
    }

    @Override
    @NonNull
    public String getName() {
        return NAME;
    }


    // Example method
    // See https://reactnative.dev/docs/native-modules-android
    @ReactMethod
    public void multiply(int a, int b, Promise promise) {
        promise.resolve(a * b);
    }

    public static native int nativeMultiply(int a, int b);

    @ReactMethod
    public void sum(int a, int b, Promise promise) {
        promise.resolve(a + b);
    }

    @ReactMethod
    public void init(String beaconID) {
        new Beacon.Builder().withBeaconId(beaconID).build();
    }

    @ReactMethod
	public void open() {
		BeaconActivity.open(reactContext);
	}

    @ReactMethod
	public void identify(ReadableMap identity) {
		String email = identity.hasKey("email") ? identity.getString("email") : "";
		if (identity.hasKey("name")) {
			Beacon.login(email, identity.getString("name"));
		} else {
			Beacon.login(email);
		}

		/* Iterator<Map.Entry<String, Object>> i = identity.getEntryIterator();

		while (i.hasNext()) {
			Map.Entry<String, Object> entry = i.next();
			String key = entry.getKey();
			if (key == "email" || key == "name") continue;
			Beacon.addAttributeWithKey(key, (String) entry.getValue());
		} */
	}

    @ReactMethod
	public void contactForm() {
		BeaconActivity.open(this.reactContext, BeaconScreens.CONTACT_FORM_SCREEN, new ArrayList<String>());
	}
}
