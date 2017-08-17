package com.gharib.controller;

import com.gharib.service.UrlCounter;
import com.gharib.service.Domain;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.net.URISyntaxException;
import java.util.Map;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class welcomeController {
        UrlCounter urlCounter = null;
        
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String get(Model model) throws IOException, URISyntaxException {
            Domain url=new Domain();
            try {
                urlCounter = new UrlCounter(url.getDomain());
                
            } catch (URISyntaxException ex) {
                Logger.getLogger(welcomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            urlCounter.countDomains();   
            Map<String, Integer> founds = urlCounter.getDomainCount();
            model.addAttribute( "contactForm",  founds);
            model.addAttribute("domain",url);
            return "exampel";
		
	}
        @RequestMapping(value="/check", method = RequestMethod.GET)
        public String editPerson(@ModelAttribute("domain") Domain url, Model model) throws IOException, URISyntaxException{
          try {
                urlCounter = new UrlCounter(url.getDomain());
                
            } catch (URISyntaxException ex) {
                Logger.getLogger(welcomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            urlCounter.countDomains();
            final Map<String, Integer> founds = urlCounter.getDomainCount();
            model.addAttribute("contactForm", founds);
            model.addAttribute("domain", url);
            return "exampel";
         }
       
       
       

}
