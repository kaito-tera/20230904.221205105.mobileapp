package jp.co.meijou.android.s221205105;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import java.util.Optional;

import jp.co.meijou.android.s221205105.databinding.ActivityMain3Binding;
import jp.co.meijou.android.s221205105.databinding.ActivityMainBinding;

public class MainActivity3 extends AppCompatActivity {

    private ActivityMain3Binding binding;
    private final ActivityResultLauncher<Intent> getActivityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                switch (result.getResultCode()) {
                    case RESULT_OK:
                        Optional.ofNullable(result.getData())
                                .map(data -> data.getStringExtra("ret"))
                                .map(text -> "Result: " + text)
                                .ifPresent(text -> binding.editText3.setText(text));
                        break;
                    case RESULT_CANCELED:
                        binding.editText3.setText("Result: Canceled");
                        break;
                    default:
                        binding.editText3.setText("Result: Unknown(" + result.getResultCode() + ")");
                        break;
                }
            }
    );
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

        binding.buttonActive.setOnClickListener(view -> {
            var intent = new Intent(this,SubActivity.class);
            intent.putExtra("text",binding.editText.getText().toString());
            startActivity(intent);

        });
    }
}