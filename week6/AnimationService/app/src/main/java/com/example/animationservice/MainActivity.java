package com.example.animationservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Animation anim;
    boolean isFirst = false;
    Messenger mService = null;
    private ServiceConnection mConnection = new ServiceConnection() {

        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            mService = new Messenger(service);
        }
        public void onServiceDisconnected(ComponentName className) {
            mService = null;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnStart = findViewById(R.id.btnStart);
        Intent intent = new Intent(getApplicationContext(),AnimationIntentService.class);
        bindService(intent,mConnection,BIND_AUTO_CREATE);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("MainActivity", "onClick: ");
                Message msg = Message.obtain();

                MyGLSurfaceView glView = (MyGLSurfaceView)findViewById(R.id.glView);
                Messenger angleReceiver = glView.getAngleReceiver();
                msg.obj = angleReceiver;
                msg.arg1 = 1;
                try {
                    mService.send(msg);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
//                MyGLSurfaceView glView = (MyGLSurfaceView)findViewById(R.id.glView);
//                Animation thisAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim);
//                anim = thisAnim;
//                glView.startAnimation(thisAnim);
//                MyGLSurfaceView glView = (MyGLSurfaceView)findViewById(R.id.glView);
//                Message msg = Message.obtain();
//                msg.obj = Boolean.TRUE;
//
//                if(isFirst == false)
//                {
//                    isFirst = true;
//
//                    glView = (MyGLSurfaceView)findViewById(R.id.glView);
//                    Messenger angleReceiver = glView.getAngleReceiver();
//                    intent.putExtra("ValueMessenger",angleReceiver);
//                    intent.putExtra("StartValue",0.0f);
//                    intent.putExtra("EndValue",360f);
//                    intent.putExtra("IncValue",1.0f);
//                    intent.putExtra("UpdateTime",100.0f);
//                    startService(intent);
//                }




            }
        });
        Button btnStop = findViewById(R.id.btnStop);
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message msg = Message.obtain();
                msg.arg1 = 0;
                try {
                    mService.send(msg);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
//                MyGLSurfaceView glView = (MyGLSurfaceView)findViewById(R.id.glView);
//                Message msg = Message.obtain();
//                msg.obj = Boolean.FALSE;
//                try {
//                    glView.getPauseHandler().send(msg);
//                } catch (RemoteException e) {
//                    throw new RuntimeException(e);
//                }
            }
        });
    }
}