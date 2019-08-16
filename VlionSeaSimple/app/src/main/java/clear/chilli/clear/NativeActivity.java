package clear.chilli.clear;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.vlion.internation.ad.inter.NativeVlionAdListener;
import cn.vlion.internation.ad.javabean.NativeVlionAd;
import cn.vlion.internation.ad.model.nativead.NativeManager;

public class NativeActivity extends AppCompatActivity {
    String TAG ="NativeActivity";
    String adId;
    LinearLayout rootView, iconView, mediaView, ad_choices_container;
    private NativeManager nativeManager;
    private TextView native_ad_title;
    private Button bt_show;


    LinearLayout rootViewAd, iconViewAd, mediaViewAd, ad_choices_containerAd;
    private TextView native_ad_titleAd;
    NativeVlionAd nativeVlionAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native);
        adId = getIntent().getStringExtra("data");


//        ViewGroup rootVoew=findViewById(R.id.in_native);

        rootView = findViewById(R.id.rootView);
        iconView = findViewById(R.id.iconView);
        native_ad_title = findViewById(R.id.native_ad_title);
        mediaView = findViewById(R.id.mediaView);
        ad_choices_container = findViewById(R.id.ad_choices_container);


        bt_show = findViewById(R.id.bt_show);
        rootViewAd = findViewById(R.id.rootViewAd);
        iconViewAd = findViewById(R.id.iconViewAd);
        native_ad_titleAd = findViewById(R.id.native_ad_titleAd);
        mediaViewAd = findViewById(R.id.mediaViewAd);
        ad_choices_containerAd = findViewById(R.id.ad_choices_containerAd);


//        for (int i = 0; i < rootView.getChildCount();i++){
//            View view=rootView.getChildAt(i);
//            if(null!=view){
//                rootView.removeViewAt(i);
//                Log.e("NativeActivity","=========="+rootView.getChildCount());
////                nativeAdLayout.addView(view);
//            }
//        }

        nativeManager = new NativeManager();
        nativeManager.getNativeAd(NativeActivity.this, adId, new NativeVlionAdListener() {
            @Override
            public void onRequestSucceed(String adId, NativeVlionAd nativeVlionAd) {
                Log.e(TAG,"onClicked="+adId);
                bt_show.setVisibility(View.VISIBLE);
                NativeActivity.this.nativeVlionAd=nativeVlionAd;
                TextView nativeAdTitle = findViewById(R.id.native_ad_title);
                TextView nativeAdSocialContext = findViewById(R.id.native_ad_social_context);
                TextView nativeAdBody = findViewById(R.id.native_ad_body);
                TextView sponsoredLabel = findViewById(R.id.native_ad_sponsored_label);
                Button nativeAdCallToAction = findViewById(R.id.native_ad_call_to_action);


                nativeAdTitle.setText(nativeVlionAd.getTitle());
                nativeAdBody.setText(nativeVlionAd.getAdBody());
                nativeAdSocialContext.setText(nativeVlionAd.getAdSocialContext());
                nativeAdCallToAction.setVisibility(nativeVlionAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
                nativeAdCallToAction.setText(nativeVlionAd.getAdCallToAction());
                sponsoredLabel.setText(nativeVlionAd.getLable());

                if (!TextUtils.isEmpty(nativeVlionAd.getIcon())) {
                    ImageView imageView = new ImageView(NativeActivity.this);
                    imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                    iconView.addView(imageView);
                    Glide.with(NativeActivity.this).load(nativeVlionAd.getIcon()).into(imageView);
                }

                // Create a list of clickable views
                List<View> clickableViews = new ArrayList<>();
                clickableViews.add(nativeAdTitle);
                clickableViews.add(nativeAdCallToAction);

                nativeVlionAd.onRegisterView(NativeActivity.this, rootView, iconView, native_ad_title, mediaView, ad_choices_container, clickableViews);

            }


            @Override
            public void onRequestFailed(String adId, int code, String errorMsg) {
                Log.e(TAG,"onRequestFailed adId="+adId+"code="+code+"errorMsg"+errorMsg);

            }

            @Override
            public void onClicked(String adId) {
                Log.e(TAG,"onClicked="+adId);

            }

            @Override
            public void onClosed(String adId) {
                Log.e(TAG,"onClosed="+adId);
            }

            @Override
            public void onImpression(String adId) {
                Log.e(TAG,"onImpression="+adId);
            }
        });
        bt_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView nativeAdTitleAd = findViewById(R.id.native_ad_titleAd);
                TextView nativeAdSocialContextAd = findViewById(R.id.native_ad_social_contextAd);
                TextView nativeAdBodyAd = findViewById(R.id.native_ad_bodyAd);
                TextView sponsoredLabelAd = findViewById(R.id.native_ad_sponsored_labelAd);
                Button nativeAdCallToActionAd = findViewById(R.id.native_ad_call_to_actionAd);


                nativeAdTitleAd.setText(nativeVlionAd.getTitle());
                nativeAdBodyAd.setText(nativeVlionAd.getAdBody());
                nativeAdSocialContextAd.setText(nativeVlionAd.getAdSocialContext());
                nativeAdCallToActionAd.setVisibility(nativeVlionAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
                nativeAdCallToActionAd.setText(nativeVlionAd.getAdCallToAction());
                sponsoredLabelAd.setText(nativeVlionAd.getLable());

                if (!TextUtils.isEmpty(nativeVlionAd.getIcon())) {
                    ImageView imageView = new ImageView(NativeActivity.this);
                    imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                    iconView.addView(imageView);
                    Glide.with(NativeActivity.this).load(nativeVlionAd.getIcon()).into(imageView);
                }

                // Create a list of clickable views
                List<View> clickableViews = new ArrayList<>();
                clickableViews.add(nativeAdTitleAd);
                clickableViews.add(nativeAdCallToActionAd);

                nativeVlionAd.onRegisterView(NativeActivity.this, rootViewAd, iconViewAd, native_ad_titleAd, mediaViewAd, ad_choices_containerAd, clickableViews);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        nativeManager.onDestory();
    }
}
