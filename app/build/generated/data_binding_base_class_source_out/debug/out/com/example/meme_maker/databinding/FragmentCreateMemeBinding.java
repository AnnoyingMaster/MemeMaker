// Generated by view binder compiler. Do not edit!
package com.example.meme_maker.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.meme_maker.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentCreateMemeBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageButton btnBrowse;

  @NonNull
  public final ImageButton btnCamera;

  @NonNull
  public final ImageButton btnTemplate;

  @NonNull
  public final TextView txtViewBrowse;

  @NonNull
  public final TextView txtViewGallery;

  @NonNull
  public final TextView txtViewTemplate;

  private FragmentCreateMemeBinding(@NonNull ConstraintLayout rootView,
      @NonNull ImageButton btnBrowse, @NonNull ImageButton btnCamera,
      @NonNull ImageButton btnTemplate, @NonNull TextView txtViewBrowse,
      @NonNull TextView txtViewGallery, @NonNull TextView txtViewTemplate) {
    this.rootView = rootView;
    this.btnBrowse = btnBrowse;
    this.btnCamera = btnCamera;
    this.btnTemplate = btnTemplate;
    this.txtViewBrowse = txtViewBrowse;
    this.txtViewGallery = txtViewGallery;
    this.txtViewTemplate = txtViewTemplate;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentCreateMemeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentCreateMemeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_create_meme, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentCreateMemeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnBrowse;
      ImageButton btnBrowse = ViewBindings.findChildViewById(rootView, id);
      if (btnBrowse == null) {
        break missingId;
      }

      id = R.id.btnCamera;
      ImageButton btnCamera = ViewBindings.findChildViewById(rootView, id);
      if (btnCamera == null) {
        break missingId;
      }

      id = R.id.btnTemplate;
      ImageButton btnTemplate = ViewBindings.findChildViewById(rootView, id);
      if (btnTemplate == null) {
        break missingId;
      }

      id = R.id.txtViewBrowse;
      TextView txtViewBrowse = ViewBindings.findChildViewById(rootView, id);
      if (txtViewBrowse == null) {
        break missingId;
      }

      id = R.id.txtViewGallery;
      TextView txtViewGallery = ViewBindings.findChildViewById(rootView, id);
      if (txtViewGallery == null) {
        break missingId;
      }

      id = R.id.txtViewTemplate;
      TextView txtViewTemplate = ViewBindings.findChildViewById(rootView, id);
      if (txtViewTemplate == null) {
        break missingId;
      }

      return new FragmentCreateMemeBinding((ConstraintLayout) rootView, btnBrowse, btnCamera,
          btnTemplate, txtViewBrowse, txtViewGallery, txtViewTemplate);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
