package com.example.android.bakingapp;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingapp.model.Step;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class StepDetailListFragment extends Fragment {
    TextView tvDes;
    Step s;
    private SimpleExoPlayer mExoplayer;
    private SimpleExoPlayerView mExoPlayerView;
    String videoUrl;
    int mCurrentWindow = 0;
    long mPlayBackPosition = 0;
    boolean mPlayWhenReady = true;


    public StepDetailListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            videoUrl = savedInstanceState.getString("url");
            s = savedInstanceState.getParcelable("s");
            mCurrentWindow = savedInstanceState.getInt("window");
            mPlayBackPosition = savedInstanceState.getLong("pos");
            mPlayWhenReady = savedInstanceState.getBoolean("r");

        }
        View rView = inflater.inflate(R.layout.fragment_step_detail_list, container, false);
        tvDes = (TextView) rView.findViewById(R.id.tv_step_description);
        mExoPlayerView = (SimpleExoPlayerView) rView.findViewById(R.id.playerView);
        videoUrl = s.getVideoURL();

        tvDes.setText(s.getDescription());

        initializePlayer(Uri.parse(videoUrl));
        return rView;
    }

    private void initializePlayer(Uri mediaUri) {
        if (mExoplayer == null) {
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mExoplayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
            mExoPlayerView.setPlayer(mExoplayer);
            String userAgent = Util.getUserAgent(getContext(), "BakingApp");
            MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(getContext(), userAgent)
                    , new DefaultExtractorsFactory(), null, null);
            mExoplayer.prepare(mediaSource);
            mExoplayer.setPlayWhenReady(mPlayWhenReady);
            mExoplayer.seekTo(mCurrentWindow, mPlayBackPosition);


        }


    }

    public void ReciveStep(Step step) {
        s = step;

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }

    private void releasePlayer() {
        mExoplayer.stop();
        if (mExoplayer != null) {
            mExoplayer.release();
            mExoplayer = null;
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("url", videoUrl);
        outState.putParcelable("s", s);
        outState.putInt("window", mCurrentWindow);
        outState.putLong("pos", mPlayBackPosition);
        outState.putBoolean("r", mPlayWhenReady);
    }

}
