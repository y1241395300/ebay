package cn.sdcit.search.mapper;

import java.util.List;

import cn.sdcit.pojo.SearchProduct;





public interface ProductMapper {
	 public List<SearchProduct> getProductList();
	 public SearchProduct getProductById(long itemId);
}
