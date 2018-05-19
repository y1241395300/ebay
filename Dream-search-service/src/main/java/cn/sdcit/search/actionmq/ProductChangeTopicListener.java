package cn.sdcit.search.actionmq;

import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import cn.sdcit.search.mapper.ProductMapper;
import cn.sdcit.search.service.SearchProductService;

public class ProductChangeTopicListener implements MessageListener {
	@Autowired
	private SearchProductService searchItemService ;
	
	@Override
	public void onMessage(Message message) {
		try {
			TextMessage textMessage = (TextMessage)message;
			String itemId = textMessage.getText();
			searchItemService.addDocument(new Long(itemId));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
