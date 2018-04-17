import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import java.io.IOException;

/**
 * @Auther : mengxiaoyang@crscd.com.cn
 * @Description :
 * @Date : Creat in 2018/4/16 16:57
 * @Modified By :
 */
public class SolrJTest {

    @Test
    public void addDoc() throws IOException, SolrServerException {
        //创建连接
        SolrServer solrServer = new HttpSolrServer("http://localhost:8080/solr/taotao");
        //创建文档对象
        SolrInputDocument document = new SolrInputDocument();
        //把文档对象写入索引库
        document.addField("id","test1");
        document.addField("item_title","MXY");
        document.addField("item_price","66666");
        solrServer.add(document);
        //提交
        solrServer.commit();
    }

    @Test
    public void delDoc() throws IOException, SolrServerException {
        SolrServer solrServer = new HttpSolrServer("http://localhost:8080/solr/taotao");
        solrServer.deleteByQuery("*:*");
        solrServer.commit();
    }
}
