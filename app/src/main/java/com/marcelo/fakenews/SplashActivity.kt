package com.marcelo.fakenews

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import org.jsoup.nodes.Document
import java.io.IOException

class SplashActivity : AppCompatActivity() {
    private var loader: AsyncTask<Void, Void, ArrayList<New>>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loader = LoadInitNews(this)
        loader!!.execute()
    }

    internal class LoadInitNews(var activity: AppCompatActivity): AsyncTask<Void, Void, ArrayList<New>>(){

        private val news: ArrayList<New> = ArrayList()

        override fun doInBackground(vararg params: Void?): ArrayList<New> {
            try {
                val url = "https://antigo.saude.gov.br/fakenews?limitstart=0"
                val doc: Document = Jsoup.connect(url).get()

                //get images
                val div: Elements = doc.select("div.tileImage")

                //get title
                val tagHeading: Elements = doc.select("h2.tileHeadline")

                val size: Int = div.size

                for (index : Int in 0..size){
                    //get image link inside tag "img" with attribute src
                    val imgUrl: String = div.select("img").eq(index).attr("src")

                    //get text title inside tag a
                    val title: String = tagHeading.select("a").eq(index).text()

                    //get details news link inside tag "a" with attibute "href"
                    val details: String = tagHeading.select("a").attr("href")

                    Log.i("Result", imgUrl + " " + title + " " + details )
                    news.add(New("https://antigo.saude.gov.br"+imgUrl, title, details))
                }

            } catch (e: IOException){
                e.printStackTrace()
            }

            return news
        }

    }
}