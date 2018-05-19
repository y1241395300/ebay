package cn.sdcit.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.sdcit.service.ContentService;
import cn.sdcit.utils.DreamResult;
import cn.sdcit.utils.EasyUITreeNode;
@Controller
public class ContentProductcontroller {
	@Autowired
	private ContentService contentService;
	@RequestMapping("/product/list")
	@ResponseBody
	public DreamResult product_list(long parentId){
		 List<EasyUITreeNode> productCats = contentService.findContentCategory(parentId);
		return DreamResult.ok(productCats);
		
	}

}
