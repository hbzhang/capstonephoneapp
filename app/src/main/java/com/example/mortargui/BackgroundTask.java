package com.example.mortargui;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import static android.provider.Settings.System.getString;

public class BackgroundTask extends AsyncTask<Void, Void, String> {

    OnDownloadUpdateListener listener;

    private Context mContext;


    public interface OnDownloadUpdateListener {
        public void OnDownloadDeckFinish(String Response);
    }


    public BackgroundTask(Context ctx, OnDownloadUpdateListener listener) {
        mContext = ctx;
        this.listener = listener;
    }

    @Override
    protected String doInBackground(Void... params) {
        String str = "downloading";
        //your async code goes here
        return str;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result) {
        if( listener != null ) {
            //listener.OnDownloadDeckProgress(result);
        }
    }
}