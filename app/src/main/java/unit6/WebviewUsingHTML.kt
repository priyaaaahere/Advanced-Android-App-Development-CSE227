package unit6

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cse227etp.R

class WebviewUsingHTML : AppCompatActivity() {
    lateinit var webView: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_webview_using_html)

        webView = findViewById(R.id.webView)
        val webviewSettings=webView.settings
        webviewSettings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()

        webView.loadUrl("file:///android_asset/index.html")
    }
}