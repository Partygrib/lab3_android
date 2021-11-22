package com.example.lab3_5.ui.first;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.View.OnClickListener;

import androidx.navigation.NavController;

import androidx.navigation.Navigation;

import com.example.lab3_5.R;
import com.example.lab3_5.databinding.FragmentFirst5Binding;

public class FirstFragment extends Fragment {

    private FragmentFirst5Binding binding;
    private Button button;
    NavController navController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFirst5Binding.inflate(inflater, container, false);
        View root = binding.getRoot();
        navController = Navigation.findNavController(container);
        button = binding.bnToSecond;
        OnClickListener but1 = new OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_f1_to_f2);
            }
        };
        button.setOnClickListener(but1);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

