package jp.co.meijou.android.s221205105;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.Optional;

import jp.co.meijou.android.s221205105.databinding.ActivityNetworkBinding;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkActivity extends AppCompatActivity {

    private final OkHttpClient okHttpClient = new OkHttpClient();

    private final Moshi moshi = new Moshi.Builder().build();

    private final JsonAdapter<Gist> gistJsonAdapter = moshi.adapter(Gist.class);

    private ActivityNetworkBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNetworkBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //var request = new Request.Builder() //アクセス先
          //      .url("https://placehold.jp/350x350.png")
          //      .build();

        binding.button.setOnClickListener(view -> {
           var text = binding.editText.getText() .toString();
            var url = Uri.parse("https://placehold.jp/500x500.png")
                    .buildUpon()
                    .appendQueryParameter("text", text)
                    .build()
                    .toString();
            // 画像の取得開始
            getImage(url);
        });
        getImage("https://placehold.jp/500x500.png");

    }
    private void getImage(String url) {
        // リクエスト先に画像URLを指定
        var request = new Request.Builder()
                .url(url)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                // 通信に失敗した時に呼ばれる
            }

            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException{
                //通信に成功したとき呼び出し
                var bitmap = BitmapFactory.decodeStream(response.body().byteStream());

                runOnUiThread(() -> binding.imageView.setImageBitmap(bitmap));
            }
        });
    }
}