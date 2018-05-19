package cn.sdcit.search.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sdcit.pojo.SearchProduct;
import cn.sdcit.pojo.SearchResult;
import cn.sdcit.search.dao.SearchDao;
import cn.sdcit.search.mapper.ProductMapper;
import cn.sdcit.search.service.SearchProductService;
import cn.sdcit.utils.DreamResult;

@Service
public class SearchProductServiceImpl implements SearchProductService {
	@Autowired
	private SolrServer solrServer;
	
	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private SearchDao searchDao;
	
	public DreamResult importProduct() throws SolrServerException, IOException{
		List<SearchProduct> productList = productMapper.getProductList();
		for (SearchProduct searchproduct : productList) {
			SolrInputDocument document = new SolrInputDocument();
			document.addField("id", searchproduct.getId());
			document.addField("product_title",searchproduct.getTitle());
			document.addField("product_sell_point", searchproduct.getSell_point());
			document.addField("product_price", searchproduct.getPrice());
			document.addField("product_image", searchproduct.getImage());
			document.addField("product_category_name", searchproduct.getCategory_name());
			solrServer.add(document);
		}
		solrServer.commit();
		return DreamResult.ok();
	}
	public SearchResult search(String keyword,int page,int rows) throws Exception{
		SolrQuery query = new SolrQuery();
		
		//设置搜索和默认搜索
		query.setQuery(keyword);
		query.set("df", "product_keywords");
		
		//分页
		int start =  (page-1)*rows;
		query.setStart(start);
		query.setRows(rows);
		
		//高亮
		query.setHighlight(true);
		query.addHighlightField("product_title");
		query.setHighlightSimplePre("<em style=\"color:red\">");
		query.setHighlightSimplePost("</em>");

		//设置总页数
		SearchResult search = searchDao.search(query);
		int recourdCount = search.getRecourdCount();
		int totalPages = recourdCount/rows;
		if(totalPages%rows != 0 )totalPages = totalPages +1;
		
		search.setTotalPages(totalPages);
		
		return search;
	}
	public void addDocument(long productId) throws SolrServerException, IOException{
		
		
		SearchProduct searchproduct = productMapper.getProductById(productId);
		SolrInputDocument document = new SolrInputDocument();
		document.addField("id", searchproduct.getId());
		document.addField("product_title",searchproduct.getTitle());
		document.addField("product_sell_point", searchproduct.getSell_point());
		document.addField("product_price", searchproduct.getPrice());
		document.addField("product_image", searchproduct.getImage());
		document.addField("product_category_name", searchproduct.getCategory_name());
		solrServer.add(document);
		solrServer.commit();
		
	}

}
