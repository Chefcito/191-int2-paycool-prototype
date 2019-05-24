package co.edu.icesi.joancaliz.paycool_prototype.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import co.edu.icesi.joancaliz.paycool_prototype.R;

public class OnboardingFragment extends Fragment {
    private static final String DRAWABLE = "drawable";
    private static final String TITLE = "title";
    private static final String CONTENT = "content";

    private int drawable;
    private String title, content;

    private ImageView imageView;
    private TextView titleTextView, contentTextView;

    public OnboardingFragment() {
        // Required empty public constructor
    }

    public static OnboardingFragment newInstance(int drawable, String title, String content) {
        OnboardingFragment fragment = new OnboardingFragment();
        Bundle args = new Bundle();
        args.putInt(DRAWABLE, drawable);
        args.putString(TITLE, title);
        args.putString(CONTENT, content);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            drawable = getArguments().getInt(DRAWABLE);
            title = getArguments().getString(TITLE);
            content = getArguments().getString(CONTENT);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_onboarding, container, false);
        imageView = view.findViewById(R.id.fragment_onboarding_image_image_view);
        Uri imageUri = Uri.parse("android.resource://co.edu.icesi.joancaliz.paycool_prototype/" + drawable);
        imageView.setImageURI(imageUri);
        titleTextView = view.findViewById(R.id.fragment_onboarding_title_text_view);
        titleTextView.setText(title);
        contentTextView = view.findViewById(R.id.fragment_onboarding_description_text_view);
        contentTextView.setText(content);
        return view;
    }
}
