package com.example.demo.es;


import com.alibaba.fastjson.JSON;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientApp {

    //@Autowired
    //private RestHighLevelClient restHighLevelClient;  http://192.168.76.100:9200/
    RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.76.100", 9200, "http")));

    @Test
    public void testCreateIndex() throws IOException {
        //1、创建索引请求
        CreateIndexRequest request = new CreateIndexRequest("liao_index");
        //2、客户端执行请求
        CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);

        System.out.println(response);
    }

    /**
     * 测试获取索引，判断其是否存在
     */
    @Test
    public void testExistIndex() throws IOException {
        GetIndexRequest request = new GetIndexRequest("huang_index");
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);

        System.out.println(exists);
    }

    @Test
    public void testDeleteIndex() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest("liao_index");
        AcknowledgedResponse delete = client.indices().delete(request, RequestOptions.DEFAULT);

        System.out.println(delete.isAcknowledged());
    }

    @Test
    public void testAddDocument() throws IOException {
        User user = new User();
        user.setName("黄敏");

        //创建请求
        IndexRequest request = new IndexRequest("huang_index");
        //规则 put/huang_index/_doc/1
        request.id("1");
        request.timeout(TimeValue.timeValueSeconds(1));
        request.timeout("1s");

        //将我们的数据放入请求 json
        request.source(JSON.toJSONString(user));

        //客户端发送请求
        IndexResponse index = client.index(request, RequestOptions.DEFAULT);

        System.out.println(index.toString());
        System.out.println(index.status());
    }

    @Test
    public void testIsExists() throws IOException {
        GetRequest request = new GetRequest("huang_index");
        //不获取返回的_soure的上下文
        request.fetchSourceContext(new FetchSourceContext(false));
        request.storedFields("_none");
        boolean exists = client.exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    @Test
    public void testGetDocument() throws IOException {
        GetRequest request = new GetRequest("huang_index");
        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        System.out.println(response.getSourceAsString());
        System.out.println(response);
    }

}
