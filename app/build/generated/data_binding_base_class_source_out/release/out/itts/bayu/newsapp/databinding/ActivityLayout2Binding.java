// Generated by view binder compiler. Do not edit!
package itts.bayu.newsapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import itts.bayu.newsapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityLayout2Binding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final ChipNavigationBar bottomNavigation;

  @NonNull
  public final RelativeLayout fragmentLayout;

  private ActivityLayout2Binding(@NonNull RelativeLayout rootView,
      @NonNull ChipNavigationBar bottomNavigation, @NonNull RelativeLayout fragmentLayout) {
    this.rootView = rootView;
    this.bottomNavigation = bottomNavigation;
    this.fragmentLayout = fragmentLayout;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityLayout2Binding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityLayout2Binding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_layout_2, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityLayout2Binding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.bottomNavigation;
      ChipNavigationBar bottomNavigation = rootView.findViewById(id);
      if (bottomNavigation == null) {
        break missingId;
      }

      RelativeLayout fragmentLayout = (RelativeLayout) rootView;

      return new ActivityLayout2Binding((RelativeLayout) rootView, bottomNavigation,
          fragmentLayout);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}