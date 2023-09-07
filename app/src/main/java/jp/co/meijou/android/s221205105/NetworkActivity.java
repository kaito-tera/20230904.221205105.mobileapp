package jp.co.meijou.android.s221205105;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

        var request = new Request.Builder() //アクセス先
                .url("https://gist.stoic.jp/okhttp.json")
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                // 通信に失敗した時に呼ばれる
            }

            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException{
                //通信に成功したとき呼び出し
                var gist = gistJsonAdapter.fromJson(response.body().source());

                //中身の取りだし
                Optional.ofNullable(gist)
                        .map(g -> g.files.get("OkHttp.txt")) //filesの中からOkHttp.txtを取り出し
                        .ifPresent(gistFile -> {
                            //UIスレッド上で実行　ほかで更新するとクラッシュ
                            runOnUiThread(() -> binding.textcontent.setText(gistFile.content));
                        });
            }
        });
    }
}