package cn.sdcit.search.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.sdcit.pojo.SearchProduct;
import cn.sdcit.pojo.SearchResult;


@Repository
public class SearchDao {
	@Autowired
	private SolrServer solrServer;
	
	public SearchResult search(SolrQuery query) throws SolrServerException{
		QueryResponse response = solrServer.query(query);
		SolrDocumentList results = response.getResults();
		long numFound = results.getNumFound();
		SearchResult searchResult = new SearchResult();
		//总商品数
		searchResult.setRecourdCount((int) numFound);
		List<SearchProduct> productlist = new ArrayList<SearchProduct>();
		
		Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
		for (SolrDocument solrDocument : results) {
			SearchProduct product = new SearchProduct();
			product.setId((String) solrDocument.get("id"));
			product.setCategory_name((String) solrDocument.get("product_category_name"));
			product.setImage((String) solrDocument.get("product_image"));
			product.setPrice((long) solrDocument.get("product_price"));
			product.setSell_point((String) solrDocument.get("product_sell_point"));
			String product_title = null;
			//取出高亮
			List<String> list = highlighting.get(solrDocument.get("id")).get("product_title");
			if( list != null  && list.size()>0){
				product_title = list.get(0);
			}else{
				product_title = (String) solrDocument.get("product_title");
			}
			product.setTitle(product_title);
			
			productlist.add(product);
		}
		searchResult.setProductList(productlist);
		return searchResult;
	}
}
