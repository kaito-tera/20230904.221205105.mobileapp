package jp.co.meijou.android.s221205105;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import jp.co.meijou.android.s221205105.databinding.ActivityMain3Binding;
import jp.co.meijou.android.s221205105.databinding.ActivityMainBinding;

public class MainActivity3 extends AppCompatActivity {

    private ActivityMain3Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.mbutton.setOnClickListener(view -> {
            var intent = new Intent(this,SubActivity.class);
            startActivity(intent);

        });
        binding.abutton.setOnClickListener(view -> {
            var intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://www.yahoo.co.jp"));

            startActivity(intent);

        });

        binding.button1.setOnClickListener(view -> {
            var intent = new Intent(this,SubActivity.class);
            intent.putExtra("text",binding.editText.getText().toString());
            startActivity(intent);

        });
    }
}