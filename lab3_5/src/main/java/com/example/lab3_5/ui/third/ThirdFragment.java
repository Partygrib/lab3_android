package com.example.lab3_5.ui.third;

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
import com.example.lab3_5.databinding.FragmentThird5Binding;

public class ThirdFragment extends Fragment {

    private FragmentThird5Binding binding;
    private Button button1;
    private Button button2;
    NavController navController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentThird5Binding.inflate(inflater, container, false);
        View root = binding.getRoot();
        navController = Navigation.findNavController(container);
        button1 = binding.bnToFirst;
        button2 = binding.bnToSecond;
        OnClickListener but1 = new OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_f3_to_f1);
            }
        };
        button1.setOnClickListener(but1);
        OnClickListener but2 = new OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_f3_to_f2);
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