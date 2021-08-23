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

  public static int scale;

  /**
   * Constructor.
   */
  public Screen() {
  }

  public void initialize(CordovaInterface cordova, CordovaWebView webView) {
    super.initialize(cordova, webView);

    Log.d(TAG, "Initializing Screen");
  }

  public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
    if ("getScreenInfo".equals(action)) {
        Screen.scale = args.getInt(0);
        JSONObject info = new JSONObject();
        info.put("height", this.getHeight());
        info.put("width", this.getWidth());
        info.put("heightRaw", this.getHeightRaw());
        info.put("widthRaw", this.getWidthRaw());
        info.put("usableHeight", this.getUsableHeight());
        info.put("navbarHeight", this.getNavbarHeight());
        info.put("statusbarHeight", this.getStatusBarHeight());
        info.put("hasNavbar", this.hasNavbarBar());
        callbackContext.success(info);
    }
    else {
        return false;
    }
    return true;
  }

  public int getHeight() {
    int screenSize[] = this.getScreenRealSize();
    int height = screenSize[0] / Screen.scale;
    return height;
  }

  public int getHeightRaw() {
    int screenSize[] = this.getScreenRealSize();
    int height = screenSize[0];
    return height;
  }

  public int getWidth() {
    int screenSize[] = this.getScreenRealSize();
    int width = screenSize[1] / Screen.scale;
    return width;
  }

  public int getWidthRaw() {
    int screenSize[] = this.getScreenRealSize();
    int width = screenSize[1];
    return width;
  }

  public void returnResponse(int response, final CallbackContext callbackContext) {
    PluginResult result = new PluginResult(PluginResult.Status.OK, response);
    result.setKeepCallback(true);
    callbackContext.sendPluginResult(result);
  }

  public int getUsableHeight() {
    int screenSize[] = this.getScreenRealSize();
    int realHeight = screenSize[0];
    int navbarHeight = this.getNavbarHeight();
    int statusbarHeight = this.getStatusBarHeight();
    int usableScreenHeight = (realHeight - navbarHeight - statusbarHeight) / Screen.scale;
    return usableScreenHeight;
  }

  public int[] getScreenRealSize() {
    Display display = cordova.getActivity().getWindowManager().getDefaultDisplay();
    int realWidth;
    int realHeight;

      if (Build.VERSION.SDK_INT >= 17){
          DisplayMetrics realMetrics = new DisplayMetrics();
          display.getRealMetrics(realMetrics);
          realWidth = realMetrics.widthPixels;
          realHeight = realMetrics.heightPixels;

      } else if (Build.VERSION.SDK_INT >= 14) {
          try {
              Method mGetRawH = Display.class.getMethod("getRawHeight");
              Method mGetRawW = Display.class.getMethod("getRawWidth");
              realWidth = (Integer) mGetRawW.invoke(display);
              realHeight = (Integer) mGetRawH.invoke(display);
          } catch (Exception e) {
              realWidth = display.getWidth();
              realHeight = display.getHeight();
              Log.e("Display Info", "Couldn't use reflection to get the real display metrics.");
          }

      } else {
          realWidth = display.getWidth();
          realHeight = display.getHeight();
      }
      return new int[] {realHeight, realWidth};
  }

    public int getNavbarHeight()
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

    public boolean hasNavbarBar() {
        Resources resources = cordova.getActivity().getResources();
        int id = resources.getIdentifier("config_showNavigationBar", "bool", "android");
        return id > 0 && resources.getBoolean(id);
    }

}
