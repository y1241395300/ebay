package cn.sdcit.service;

import java.util.Date;
import java.util.List;

import cn.sdcit.entity.DreamProduct;
import cn.sdcit.utils.DreamResult;

public interface ProductService {
	public DreamResult createProduct(DreamProduct dreamproduct,String data);			//创建商品
	public DreamResult updateProduct(long id,DreamProduct dreamproduct,String data);	//更新商品信息
	public DreamResult deleteProduct(long id);											//删除商品
	public DreamResult saveProduct(long id);											//商品上架
	public DreamResult outProduct(long id);												//商品下架	
	public DreamResult findProduct(long id);	
	}

