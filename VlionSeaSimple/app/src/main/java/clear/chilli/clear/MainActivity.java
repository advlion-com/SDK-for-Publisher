package clear.chilli.clear;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.ads.consent.ConsentForm;
import com.google.ads.consent.ConsentFormListener;
import com.google.ads.consent.ConsentInfoUpdateListener;
import com.google.ads.consent.ConsentInformation;
import com.google.ads.consent.ConsentStatus;
import com.google.ads.consent.DebugGeography;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private ConsentInformation consentInformation;
    private ConsentForm form;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //欧盟征求意见
//        一组发布商 ID，供您的应用发出广告请求。 找到您的发布商 ID。
//        一个 ConsentInfoUpdateListener 的实例。

        ConsentInformation.getInstance(MainActivity.this).addTestDevice("11899CAAAA38428399DF38AEECFE3FAB");

        // Geography appears as in EEA for test devices.
        ConsentInformation.getInstance(this).
                setDebugGeography(DebugGeography.DEBUG_GEOGRAPHY_EEA);
//    Geography appears as not in EEA for debug devices.
//        ConsentInformation.getInstance(this).
//                setDebugGeography(DebugGeography.DEBUG_GEOGRAPHY_NOT_EEA);


        consentInformation = ConsentInformation.getInstance(this);
        String[] publisherIds = {"pub-3624037410015801"};
        consentInformation.requestConsentInfoUpdate(publisherIds, new ConsentInfoUpdateListener() {
            @Override
            public void onConsentInfoUpdated(ConsentStatus consentStatus) {
                // User's consent status successfully updated.
//                ConsentStatus.PERSONALIZED	用户已同意接收个性化广告。
//                ConsentStatus.NON_PERSONALIZED	用户已同意接收非个性化广告。
//                ConsentStatus.UNKNOWN	用户既未同意接收也未拒绝接收个性化广告或非个性化广告。
                Log.e("MainActivity", "" + consentStatus);
//

                if (consentStatus.equals(ConsentStatus.PERSONALIZED)) {

                }
                else if (consentStatus.equals(ConsentStatus.PERSONALIZED)) {

                }
                else if (consentStatus.equals(ConsentStatus.PERSONALIZED)) {

                }

//                consentInformation.isRequestLocationInEeaOrUnknown()，以查看用户是否位于欧洲经济区内或者请求位置是否未知。
                Log.e("MainActivity", "isRequestLocationInEeaOrUnknown:" + consentInformation.isRequestLocationInEeaOrUnknown());
                if (consentInformation.isRequestLocationInEeaOrUnknown()) {
//                    如果返回的 ConsentStatus 为 PERSONALIZED 或 NON_PERSONALIZED，则表明用户已提供用户意见。您现在可以将用户意见转发给 Google 移动广告 SDK。
                    if (consentStatus.equals(ConsentStatus.PERSONALIZED) || consentStatus.equals(ConsentStatus.PERSONALIZED)) {
                        Bundle extras = new Bundle();
                        extras.putString("npa", "1");
                        showForm();
                    }
                    if (consentStatus.equals(ConsentStatus.UNKNOWN)) {


                    }

                }
            }

            @Override
            public void onFailedToUpdateConsentInfo(String errorDescription) {
                // User's consent status failed to update.
                Log.e("MainActivity", "onFailedToUpdateConsentInfo:" + errorDescription + "+++++" + consentInformation.isRequestLocationInEeaOrUnknown());

            }
        });

        findViewById(R.id.tv_banner).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BannerActivity.class).putExtra("data", "23491"));
            }
        });
        findViewById(R.id.tv_interstitial).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, InterstitialActivity.class).putExtra("data", "23492"));
            }
        });
        findViewById(R.id.tv_native).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NativeActivity.class).putExtra("data", "23490"));
            }
        });
        findViewById(R.id.tv_video).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, VideoActivity.class).putExtra("data", "23495"));
            }
        });


    }

    private void showForm() {
        URL privacyUrl = null;
        try {
            // TODO: Replace with your app's privacy policy URL.
            privacyUrl = new URL("https://www.your.com/privacyurl");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            // Handle error.
        }
        form = new ConsentForm.Builder(MainActivity.this, privacyUrl)
                .withListener(new ConsentFormListener() {
                    @Override
                    public void onConsentFormLoaded() {
                        // Consent form loaded successfully.
                        Log.e("MainActivity", "onConsentFormLoaded");
                        form.show();
                    }

                    @Override
                    public void onConsentFormOpened() {
                        // Consent form was displayed.
                        Log.e("MainActivity", "onConsentFormLoaded");
                    }

                    @Override
                    public void onConsentFormClosed(ConsentStatus consentStatus, Boolean userPrefersAdFree) {
                        // Consent form was closed.
                        Log.e("MainActivity","onConsentFormClosed");
                    }

                    @Override
                    public void onConsentFormError(String errorDescription) {
                        // Consent form error.
                        Log.e("MainActivity","onConsentFormError"+errorDescription);
                    }
                })
                .withPersonalizedAdsOption()
                .withNonPersonalizedAdsOption()
                .withAdFreeOption()
                .build();
        form.load();
    }
}
