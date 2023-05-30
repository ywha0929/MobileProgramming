package com.example.animationservice;

import static android.os.Process.THREAD_PRIORITY_BACKGROUND;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.renderscript.Sampler;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;

import androidx.annotation.Nullable;

import java.util.concurrent.atomic.AtomicBoolean;


public class AnimationIntentService extends Service {
    final String TAG = "AnimationIntentService";
    private ValueAnimator ServiceAnimator;
    private Looper serviceLooper;
    private ServiceHandler serviceHandler;
    AtomicBoolean isPlay = new AtomicBoolean();
    @SuppressLint("HandlerLeak")
    Messenger mMessenger;

    public AnimationIntentService() {
        isPlay.set(false);
        Log.d(TAG, "AnimationIntentService: created");
        HandlerThread thread = new HandlerThread("ServiceStartArguments",
                THREAD_PRIORITY_BACKGROUND);
        thread.start();

        // Get the HandlerThread's Looper and use it for our Handler
        serviceLooper = thread.getLooper();
        mMessenger = new Messenger(new ServiceHandler(serviceLooper));
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }



    private final class ServiceHandler extends Handler {
        public ServiceHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            // Normally we would do some work here, like download a file.
            // For our sample, we just sleep for 5 seconds.
            if(msg.arg1 == 1)
            {
                if(ServiceAnimator ==  null)
                {
                    ValueAnimator animator = ValueAnimator.ofFloat(0, 360);
                    animator.setDuration(2000);
                    animator.setInterpolator(new LinearInterpolator());
                    animator.setRepeatCount(5);
                    animator.setRepeatMode(ValueAnimator.RESTART);
                    Messenger messenger = (Messenger) msg.obj;
                    animator.addUpdateListener(
                            new ValueAnimator.AnimatorUpdateListener() {
                                @Override
                                public void onAnimationUpdate(ValueAnimator animation) {
                                    Log.d(TAG, "onAnimationUpdate: ");
                                    float value = (float) animation.getAnimatedValue();
                                    Message msg1 = Message.obtain();
                                    msg1.obj = Float.valueOf( value );
                                    msg1.arg1 = 1;
                                    try {
                                        messenger.send(msg1);
                                    } catch (RemoteException e) {
                                        throw new RuntimeException(e);
                                    }

                                }
                            });
                    ServiceAnimator = animator;
                    ServiceAnimator.start();

                }
                else
                    ServiceAnimator.resume();

            }
            else
            {
                ServiceAnimator.pause();
            }


        }
    }
}

//
//    @Override
//    protected void onHandleIntent(Intent intent) {
//        Messenger valMessenger = (Messenger) intent.getExtras().get("ValueMessenger");
//        startAngle = intent.getFloatExtra("StartValue",0);
//        endAngle   = intent.getFloatExtra("EndValue",0);
//        incAngle   = intent.getFloatExtra("IncValue",1);
//        stepTime   = intent.getFloatExtra("UpdateTime",0);
//        while(startAngle < endAngle) {
//            Message msg = Message.obtain();
//            msg.obj = new Float(startAngle);
//            msg.arg1 = 1;
//            try{
//                valMessenger.send(msg);
//                Thread.sleep((long)stepTime);
//            } catch(android.os.RemoteException ex) {
//                ex.printStackTrace();
//            } catch (InterruptedException ex) {
//                ex.printStackTrace();
//            }
//            startAngle +=incAngle;
//        }
//    }

