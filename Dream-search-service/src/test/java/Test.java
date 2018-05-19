import java.io.IOException;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;

public class Test {
	public void addDucument() throws Exception{
		SolrServer solrServer =new HttpSolrServer("http://10.83.0.101:8983");
		SolrInputDocument document=new SolrInputDocument();
		document.addField("id", "test1");
		document.addField("product_title", "测试商品");
		document.addField("product_price", "999");
		solrServer.add(document);
		solrServer.commit();
	}
public static void main(String[] args) throws Exception {
	Test test=new Test();
		test.addDucument();
}	
}
