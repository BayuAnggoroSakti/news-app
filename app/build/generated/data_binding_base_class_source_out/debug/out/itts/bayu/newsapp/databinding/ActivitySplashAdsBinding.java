// Generated by view binder compiler. Do not edit!
package itts.bayu.newsapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.huawei.hms.ads.splash.SplashView;
import itts.bayu.newsapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivitySplashAdsBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final RelativeLayout logoArea;

  @NonNull
  public final SplashView splashAdView;

  private ActivitySplashAdsBinding(@NonNull RelativeLayout rootView,
      @NonNull RelativeLayout logoArea, @NonNull SplashView splashAdView) {
    this.rootView = rootView;
    this.logoArea = logoArea;
    this.splashAdView = splashAdView;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivitySplashAdsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivitySplashAdsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_splash_ads, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivitySplashAdsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.logo_area;
      RelativeLayout logoArea = rootView.findViewById(id);
      if (logoArea == null) {
        break missingId;
      }

      id = R.id.splash_ad_view;
      SplashView splashAdView = rootView.findViewById(id);
      if (splashAdView == null) {
        break missingId;
      }

      return new ActivitySplashAdsBinding((RelativeLayout) rootView, logoArea, splashAdView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}