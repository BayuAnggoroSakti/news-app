// Generated by view binder compiler. Do not edit!
package itts.bayu.newsapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.makeramen.roundedimageview.RoundedImageView;
import itts.bayu.newsapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ListItemNewsBinding implements ViewBinding {
  @NonNull
  private final FrameLayout rootView;

  @NonNull
  public final FrameLayout frameListNews;

  @NonNull
  public final RoundedImageView imageThumbnail;

  @NonNull
  public final LinearLayout linearlay;

  @NonNull
  public final TextView tvDateTime;

  @NonNull
  public final TextView tvNameSource;

  @NonNull
  public final TextView tvTimeAgo;

  @NonNull
  public final TextView tvTitleNews;

  private ListItemNewsBinding(@NonNull FrameLayout rootView, @NonNull FrameLayout frameListNews,
      @NonNull RoundedImageView imageThumbnail, @NonNull LinearLayout linearlay,
      @NonNull TextView tvDateTime, @NonNull TextView tvNameSource, @NonNull TextView tvTimeAgo,
      @NonNull TextView tvTitleNews) {
    this.rootView = rootView;
    this.frameListNews = frameListNews;
    this.imageThumbnail = imageThumbnail;
    this.linearlay = linearlay;
    this.tvDateTime = tvDateTime;
    this.tvNameSource = tvNameSource;
    this.tvTimeAgo = tvTimeAgo;
    this.tvTitleNews = tvTitleNews;
  }

  @Override
  @NonNull
  public FrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ListItemNewsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ListItemNewsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.list_item_news, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ListItemNewsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      FrameLayout frameListNews = (FrameLayout) rootView;

      id = R.id.imageThumbnail;
      RoundedImageView imageThumbnail = rootView.findViewById(id);
      if (imageThumbnail == null) {
        break missingId;
      }

      id = R.id.linearlay;
      LinearLayout linearlay = rootView.findViewById(id);
      if (linearlay == null) {
        break missingId;
      }

      id = R.id.tvDateTime;
      TextView tvDateTime = rootView.findViewById(id);
      if (tvDateTime == null) {
        break missingId;
      }

      id = R.id.tvNameSource;
      TextView tvNameSource = rootView.findViewById(id);
      if (tvNameSource == null) {
        break missingId;
      }

      id = R.id.tvTimeAgo;
      TextView tvTimeAgo = rootView.findViewById(id);
      if (tvTimeAgo == null) {
        break missingId;
      }

      id = R.id.tvTitleNews;
      TextView tvTitleNews = rootView.findViewById(id);
      if (tvTitleNews == null) {
        break missingId;
      }

      return new ListItemNewsBinding((FrameLayout) rootView, frameListNews, imageThumbnail,
          linearlay, tvDateTime, tvNameSource, tvTimeAgo, tvTitleNews);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}