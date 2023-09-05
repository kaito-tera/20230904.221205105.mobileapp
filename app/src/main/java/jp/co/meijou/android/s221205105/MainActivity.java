package jp.co.meijou.android.s221205105;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import jp.co.meijou.android.s221205105.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button.setOnClickListener(view -> {
            var text = binding.editTextText.getText().toString();
            binding.text.setText(text);
        });

        binding.editTextText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                binding.text.setText(editable.toString());

            }
        });

        /*binding.button.setOnClickListener(view -> {
            binding.text.setText(R.string.meijou);
           /* int textRes = 0;
            binding.text.setText(textRes);
            if(textRes == R.string.name1) {
                textRes = R.string.meijou;
            }else{
                textRes = R.string.name1;
            
        });

        binding.button2.setOnClickListener(view -> {
            binding.imageView.setImageResource(R.drawable.baseline_local_fire_department_24);

        });
       // binding.text.setText(R.string.name1);
        */
    }
}