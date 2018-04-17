import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.Closeable;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther Mxy 80103005
 * @date : Creat in 2018/3/29 10:37
 */
public class HTTPClientTest {

    @Test
    public void doGet() throws Exception {
        //创建HTTPClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建get对象
        HttpGet get = new HttpGet("http://www.sougou.com");
        //执行请求
        CloseableHttpResponse response = httpClient.execute(get);
        //获取结果
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println(statusCode);
        HttpEntity entity = response.getEntity();
        String string = EntityUtils.toString(entity,"utf-8");
        System.out.println(string);
        //关闭HTTPClient
        response.close();
        httpClient.close();
    }

    @Test
    public void doGetWithParam() throws Exception {
        CloseableHttpClient client = HttpClients.createDefault();
        URIBuilder builder = new URIBuilder("https://www.sogou.com/sogou");
        builder.addParameter("query","map");
        HttpGet get = new HttpGet(builder.build());
        CloseableHttpResponse response = client.execute(get);
        //获取结果
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println(statusCode);
        HttpEntity entity = response.getEntity();
        String string = EntityUtils.toString(entity,"utf-8");
        System.out.println(string);
        //关闭HTTPClient
        response.close();
        client.close();
    }

    @Test
    public void doPost() throws Exception {
        //创建HTTPClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建post对象
        HttpPost post = new HttpPost("http://localhost:8082/httpclient/post.html");
        //执行请求
        CloseableHttpResponse response = httpClient.execute(post);
        //获取结果
        HttpEntity entity = response.getEntity();
        String string = EntityUtils.toString(entity,"utf-8");
        System.out.println(string);
        //关闭HTTPClient
        response.close();
        httpClient.close();
    }

    @Test
    public void doPostWithParam() throws Exception {
        //创建HTTPClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建post对象
        HttpPost post = new HttpPost("http://localhost:8082/httpclient/post.action");
        //创建一个Entity，模拟一个表单
        List<NameValuePair> kvList = new ArrayList<>();
        kvList.add(new BasicNameValuePair("username", "麦序"));
        kvList.add(new BasicNameValuePair("pwd", "525"));
        //包装成Entity对象
        StringEntity entity = new UrlEncodedFormEntity(kvList,"utf-8");
        //设置请求的内容
        post.setEntity(entity);
        //执行请求
        CloseableHttpResponse response = httpClient.execute(post);
        //获取结果
        String string = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(string);
        //关闭HTTPClient
        response.close();
        httpClient.close();
    }
}
