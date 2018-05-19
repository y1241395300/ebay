package cn.sdcit.service;

import java.util.List;

import cn.sdcit.utils.EasyUITreeNode;

public interface ItemCatService {
	public List<EasyUITreeNode> findTbItemCatList(long parentId);
}
