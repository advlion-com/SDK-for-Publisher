package clear.chilli.clear;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;

import cn.vlion.internation.ad.inter.RewardVlionAdListener;
import cn.vlion.internation.ad.model.video.VideoManager;

public class VideoActivity extends AppCompatActivity {
    String TAG=VideoActivity.class.getName();
    String adId;
    private LinearLayout ll_show;
    VideoManager videoManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        adId = getIntent().getStringExtra("data");
        ll_show = (LinearLayout) findViewById(R.id.ll_show);
        videoManager = new VideoManager();
        videoManager.getVideoAd(VideoActivity.this, adId, new RewardVlionAdListener() {
                    @Override
                    public void onRequestFailed(String adId, int code, String errorMsg) {
                        Log.e(TAG,"onRequestFailed adId="+adId+"code="+code+"errorMsg"+errorMsg);

                    }

                    @Override
                    public void onClicked(String adId) {
                        Log.e(TAG,"onClicked:adId="+adId);
                    }

                    @Override
                    public void onClosed(String adId) {
                        Log.e(TAG,"onClosed:adId="+adId);
                    }

                    @Override
                    public void onImpression(String adId) {
                        Log.e(TAG,"onImpression:adId="+adId);
                    }

                    @Override
                    public void onRewardLoaded(String adId) {
                        Log.e(TAG,"onRewardLoaded:adId="+adId+"+++isReady()="+videoManager.isReady());
                        if (videoManager.isReady()) {
                            videoManager.show();
                            Log.e(TAG,"onRewardLoaded:adId="+adId+"+++show");
                        }
                    }

                    @Override
                    public void onShowSucceed(String adId) {
                        Log.e(TAG,"onShowSucceed:adId="+adId);
                    }

                    @Override
                    public void onRewardStarted(String adId) {
                        Log.e(TAG,"onRewardStarted:adId="+adId);
                    }

                    @Override
                    public void onRewardCompleted(String adId) {
                        Log.e(TAG,"onRewardCompleted:adId="+adId);
                    }

                    @Override
                    public void onRewarded(String adId) {
                        Log.e(TAG,"onRewarded:adId="+adId);
                    }
                }

        );
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (null != videoManager)
            videoManager.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (null != videoManager)
            videoManager.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
