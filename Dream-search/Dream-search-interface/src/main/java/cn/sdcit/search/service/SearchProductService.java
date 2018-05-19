package cn.sdcit.search.service;

import java.io.IOException;
import cn.sdcit.pojo.SearchResult;
import cn.sdcit.utils.DreamResult;
public interface SearchProductService {

	public DreamResult importProduct() throws Exception;
	public SearchResult search(String keyword,int page,int rows) throws Exception;
	public void addDocument(long ProductId) throws Exception;
}
