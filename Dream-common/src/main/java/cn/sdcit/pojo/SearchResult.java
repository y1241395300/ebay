package cn.sdcit.pojo;

import java.io.Serializable;
import java.util.List;

public class SearchResult implements Serializable {
	private int totalPages;
	private int recourdCount;
	private List<SearchProduct> ProductList;

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getRecourdCount() {
		return recourdCount;
	}

	public void setRecourdCount(int recourdCount) {
		this.recourdCount = recourdCount;
	}

	public List<SearchProduct> getProductList() {
		return ProductList;
	}

	public void setProductList(List<SearchProduct> ProductList) {
		this.ProductList = ProductList;
	}

}
