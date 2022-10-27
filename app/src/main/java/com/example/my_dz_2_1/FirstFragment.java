package com.example.my_dz_2_1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;


public class FirstFragment extends Fragment {

    public static final String TEXT_BUNDLE_KEY = "text";
    public static final String IMAGE_BUNDLE_KEY = "image";
    private ImageView ivImage;
    private EditText etInput;
    private MaterialButton btnSend;
    private Uri imageForSend = null;

    // Фрагмент привязка к Активити
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    // Фрагмент создается
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //Создается содержимое Фрагмента
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ferst, container, false);
    }

    //Содержимое было создано и изменять тут
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ivImage = view.findViewById(R.id.iv_image);
        etInput = view.findViewById(R.id.ed_input);
        btnSend = view.findViewById(R.id.btn_sent);

        setUpListeners();
    }

    private void setUpListeners() {
        ivImage.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            activityResultLauncher.launch(intent);

        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = etInput.getText().toString().trim();
                Bundle bundle = new Bundle();
                if (text.isEmpty() && imageForSend == null){
                    Toast.makeText(getActivity(), "Введите текст и выберите изображение", Toast.LENGTH_SHORT).show();
                }
                    else if (text.isEmpty()){
                        Toast.makeText(getActivity(), "Введите текст", Toast.LENGTH_SHORT).show();
                    }
                    else if (imageForSend == null){
                        Toast.makeText(getActivity(), "выберите изображение", Toast.LENGTH_SHORT).show();
                    }
                else {
                    bundle.putString(TEXT_BUNDLE_KEY, text);
                    bundle.putString(IMAGE_BUNDLE_KEY, imageForSend.toString());
                    getParentFragmentManager().beginTransaction()
                            .add(R.id.fragment_container, SecondFragment.class, bundle)
                            .addToBackStack("FirstFragment")
                            .commit();
                }
            }
        });
    }

    private final ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == Activity.RESULT_OK) {
            @Nullable Intent data = result.getData();
            if (data != null) {
                @Nullable Uri imageFromGallery = data.getData();
                if (imageFromGallery != null) {
                    ivImage.setImageURI(imageFromGallery);
                    imageForSend = imageFromGallery;
                }
            }
        }
    });

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    // содержимость Фрагмента уничтажется
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    //Фрагмент отвязывается от Активити
    @Override
    public void onDetach() {
        super.onDetach();
    }
}
