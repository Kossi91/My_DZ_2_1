package com.example.my_dz_2_1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class SecondFragment extends Fragment {

    private ImageView ivFullscreen;
    private TextView tvText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ivFullscreen = view.findViewById(R.id.iv_fullscreen);
        tvText = view.findViewById(R.id.tv_text);
        getData();

        tvText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               requireActivity().getSupportFragmentManager().beginTransaction()
                       .replace(R.id.fragment_container, new ThirdFragment())
                       .addToBackStack("SecondFragment")
                       .commit();

            }
        });
    }

    private void getData() {
        Bundle data = getArguments();
        if (data != null){
            String text = data.getString(FirstFragment.TEXT_BUNDLE_KEY);
            Uri imageForGet = Uri.parse(data.getString(FirstFragment.IMAGE_BUNDLE_KEY));
            tvText.setText(text);
            ivFullscreen.setImageURI(imageForGet);
        }
    }
}