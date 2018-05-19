package cn.sdcit.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.sdcit.pojo.SearchResult;
import cn.sdcit.search.service.SearchProductService;


@Controller
public class SearchController {
	@Autowired
	private SearchProductService searchProductService;
	
	@RequestMapping("/search")
	public String search(String keyword ,@RequestParam(defaultValue="1") Integer page,Model model) throws Exception{
		keyword = new String(keyword.getBytes("iso8859-1"),"utf-8");
		SearchResult search = searchProductService.search(keyword, page, 10);
		model.addAttribute("query", keyword);
		model.addAttribute("recourdCount", search.getRecourdCount());
		model.addAttribute("totalPages", search.getTotalPages());
		model.addAttribute("ProductList", search.getProductList());
		
		return "search";
	}
}
