package org.jwt;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class CustomController {
	@GetMapping("/test")
	public String SendReq() throws ClientProtocolException, IOException {
		System.out.println("TEST BEGIN");
		HttpGet request = new HttpGet("http//localhost:8081/lol");
		CloseableHttpClient httpClient = HttpClients.createDefault();
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            System.out.println(response);
            HttpEntity entity = response.getEntity();
            Header headers = entity.getContentType();
            System.out.println(headers);
            if (entity != null) {
                
                String result = EntityUtils.toString(entity);
                System.out.println(result);
            }

        }catch(Exception exc) {
        	System.out.println(exc.getMessage());
        }
		return "SOMEDATA";
	}
}
