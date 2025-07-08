package com.example.btvn_recyclerview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DetailedProductFragment extends Fragment {

    ImageView imageView;
    TextView textView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detailed_product, container, false);

        imageView = view.findViewById(R.id.imgDetail);
        textView = view.findViewById(R.id.txtDetail);

        if (getArguments() != null) {
            int imageResId = getArguments().getInt("imageResId");
            String name = getArguments().getString("name");

            imageView.setImageResource(imageResId);
            textView.setText(name);
        }

        return view;
    }
}
