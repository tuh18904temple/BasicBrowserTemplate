package edu.temple.basicbrowser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.ImageButton
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.net.URL
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    lateinit var urlEditText: EditText
    lateinit var goButton: ImageButton
    lateinit var webView: WebView

    lateinit var requestQueue: RequestQueue



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        urlEditText = findViewById(R.id.urlEditText)
        goButton = findViewById(R.id.goButton)
        webView = findViewById(R.id.webView)
        webView.settings.javaScriptEnabled = true

        val urlText = urlEditText.text.toString()




            goButton.setOnClickListener(){

                requestQueue = Volley.newRequestQueue(this)

                requestQueue.add(
                    StringRequest(Request.Method.GET,urlText){
                        webView.loadDataWithBaseURL("",it,"text/html","utf-8","")
                    }
                )

                webView.loadUrl(urlText)

            }



        // Allow your browser to intercept hyperlink clicks
        webView.webViewClient = object: WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
            }
        }


    }
}