package com.example.bluegate.activity;

import android.util.Log;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class CustomWebSocketListener extends WebSocketListener {
    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        super.onOpen(webSocket, response);
        Log.d("TLOG", "전송 데이터 확인 : " + webSocket + " : " + response);
        webSocket.close(1000, null);
        // WebSocket 연결이 열릴 때의 처리
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        super.onMessage(webSocket, text);
        // 서버로부터 텍스트 메시지를 받을 때의 처리
    }

    @Override
    public void onMessage(WebSocket webSocket, ByteString bytes) {
        super.onMessage(webSocket, bytes);
        // 서버로부터 바이너리 메시지를 받을 때의 처리
    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        super.onClosing(webSocket, code, reason);
        // WebSocket 연결이 닫힐 때의 처리
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        super.onFailure(webSocket, t, response);
        Log.d("TLOG","소켓 onFailure : " + t.toString()); // WebSocket에 에러가 발생할 때의 처리
    }
}
