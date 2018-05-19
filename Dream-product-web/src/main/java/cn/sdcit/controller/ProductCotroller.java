package cn.sdcit.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.sdcit.entity.DreamProduct;
import cn.sdcit.service.ProductService;
import cn.sdcit.utils.DreamResult;
@Controller
public class ProductCotroller {
	@Autowired
	private ProductService  productService;
	@RequestMapping("/createproduct")
	@ResponseBody
	public DreamResult createProduct(DreamProduct dreamproduct,String data){	//创建商品
			return productService.createProduct(dreamproduct, data);	
	}
	
	@RequestMapping("/updateproduct")
	@ResponseBody
	public DreamResult updateProduct(long id,DreamProduct dreamproduct,String data){	//更新商品
			return productService.updateProduct(id, dreamproduct, data);
	}
	@RequestMapping("/deleteproduct")
	@ResponseBody
	public DreamResult deleteProduct(long id){	//删除商品
		return productService.deleteProduct(id);
	}
	@RequestMapping("/saveproduct")
	@ResponseBody
	public DreamResult savaProduct(long id){	//商品上架
		return productService.saveProduct(id);
	}
	@RequestMapping("/outproduct")
	@ResponseBody
	public DreamResult outproduct(long id){		//商品下架
		return productService.outProduct(id);
	}
	@RequestMapping("/findproduct")
	@ResponseBody
	public DreamResult findProduct(long id){
		return productService.findProduct(id); //查询商品信息
	}
}
