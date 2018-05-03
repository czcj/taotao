package taotaoUtil;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class HttpClientUtil {
    //httpClient执行get请求
    public String doGet(String url) throws IOException {
        //1,创建一个httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //    2，创建一个get对象
        HttpGet get = new HttpGet(url);
        //    执行请求
        CloseableHttpResponse response = httpClient.execute(get);
        //取出响应结果
        int statusCode = response.getStatusLine().getStatusCode();
        HttpEntity entity = response.getEntity();
        String string = EntityUtils.toString(entity, "utf-8");
        response.close();
        httpClient.close();
        return string;
    }
   //httpClient执行带参数的get请求
    public String doGetWithParam(String url) throws IOException, URISyntaxException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String[] urlArr = url.split("/?");
        URIBuilder uriBuilder = new URIBuilder(urlArr[0]);
        String[] paramsArr = urlArr[1].split("&");
        for(String paramDemo : paramsArr){
            String[] kvStr = paramDemo.split("=");
            uriBuilder.addParameter(kvStr[0],kvStr[1]);
        }
        HttpGet get = new HttpGet(uriBuilder.build());
        CloseableHttpResponse response = httpClient.execute(get);
        int statusCode = response.getStatusLine().getStatusCode();
        HttpEntity entity = response.getEntity();
        String string = EntityUtils.toString(entity, "utf-8");
        response.close();
        httpClient.close();
        return string;
    }
    //httpClient执行post请求
    public void dopost()throws Exception{
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("www.baidu.com");
        CloseableHttpResponse response = httpClient.execute(httpPost);
        int statusCode = response.getStatusLine().getStatusCode();
        HttpEntity entity = response.getEntity();
        EntityUtils.toString(entity,"utf-8");
        response.close();
        httpClient.close();
    }
    //httpClient执行post请求带参数
    public void doPostWithParam()throws Exception{
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost();
        List<NameValuePair> kvList = new ArrayList<>();
        kvList.add(new BasicNameValuePair("username","zhangsan"));
        kvList.add(new BasicNameValuePair("password","123123"));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(kvList, "utf-8");
        httpPost.setEntity(entity);
        CloseableHttpResponse response = httpClient.execute(httpPost);
        int statusCode = response.getStatusLine().getStatusCode();
        String string = EntityUtils.toString(response.getEntity());
        response.close();
        httpClient.close();
    }
}
