package com.example.my_dz_2_1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;


public class ThirdFragment extends Fragment {

    EditText editName;
    EditText editPassword;
    MaterialButton buttonConfig;
    String isPassword = "admin";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editName = view.findViewById(R.id.ed_name);
        editPassword = view.findViewById(R.id.ed_password);
        buttonConfig = view.findViewById(R.id.button);
        click();
    }

    private void click() {
        buttonConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editName.getText().toString();
                String password = editPassword.getText().toString();
                if (!email.equals(isPassword) || !password.equals(isPassword)) {
                    if (!email.equals(isPassword)) {
                        editName.setError("Неверное имя пользователя");
                    } else if (!password.equals(isPassword)) {
                        editPassword.setError("Неправильный пароль");
                    }
                } else {
                    Toast.makeText(getActivity(), "Добро пожалывать", Toast.LENGTH_SHORT).show();

                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                    fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    requireActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new FirstFragment())
                            .commit();


                }
            }
        });
    }
}