package cn.sdcit.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.support.json.JSONUtils;

import cn.sdcit.entity.DreamProductCat;
import cn.sdcit.entity.DreamProductCatExample;
import cn.sdcit.entity.DreamProductCatExample.Criteria;
import cn.sdcit.mapper.DreamProductCatMapper;
import cn.sdcit.reids.JedisClientPool;
import cn.sdcit.service.ContentService;
import cn.sdcit.utils.DreamResult;
import cn.sdcit.utils.EasyUITreeNode;
import cn.sdcit.utils.JsonUtils;
@Service
public class ContentServiceImpl implements ContentService {
	@Autowired
	private DreamProductCatMapper dreamPruductCatMapper;
	@Autowired
	private JedisClientPool jedisClientPool;
	public List<EasyUITreeNode> findContentCategory(Long parentId) {
		String json = jedisClientPool.get(parentId+"");
		if(json!= null){//判断redis是否有缓存，如果有直接从redis读入如果没有查询数据库并吧数据存入redis
			 List<EasyUITreeNode> jsonToList = JsonUtils.jsonToList(json, EasyUITreeNode.class);
			return jsonToList;
		}
		DreamProductCatExample example = new DreamProductCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<DreamProductCat> selectByExample = dreamPruductCatMapper.selectByExample(example);
		List<EasyUITreeNode> list = new ArrayList<>();
		for(DreamProductCat cat : selectByExample){//将查询到的类目存到list
			EasyUITreeNode node =new EasyUITreeNode();
			node.setId(cat.getId());
			node.setState(cat.getIsLeaf()?"false" : "true");//false 无下级 目录true 还有下级目录
			node.setText(cat.getName());
			list.add(node);
	
		}
		jedisClientPool.set(parentId+"", JsonUtils.objectToJson(list));
		return list;
	}

}
