package com.nineg.test.quicksetting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nineg.test.quicksetting.systemui.policy.FlashlightController;
import com.nineg.test.quicksetting.systemui.qs.bases.QSTile;
import com.nineg.test.quicksetting.systemui.qs.bases.QSTileBaseView;
import com.nineg.test.quicksetting.systemui.qs.bases.QSTileHost;
import com.nineg.test.quicksetting.systemui.qs.bases.QSTileView;
import com.nineg.test.quicksetting.systemui.qs.bases.TileLayout;

import java.util.ArrayList;

/**
 * Created by nineg on 2017/7/16.
 */

public class QSPanelActivity extends Activity {
    ViewGroup mPanelView;
    QSTileHost mHost;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qs_panel_main);
        mPanelView = (ViewGroup) findViewById(R.id.qs_main);
        setupTileLayout();
        FlashlightController flashlightController = new FlashlightController(this);
        mHost = new QSTileHost(this, flashlightController);
        for (QSTile tiles : mHost.getTiles()) {
            addTile(tiles, false);
        }

    }

    protected QSTileBaseView createTileView(QSTile<?> tile, boolean collapsedView) {
        return new QSTileView(this, tile.createTileView(this), collapsedView);
    }

    protected void drawTile(TileRecord r, QSTile.State state) {
        r.tileView.onStateChanged(state);
    }

    protected void addTile(final QSTile<?> tile, boolean collapsedView) {
        final TileRecord r = new TileRecord();
        r.tile = tile;
        r.tileView = createTileView(tile, collapsedView);
        final QSTile.Callback callback = new QSTile.Callback() {
            @Override
            public void onStateChanged(QSTile.State state) {
                drawTile(r, state);
            }

            @Override
            public void onShowDetail(boolean show) {
                // Both the collapsed and full QS panels get this callback, this check determines
                // which one should handle showing the detail.
//                if (shouldShowDetail()) {
//                    QSPanel.this.showDetail(show, r);
//                }
            }

            @Override
            public void onToggleStateChanged(boolean state) {
//                if (mDetailRecord == r) {
//                    fireToggleStateChanged(state);
//                }
            }

            @Override
            public void onScanStateChanged(boolean state) {
//                r.scanState = state;
//                if (mDetailRecord == r) {
//                    fireScanStateChanged(r.scanState);
//                }
            }

            @Override
            public void onAnnouncementRequested(CharSequence announcement) {
//                announceForAccessibility(announcement);
            }
        };
        r.tile.addCallback(callback);
        r.callback = callback;
        final View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTileClick(r.tile);
            }
        };
        final View.OnLongClickListener longClick = new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                r.tile.longClick();
                return true;
            }
        };
        r.tileView.init(click, longClick);
        r.tile.refreshState();
        mRecords.add(r);

        if (mTileLayout != null) {
            mTileLayout.addTile(r);
        }
    }

    protected void onTileClick(QSTile<?> tile) {
        tile.click();
    }

    protected void setupTileLayout() {
        mTileLayout = new TileLayout(this);
        mTileLayout.setListening(mListening);
        mPanelView.addView((View) mTileLayout);
    }

    protected final ArrayList<TileRecord> mRecords = new ArrayList<TileRecord>();

    protected static class Record {
//        DetailAdapter detailAdapter;
        int x;
        int y;
    }

    public static final class TileRecord extends Record {
        public QSTile<?> tile;
        public QSTileBaseView tileView;
        public boolean scanState;
        public QSTile.Callback callback;
    }

    public interface Callback {
//        void onShowingDetail(DetailAdapter detail, int x, int y);
        void onToggleStateChanged(boolean state);
        void onScanStateChanged(boolean state);
    }

    protected QSTileLayout mTileLayout;

    public interface QSTileLayout {
        void addTile(TileRecord tile);
        void removeTile(TileRecord tile);
        int getOffsetTop(TileRecord tile);
        boolean updateResources();

        void setListening(boolean listening);
    }

    protected boolean mListening;
    public void setListening(boolean listening) {
        if (mListening == listening) return;
        mListening = listening;
        if (mTileLayout != null) {
            mTileLayout.setListening(listening);
        }
//        mFooter.setListening(mListening);
        if (mListening) {
            refreshAllTiles();
        }
//        if (mBrightnessView.getVisibility() == View.VISIBLE) {
//            if (listening) {
//                mBrightnessController.registerCallbacks();
//            } else {
//                mBrightnessController.unregisterCallbacks();
//            }
//        }
    }

    public void refreshAllTiles() {
        for (TileRecord r : mRecords) {
            r.tile.refreshState();
        }
    }
}
