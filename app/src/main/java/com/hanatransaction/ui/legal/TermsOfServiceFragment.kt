package com.hanatransaction.ui.legal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.hanatransaction.R

/**
 * Fragment that displays the terms of service
 */
class TermsOfServiceFragment : Fragment() {
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_legal_document, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = getString(R.string.terms_of_service_title)
        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        
        val webView = view.findViewById<WebView>(R.id.webView)
        val termsContent = getString(R.string.terms_of_service_content)
        
        // Load the HTML content
        webView.loadDataWithBaseURL(
            null,
            wrapHtmlContent(termsContent),
            "text/html",
            "UTF-8",
            null
        )
    }
    
    /**
     * Wrap the content in proper HTML tags with styling
     */
    private fun wrapHtmlContent(content: String): String {
        return """
            <!DOCTYPE html>
            <html>
            <head>
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <style>
                    body {
                        font-family: 'Roboto', 'Helvetica', 'Arial', sans-serif;
                        line-height: 1.6;
                        padding: 16px;
                        color: #000000;
                        background-color: #ffffff;
                    }
                    h1 {
                        font-size: 24px;
                        font-weight: 500;
                        margin-bottom: 24px;
                    }
                    h2 {
                        font-size: 20px;
                        font-weight: 500;
                        margin-top: 24px;
                        margin-bottom: 16px;
                    }
                    p {
                        margin-bottom: 16px;
                    }
                    ul {
                        margin-bottom: 16px;
                        padding-left: 24px;
                    }
                    li {
                        margin-bottom: 8px;
                    }
                </style>
            </head>
            <body>
                $content
            </body>
            </html>
        """.trimIndent()
    }
} 