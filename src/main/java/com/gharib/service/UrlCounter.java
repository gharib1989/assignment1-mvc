package com.gharib.service;


import java.io.BufferedReader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class UrlCounter{
    private final String htmlPageUrl;
    private final String pageDomain;
    private Map<String, Integer> domainCount;
   
    public UrlCounter(String htmlPageUrl) throws URISyntaxException{
        this.htmlPageUrl = htmlPageUrl;
        this.pageDomain = stripDomain(htmlPageUrl);
    }
    
    public void countDomains() throws IOException, URISyntaxException {
        this.domainCount = new HashMap<>();

        final Elements linkElements = findLinkElements();

        countDomains(linkElements);
    }

    private Elements findLinkElements() throws IOException {
        final  URL url = new URL(htmlPageUrl);
        final URLConnection spoof = url.openConnection();  
        spoof.setRequestProperty( "User-Agent", "Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0; H010818)" );
        final BufferedReader in = new BufferedReader(new InputStreamReader(spoof.getInputStream()));
        String strLine = "";
        String pageSource ="";
        while ((strLine = in.readLine()) != null){
            pageSource+=strLine ;
        }
        
        final Document allElements = Jsoup.parse(pageSource);
        return allElements.getElementsByTag("a");
    }
    
    
    private String stripDomain(String fullUrl) throws URISyntaxException {
        final String domainWithWww = (new URI(fullUrl)).getHost();
        return domainWithWww.startsWith("www.") ? domainWithWww.substring(4) : domainWithWww;
    }
    
    private void countDomains(final Elements linkElements) throws URISyntaxException {
        for (final Element element : linkElements) {
            final String href = element.attr("href").toLowerCase();

            if (href.startsWith("http")) {
                final String domainName = stripDomain(href);
                addNewDomainOrIncreaseCount(domainName);
            } else if (!href.startsWith("#") || !href.isEmpty() ) {
                addNewDomainOrIncreaseCount(pageDomain);
            }
        }
    }
    
    private void addNewDomainOrIncreaseCount(final String domainName) {
        if (domainCount.containsKey(domainName)) {
            final int oldCount = domainCount.get(domainName);
            domainCount.put(domainName, oldCount + 1);
        } else {
            domainCount.put(domainName, 1);
        }
    }
    
    public Map<String, Integer> getDomainCount() {
        return domainCount;
    }
    
 
 
} 