package com.nineg.test.quicksetting.systemui.model;

import android.content.ContentResolver;
import android.os.UserHandle;

/**
 * Created by nineg on 2017/8/27.
 */

public class QSTileModel {
    public static String getString(ContentResolver resolver, String name) {
        return null;
    }

    public static boolean putString(ContentResolver resolver, String name, String value) {
        return true;
    }

    public static int getInt(ContentResolver cr, String name, int def) {
        String v = getString(cr, name);
        try {
            return v != null ? Integer.parseInt(v) : def;
        } catch (NumberFormatException e) {
            return def;
        }
    }

    public static boolean putInt(ContentResolver cr, String name, int value) {
        return putString(cr, name, Integer.toString(value));
    }
}
