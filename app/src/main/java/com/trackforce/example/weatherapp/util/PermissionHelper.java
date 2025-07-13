package com.trackforce.example.weatherapp.util;

import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionHelper {

    private final Activity activity;
    private final int requestCode;

    public PermissionHelper(Activity activity, int requestCode) {
        this.activity = activity;
        this.requestCode = requestCode;
    }

    public boolean hasPermission(String permission) {
        return ContextCompat.checkSelfPermission(activity, permission)
                == PackageManager.PERMISSION_GRANTED;
    }

    public boolean hasPermissions(String[] permissions) {
        for (String permission : permissions) {
            if (!hasPermission(permission)) {
                return false;
            }
        }
        return true;
    }

    public void requestPermissions(String[] permissions) {
        ActivityCompat.requestPermissions(activity, permissions, requestCode);
    }

    public void handlePermissionResult(String[] permissions, int[] grantResults, PermissionCallback callback) {
        boolean allGranted = true;
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                allGranted = false;
                break;
            }
        }
        if (allGranted) {
            callback.onGranted();
        } else {
            callback.onDenied();
        }
    }

    public interface PermissionCallback {
        void onGranted();
        void onDenied();
    }
}
