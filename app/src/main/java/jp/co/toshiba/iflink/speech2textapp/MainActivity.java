package jp.co.toshiba.iflink.speech2textapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.os.Bundle;

import android.Manifest;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    // パーミッションリクエストのリクエストコード
    private static final int REQUEST_CODE_PERMISSIONS = 100;
    // 必要なーミッションの配列
    private final String[] PERMISSIONS = {
            Manifest.permission.RECORD_AUDIO
    };
    EditText edt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (checkPermissions()) {
            // 権限が許可されている場合の処理を実行
            // ...
        } else {
            // 権限が許可されていない場合は、権限リクエストを行う
            requestPermissions();
        }
        edt = findViewById(R.id.edt);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = edt.getText().toString();
                Text2Speech text2Speech = new Text2Speech(getApplicationContext());
                text2Speech.speak(text);
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 許可された場合の処理
            } else {
                // 拒否された場合の処理
            }
        }
    }


    // パーミッションが許可されているかどうかを確認
    private boolean checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                        != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    PERMISSIONS,
                    REQUEST_CODE_PERMISSIONS);
        }
        for (String permission : PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(this, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }
    // パーミッションリクエストの呼び出し
    private void requestPermissions() {
        ActivityCompat.requestPermissions(this, PERMISSIONS, REQUEST_CODE_PERMISSIONS);
    }
}