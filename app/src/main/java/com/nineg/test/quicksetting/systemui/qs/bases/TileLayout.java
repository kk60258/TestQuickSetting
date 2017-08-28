package com.nineg.test.quicksetting.systemui.qs.bases;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.nineg.test.quicksetting.QSPanelActivity;

import java.util.ArrayList;

public class TileLayout extends LinearLayout implements QSPanelActivity.QSTileLayout {

    private static final float TILE_ASPECT = 1.2f;

    private static final String TAG = "TileLayout";

    private Context mContext;
    protected final ArrayList<QSPanelActivity.TileRecord> mRecords = new ArrayList<>();
    private int mCellMarginTop;
    private boolean mListening;

    public TileLayout(Context context) {
        this(context, null);
    }

    public TileLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        setFocusableInTouchMode(true);
        updateResources();

    }

    @Override
    public int getOffsetTop(QSPanelActivity.TileRecord tile) {
        return getTop();
    }

    @Override
    public boolean updateResources() {
        return false;
    }

    @Override
    public void setListening(boolean listening) {
        if (mListening == listening) return;
        mListening = listening;
        for (QSPanelActivity.TileRecord record : mRecords) {
            record.tile.setListening(this, mListening);
        }
    }

    public void addTile(QSPanelActivity.TileRecord tile) {
        mRecords.add(tile);
        tile.tile.setListening(this, mListening);
        addView(tile.tileView);
    }

    @Override
    public void removeTile(QSPanelActivity.TileRecord tile) {
        mRecords.remove(tile);
        tile.tile.setListening(this, false);
        removeView(tile.tileView);
    }

    public void removeAllViews() {
        for (QSPanelActivity.TileRecord record : mRecords) {
            record.tile.setListening(this, false);
        }
        mRecords.clear();
        super.removeAllViews();
    }
}
