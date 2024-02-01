package com.android.settings.deviceinfo.firmwareversion;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.SystemProperties;
import android.text.TextUtils;

import androidx.preference.Preference;

import com.android.settings.R;
import com.android.settings.core.BasePreferenceController;

public class amogOSMaintainerPreferenceController extends BasePreferenceController {

    private static final String TAG = "amogOSMaintainerPreferenceController";
    private static final String ROM_PROPERTY = "ro.amogos.maintainer";
    private static final String YOUTUBE_URL = "https://www.youtube.com/watch?v=Ns3YxbIhTRM";

    public amogOSMaintainerPreferenceController(Context context, String key) {
        super(context, key);
    }

    public int getAvailabilityStatus() {
        return AVAILABLE;
    }

    public CharSequence getSummary() {
        String rom = SystemProperties.get(ROM_PROPERTY,
                this.mContext.getString(R.string.device_info_default));
        return rom;
    }

    @Override
    public boolean handlePreferenceTreeClick(Preference preference) {
        if (!TextUtils.equals(preference.getKey(), getPreferenceKey())) {
            openYouTubeVideo();
            return true;
        }
        return super.handlePreferenceTreeClick(preference);
    }

    private void openYouTubeVideo() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(YOUTUBE_URL));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setPackage("com.google.android.youtube"); // Specify the YouTube app
        if (intent.resolveActivity(mContext.getPackageManager()) != null) {
            mContext.startActivity(intent);
        } else {
            // YouTube app not installed, open in a browser or handle as needed
            intent.setPackage(null); // Clear the package to allow the system to handle it
            mContext.startActivity(intent);
        }
    }
}
