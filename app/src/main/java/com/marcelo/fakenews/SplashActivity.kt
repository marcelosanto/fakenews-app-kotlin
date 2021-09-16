package com.marcelo.fakenews

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    internal class LoadInitNwes(var activity: AppCompatActivity): AsyncTask<Void, Void, ArrayList<New>>(){
        override fun doInBackground(vararg params: Void?): ArrayList<New> {
            TODO("Not yet implemented")
        }

    }
}