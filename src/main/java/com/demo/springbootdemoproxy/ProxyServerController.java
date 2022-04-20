package com.demo.springbootdemoproxy;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
public class ProxyServerController {
//    @Autowired


    @RequestMapping (value = "/**", method = {RequestMethod.GET, RequestMethod.POST})
    public String responseVal(HttpServletRequest request) throws IOException {
        WebServiceRestClientService webServiceRestClientService= new WebServiceRestClientService();
        System.out.println("Value : " + request.getRequestURI());

        String bodyStr= IOUtils.toString(request.getReader());
        String response = "";
        if ("GET".equals(request.getMethod())){
            response = webServiceRestClientService.getJsonService(getFullURL(request,"http://localhost:8081"));
        }else if ("POST".equals(request.getMethod())){
            response = webServiceRestClientService.createJsonService(request.getRequestURL().toString(),bodyStr);
        }
        return response;
    }
    public String getFullURL(HttpServletRequest request, String newURL) {

        String requestURLStr = request.getRequestURL().toString();
        String contextPath= request.getServletPath();
        int indexOfContextPath = requestURLStr.indexOf(contextPath);
        String domainPort=request.getRequestURL().toString().substring(0,indexOfContextPath);
        requestURLStr = requestURLStr.replaceFirst(domainPort,newURL);


        WebServiceRestClientService webServiceRestClientService= new WebServiceRestClientService();
        StringBuilder requestURL = new StringBuilder(requestURLStr);
        String queryString = request.getQueryString();
        if (queryString == null) {
            return requestURL.toString();
        } else {
            return requestURL.append('?').append(queryString).toString();
        }
    }

    public String getUrlWithoutScheme(String requestURL, String requestURI){
        /*
        StringBuffer url = requestURL;
        String uri = requestURI;
        int idx = (((uri != null) && (uri.length() > 0)) ? url.indexOf(uri) : url.length());
        String host = url.substring(0, idx); //base url
        idx = host.indexOf("://");
        if(idx > 0) {
            host = host.substring(idx); //remove scheme if present
        }
        */
        return null;
    }

}
