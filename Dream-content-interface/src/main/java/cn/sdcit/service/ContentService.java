package cn.sdcit.service;

import java.util.List;

import cn.sdcit.utils.DreamResult;
import cn.sdcit.utils.EasyUITreeNode;

public interface ContentService {
	public List<EasyUITreeNode> findContentCategory(Long parentId);
	
}
