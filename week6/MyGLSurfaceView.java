package com.example.min_hokyung.myapplication;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class MyGLSurfaceView extends GLSurfaceView {

    private final MyGLRenderer mRenderer;

    public MyGLSurfaceView(Context context) {
        super(context);

        setEGLContextClientVersion(2);

        mRenderer = new MyGLRenderer();
        setRenderer(mRenderer);

        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    public Messenger getAngleReceiver() { return mAngleReceiver; }

    // 1. define and create a handler instance.
    // 2. create a messenger instance with the handler (mAngleReceiver).

}
