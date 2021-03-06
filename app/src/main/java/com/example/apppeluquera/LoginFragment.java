package com.example.apppeluquera;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apppeluquera.databinding.FragmentLoginBinding;
import com.example.apppeluquera.databinding.FragmentRegistrationBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import static android.content.ContentValues.TAG;

public class LoginFragment extends BaseFragment {

    private FragmentLoginBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentLoginBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);

        binding.login.setOnClickListener(view1 -> {

            String email = binding.editTextEmailAdress.getText().toString();
            String password_ = binding.editTextPassword.getText().toString();

            String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
            java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
            java.util.regex.Matcher m = p.matcher(email);

            if(email.isEmpty()){
                Toast.makeText(requireContext(), "Introduce un correo electr??nico", Toast.LENGTH_SHORT).show();
            } else if (!m.matches()){
                Toast.makeText(requireContext(), "Introduce un correo electr??nico v??lido", Toast.LENGTH_SHORT).show();
            } else if (password_.isEmpty()){
                Toast.makeText(requireContext(), "Introduce tu contrase??a", Toast.LENGTH_SHORT).show();
            } else {
                //navController.navigate(R.id.action_loginFragment_to_menuFragment);
                auth.signInWithEmailAndPassword(email, password_).addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                db.collection("users").document(auth.getCurrentUser().getUid()).get().addOnCompleteListener(task1 -> {
                                    if (task1.isSuccessful()) {
                                        DocumentSnapshot document = task1.getResult();
                                        if (document.exists()) {
                                            if (document.get("type").equals("user")) {
                                                System.out.println("Login como user");
                                                System.out.println(document.get("type").toString());
                                                nav.navigate(R.id.action_loginFragment_to_menuFragment);
                                            } else if (document.get("type").equals("business")) {
                                                System.out.println("Login como business");
                                                nav.navigate(R.id.action_loginFragment_to_menuNegocioFragment);
                                            }
                                        }
                                    }
                                });

                            } else {
                                Toast.makeText(requireContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        binding.goToRegistration.setOnClickListener(view2 -> {
            nav.navigate(R.id.action_loginFragment_to_registrationOptionFragment);
        });

    }
}
