package com.example.astroweather1.weather;

import android.os.Build;

import androidx.annotation.RequiresApi;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

import net.oauth.*;


public class WeatherRequestSender {
    final static String appId = "F1wQmT7k";
    final static String consumerKey = "dj0yJmk9WVR3Z0tQbmFudnlDJmQ9WVdrOVJqRjNVVzFVTjJzbWNHbzlNQS0tJnM9Y29uc3VtZXJzZWNyZXQmc3Y9MCZ4PWI3";
    final static String consumerSecret = "8962ec20db5d726bab8dc2cff9bb089db28ba42f";
    final static String baseUrl = "https://weather-ydn-yql.media.yahoo.com/forecastrss";

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String send() throws UnsupportedEncodingException, IOException, InterruptedException, OAuthException, URISyntaxException {
        /*long timestamp = new Date().getTime() / 1000;
        byte[] nonce = new byte[32];
        Random rand = new Random();
        rand.nextBytes(nonce);
        String oauthNonce = new String(nonce).replaceAll("\\W", "");

        List<String> parameters = new ArrayList<>();
        parameters.add("oauth_consumer_key=" + consumerKey);
        parameters.add("oauth_nonce=" + oauthNonce);
        parameters.add("oauth_signature_method=HMAC-SHA1");
        parameters.add("oauth_timestamp=" + timestamp);
        parameters.add("oauth_version=1.0");
        // Make sure value is encoded
        parameters.add("location=" + URLEncoder.encode("lodz,pl", "UTF-8"));
        parameters.add("format=json");
        Collections.sort(parameters);

        StringBuffer parametersList = new StringBuffer();
        for (int i = 0; i < parameters.size(); i++) {
            parametersList.append(((i > 0) ? "&" : "") + parameters.get(i));
        }

        String signatureString = "GET&" +
                URLEncoder.encode(url, "UTF-8") + "&" +
                URLEncoder.encode(parametersList.toString(), "UTF-8");

        String signature = null;
        try {
            SecretKeySpec signingKey = new SecretKeySpec((consumerSecret + "&").getBytes(), "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);
            byte[] rawHMAC = mac.doFinal(signatureString.getBytes());
            Base64.Encoder encoder = Base64.getEncoder();
            signature = encoder.encodeToString(rawHMAC);
        } catch (Exception e) {
            System.err.println("Unable to append signature");
            System.exit(0);
        }

        String authorizationLine = "OAuth " +
                "oauth_consumer_key=\"" + consumerKey + "\", " +
                "oauth_nonce=\"" + oauthNonce + "\", " +
                "oauth_timestamp=\"" + timestamp + "\", " +
                "oauth_signature_method=\"HMAC-SHA1\", " +
                "oauth_signature=\"" + signature + "\", " +
                "oauth_version=\"1.0\"";*/

        //Map<String, String> headers = new HashMap<>();



        OAuthConsumer consumer = new OAuthConsumer(null, consumerKey, consumerSecret, null);
        consumer.setProperty(OAuth.OAUTH_SIGNATURE_METHOD, OAuth.HMAC_SHA1);
        OAuthAccessor accessor = new OAuthAccessor(consumer);
        String authorization;
        try {
            OAuthMessage request = accessor.newRequestMessage(OAuthMessage.GET, baseUrl + "?location="+"lodz"+"&u=c"+"&format=json", null);
            authorization = request.getAuthorizationHeader(null);
            //headers.put("Authorization", authorization);
        } catch (OAuthException  |IOException | URISyntaxException e) {
            throw e;
            //throw new AuthFailureError(e.getMessage());
        }

        HttpClient httpClient = new DefaultHttpClient();
        //HttpResponse response = httpClient.execute(new HttpGet("https://weather-ydn-yql.media.yahoo.com/forecastrss?location=lodz,pl&format=json"));
        //HttpResponse response = httpClient.execute(new HttpGet("https://weather-ydn-yql.media.yahoo.com/forecastrss"));
        HttpGet request = new HttpGet("https://weather-ydn-yql.media.yahoo.com/forecastrss");
        request.addHeader("Authorization",authorization);
        request.addHeader("X-Yahoo-App-Id", appId);
        request.addHeader("Content-Type", "application/json");
        HttpResponse response = httpClient.execute(request);
        StatusLine statusLine = response.getStatusLine();
        if(statusLine.getStatusCode() == HttpStatus.SC_OK){
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            response.getEntity().writeTo(out);
            String responseString = out.toString();
            out.close();
            return responseString;
            //..more logic
        } else{
            //Closes the connection.
            response.getEntity().getContent().close();
            throw new IOException(statusLine.getReasonPhrase());
        }

        //CloseableHttpClient client = HttpClients.createDefault();

        //HttpClient client = HttpClient.newHttpClient();
        //HttpRequest request = HttpRequest.newBuilder()
        //        .uri(URI.create(url + "?location=lodz,pl&format=json"))
        //        .header("Authorization", authorizationLine)
        //        .header("X-Yahoo-App-Id", appId)
        //        .header("Content-Type", "application/json")
        //        .build();

        //return client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
