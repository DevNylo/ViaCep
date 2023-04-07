package br.com.viacep.viacep.services;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

import br.com.viacep.viacep.model.Address;

import java.io.IOException;

public class ViaCepService {
    
    public Address getAddress(String cep) throws ClientProtocolException, IOException{

        Address address = null;

        HttpGet request = new HttpGet("https://viacep.com.br/ws/"+cep+"/json/");
        
        try(CloseableHttpClient httpClient = HttpClientBuilder.create().disableRedirectHandling().build(); 
        
            CloseableHttpResponse response = httpClient.execute(request)) {
                
                HttpEntity entity = response.getEntity();

                if(entity != null){
                    String result = EntityUtils.toString(entity);

                    Gson gson = new Gson();

                    address = gson.fromJson(result, Address.class);
                }
        }
        return address;
    }
}
