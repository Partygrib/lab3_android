package com.example.lab3_5.ui.second;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.View.OnClickListener;

import androidx.navigation.NavController;

import androidx.navigation.Navigation;

import com.example.lab3_5.R;
import com.example.lab3_5.databinding.FragmentSecond5Binding;

public class SecondFragment extends Fragment {

    private FragmentSecond5Binding binding;
    private Button button1;
    private Button button2;
    NavController navController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSecond5Binding.inflate(inflater, container, false);
        View root = binding.getRoot();
        navController = Navigation.findNavController(container);
        button1 = binding.Button1;
        button2 = binding.Button2;
        OnClickListener but1 = new OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_f2_to_f1);
            }
        };
        button1.setOnClickListener(but1);
        OnClickListener but2 = new OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_f2_to_f3);
            }
        };
        button2.setOnClickListener(but2);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}