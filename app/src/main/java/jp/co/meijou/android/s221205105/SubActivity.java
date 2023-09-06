package jp.co.meijou.android.s221205105;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import jp.co.meijou.android.s221205105.databinding.ActivityMain3Binding;
import jp.co.meijou.android.s221205105.databinding.ActivitySubBinding;

public class SubActivity extends AppCompatActivity {

    private ActivitySubBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySubBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }
}