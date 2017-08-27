package com.nineg.test.quicksetting.systemui.qs.external;

import android.content.ComponentName;

import com.nineg.test.quicksetting.systemui.qs.bases.QSTile;

/**
 * Created by nineg on 2017/8/27.
 */

//A Helper classes to deference external tiles from QSTileHost. This speeds up developement.
public class ExternalTilesHelper {
    public static final String PREFIX = "custom(";//CustomTile.PREFIX

    public static boolean isCustomTiles(QSTile tile) {
        return false;
//        return tile instanceof CustomTile;
    }

    public static boolean isCustomTilesUserIsCurrentUser(QSTile tile, int currentUser) {
        return true;
//        return isCustomTiles(tile) && ((CustomTile) tile).getUser() == currentUser;
    }

    public static String toCustomTileSpec(ComponentName name) {
        return PREFIX + name.flattenToShortString() + ")";
    }
}
