package com.example.bluegate.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.bluegate.R;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

public class MainActivity extends AppCompatActivity {
    ImageButton register_btn; //펫 추가 버튼
    Switch toggle; //문 제어 토글
    TextView door_status;

    private OkHttpClient client;
    private WebSocket webSocket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //펫 추가 버튼
        register_btn = (ImageButton) findViewById(R.id.plus_button);
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
            }
        });

        //문 토글
        toggle = (Switch) findViewById(R.id.toggleButton);
        door_status = (TextView) findViewById(R.id.door_status);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isOpen) {
                if (isOpen) {
                    door_status.setText("Opened");
                    // WebSocket 연결 및 통신 시작
                    connectWebSocket("ws://localhost:8080/faceRecognition");
                } else {
                    door_status.setText("Closed");
                    // WebSocket 연결 종료
                    if (webSocket != null) {
                        webSocket.close(1000, "App closed");
                    } //이거를 지울까?
                }
            }
        });

        client = new OkHttpClient();
    }

    private void connectWebSocket(String url) {
        Request request = new Request.Builder().url(url).build();
        CustomWebSocketListener listener = new CustomWebSocketListener();
        webSocket = client.newWebSocket(request, listener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webSocket != null) {
            webSocket.close(1000, "App closed");
        }
    }
}
