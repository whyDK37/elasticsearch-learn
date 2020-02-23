package me.es;

import java.io.IOException;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

public class EsDemo {

  public static void main(String[] args) throws IOException {
    RestHighLevelClient client = new RestHighLevelClient(
        RestClient.builder(new HttpHost("localhost", 9200, "http")));

    IndexRequest request = new IndexRequest("posts");
    request.id("1");
    String jsonString = "{" +
        "\"user\":\"kimchy\"," +
        "\"postDate\":\"2013-01-30\"," +
        "\"message\":\"trying out Elasticsearch\"" +
        "}";
    request.source(jsonString, XContentType.JSON);

    Map<String, Object> jsonMap = new HashMap<>();
    jsonMap.put("user", "kimchy");
    jsonMap.put("postDate", new Date());
    jsonMap.put("message", "trying out Elasticsearch");
    IndexRequest indexRequest = new IndexRequest("posts")
        .id("1").source(jsonMap);

    client.index(request, RequestOptions.DEFAULT);

    client.close();
  }
}
