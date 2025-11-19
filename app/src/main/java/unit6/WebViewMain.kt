package unit6

import android.graphics.Bitmap
import android.health.connect.datatypes.units.Length
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cse227etp.R

class WebViewMain : AppCompatActivity() {


    //FOR THIS APPLICATION TO WORK IT'S IMPORTANT TO KEEP INTERNET PERMISSION IN ANDROID MANIFEST FILE
    private lateinit var webView: WebView
    private lateinit var progressBar: ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_web_view_main)

        val webView=findViewById<WebView>(R.id.webView)
        val progressBar=findViewById<ProgressBar>(R.id.progressBar)

        val webSettings=webView.settings
        webSettings.javaScriptEnabled=true
        webSettings.domStorageEnabled=true
        webSettings.loadsImagesAutomatically=true
        webSettings.mixedContentMode= WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        webSettings.builtInZoomControls=false
        webSettings.displayZoomControls=true

        webView.loadUrl("https://www.wikipedia.org")

        webView.webViewClient=object: WebViewClient(){
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                progressBar.visibility=View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                progressBar.visibility=View.GONE
                Toast.makeText(this@WebViewMain, "Page Loaded", Toast.LENGTH_LONG).show()
            }
            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?){
                Toast.makeText(this@WebViewMain, "Error! $error", Toast.LENGTH_LONG).show()
            }
        }

        webView.webChromeClient=object: WebChromeClient(){
            override fun onReceivedTitle(view: WebView?, title: String?) {
                supportActionBar?.title=title
            }
        }

        fun onBackPressed() {
            if(webView.canGoBack()){
                webView.goBack()
            }
            else{
                super.onBackPressed()
            }
        }
    }
}