package clear.chilli.clear;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;

import cn.vlion.internation.ad.inter.VlionAdListener;
import cn.vlion.internation.ad.model.Interstitial.InterstitialManager;

public class InterstitialActivity extends AppCompatActivity {
    String TAG ="InterstitialActivity";
    String adId;
    private LinearLayout ll_show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interstitial);
        adId = getIntent().getStringExtra("data");

        ll_show= (LinearLayout) findViewById(R.id.ll_show);
        InterstitialManager interstitialManager = new InterstitialManager();
        interstitialManager.getInterstitialAd(InterstitialActivity.this,adId,ll_show, new VlionAdListener() {
            @Override
            public void onRequestSucceed(String adId) {
                Log.e(TAG,"onRequestSucceed adId="+adId);
            }

            @Override
            public void onShowSucceed(String adId) {
                Log.e(TAG,"onShowSucceed adId="+adId);
            }

            @Override
            public void onRequestFailed(String adId, int code, String errorMsg) {
                Log.e(TAG,"onRequestFailed adId="+adId+"code="+code+"errorMsg"+errorMsg);
            }

            @Override
            public void onClicked(String adId) {
                Log.e(TAG,"onRequestFailed adId="+adId);
            }

            @Override
            public void onClosed(String adId) {
                Log.e(TAG,"onClosed adId="+adId);
            }

            @Override
            public void onImpression(String adId) {
                Log.e(TAG,"onImpression adId="+adId);
            }

        });
    }
}
