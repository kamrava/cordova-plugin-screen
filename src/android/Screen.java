/**
 */
package org.apache.cordova.screen;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import java.lang.reflect.Method;
import android.content.Context;
import android.view.Display;
import android.os.Build;
import android.util.Log;

import java.util.Date;

public class Screen extends CordovaPlugin {
  private static final String TAG = "Screen";
  private static Context context;

  public void initialize(CordovaInterface cordova, CordovaWebView webView) {
    super.initialize(cordova, webView);

    Log.d(TAG, "Initializing Screen");
  }

  public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
    if(action.equals("height")) {
      int devicePixelRatio = args.getInt(0);
      int screenSize[] = this.getScreenRealSize();
      int height = screenSize[0] / devicePixelRatio;
      this.returnResponse(height, callbackContext);
    } else if(action.equals("usableScreenHeight")) {
      int devicePixelRatio = args.getInt(0);
      int usableScreenHeight = this.getUsableScreenHeight(devicePixelRatio);
      this.returnResponse(usableScreenHeight, callbackContext);
    } else if(action.equals("width")) {
      int devicePixelRatio = args.getInt(0);
      int screenSize[] = this.getScreenRealSize();
      int width = screenSize[1] / devicePixelRatio;
      this.returnResponse(width, callbackContext);
    } else if(action.equals("navbarHeight")) {
      int navbarHeight = this.getNavigationBarHeight();
      this.returnResponse(navbarHeight, callbackContext);
    } else if(action.equals("statusbarHeight")) {
      int statusbarHeight = this.getStatusBarHeight();
      this.returnResponse(statusbarHeight, callbackContext);
    } else if(action.equals("hasNavbar")) {
      int hasNavbar = this.hasNavigationBar() ? 1 : 0;
      this.returnResponse(hasNavbar, callbackContext);
    }
    return true;
  }

  public void returnResponse(int response, final CallbackContext callbackContext) {
    PluginResult result = new PluginResult(PluginResult.Status.OK, response);
    result.setKeepCallback(true);
    callbackContext.sendPluginResult(result);
  }

  public int getUsableScreenHeight(int devicePixelRatio) {
    int screenSize[] = this.getScreenRealSize();
    int realHeight = screenSize[0];
    int navbarHeight = this.getNavigationBarHeight();
    int statusbarHeight = this.getStatusBarHeight();
    int usableScreenHeight = (realHeight - navbarHeight - statusbarHeight) / devicePixelRatio;
    return usableScreenHeight;
  }

  public int[] getScreenRealSize() {
    Display display = cordova.getActivity().getWindowManager().getDefaultDisplay();
    int realWidth;
    int realHeight;

      if (Build.VERSION.SDK_INT >= 17){
          //new pleasant way to get real metrics
          DisplayMetrics realMetrics = new DisplayMetrics();
          display.getRealMetrics(realMetrics);
          realWidth = realMetrics.widthPixels;
          realHeight = realMetrics.heightPixels;

      } else if (Build.VERSION.SDK_INT >= 14) {
          //reflection for this weird in-between time
          try {
              Method mGetRawH = Display.class.getMethod("getRawHeight");
              Method mGetRawW = Display.class.getMethod("getRawWidth");
              realWidth = (Integer) mGetRawW.invoke(display);
              realHeight = (Integer) mGetRawH.invoke(display);
          } catch (Exception e) {
              //this may not be 100% accurate, but it's all we've got
              realWidth = display.getWidth();
              realHeight = display.getHeight();
              Log.e("Display Info", "Couldn't use reflection to get the real display metrics.");
          }

      } else {
          //This should be close, as lower API devices should not have window navigation bars
          realWidth = display.getWidth();
          realHeight = display.getHeight();
      }
      return new int[] {realHeight, realWidth};
  }

    public int getNavigationBarHeight()
    {
        Resources resources = cordova.getActivity().getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return resources.getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = cordova.getActivity().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = cordova.getActivity().getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public boolean hasNavigationBar() {
        Resources resources = cordova.getActivity().getResources();
        int id = resources.getIdentifier("config_showNavigationBar", "bool", "android");
        return id > 0 && resources.getBoolean(id);
    }

}
