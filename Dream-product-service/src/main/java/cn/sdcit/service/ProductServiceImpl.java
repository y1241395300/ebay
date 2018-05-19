package cn.sdcit.service;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.support.json.JSONUtils;

import cn.sdcit.entity.DreamProduct;
import cn.sdcit.entity.DreamProductDesc;
import cn.sdcit.entity.DreamProductDescExample;
import cn.sdcit.entity.DreamProductExample;
import cn.sdcit.entity.DreamProductExample.Criteria;
import cn.sdcit.mapper.DreamProductDescMapper;
import cn.sdcit.mapper.DreamProductMapper;
import cn.sdcit.reids.JedisClientPool;
import cn.sdcit.utils.DreamResult;
import cn.sdcit.utils.IDUtils;
import cn.sdcit.utils.JsonUtils;
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private JedisClientPool jedisClientPool;
	@Autowired
	private DreamProductMapper dreamProductMapper;
	@Autowired
	private DreamProductDescMapper descMapper;
	public DreamResult createProduct(DreamProduct dreamproduct,String data) {  //添加商品信息	
			IDUtils idutils=new IDUtils();
			long id=idutils.genItemId();
			dreamproduct.setId(id);
			dreamproduct.setCreated(new Date());
			dreamproduct.setUpdated(new Date());
			dreamproduct.setState(0);
			dreamProductMapper.insert(dreamproduct);
			DreamProductDesc desc =new DreamProductDesc();
			desc.setProId(id);
			desc.setCreated(new Date());
			desc.setUpdated(new Date());
			desc.setProDesc(data);
			descMapper.insert(desc);
			return DreamResult.ok(dreamproduct);
	}

	public DreamResult updateProduct(long id,DreamProduct dreamproduct,String data) { //修改商品信息
		DreamProduct selectByPrimaryKey = dreamProductMapper.selectByPrimaryKey(id);
		if(selectByPrimaryKey==null){
			return DreamResult.build(200, "没有这个商品");
		}
		else{
		DreamProductExample dreamProductExample = new DreamProductExample();
		Criteria createCriteria = dreamProductExample.createCriteria();
				createCriteria.andIdEqualTo(id);
				dreamproduct.setUpdated(new Date());
				dreamProductMapper.updateByExampleSelective(dreamproduct,dreamProductExample);
				DreamProductDesc dreamProductDesc = new DreamProductDesc();
				dreamProductDesc.setProDesc(data);
				dreamProductDesc.setUpdated(new Date());
				DreamProductDescExample dreamProductDescExample =new DreamProductDescExample();
				DreamProductDescExample.Criteria createCriteria2 = dreamProductDescExample.createCriteria();
						createCriteria2.andProIdEqualTo(id);
				descMapper.updateByExampleSelective(dreamProductDesc, dreamProductDescExample);	
				return DreamResult.build(200,"修改成功");
		    }
	}

	public DreamResult deleteProduct(long id) {   //删除商品信息
		// TODO 自动生成的方法存根
		DreamProduct selectByPrimaryKey = dreamProductMapper.selectByPrimaryKey(id);
		if(selectByPrimaryKey==null){
			return DreamResult.build(200, "没有这个商品");
		}
		else{
					dreamProductMapper.deleteByPrimaryKey(id);
					return DreamResult.build(200, "删除成功");
		}
	}

	public DreamResult findProduct(long id) {   //查找商品信息
		// TODO 自动生成的方法存根
					DreamProduct selectByPrimaryKey = dreamProductMapper.selectByPrimaryKey(id);
					if(selectByPrimaryKey==null){
						return DreamResult.build(200, "没有这个商品");
					}
					else {
						return DreamResult.ok(selectByPrimaryKey);
					}
					
	}

	@Override
	public DreamResult saveProduct(long id) { //商品上架
		DreamProduct selectByPrimaryKey = dreamProductMapper.selectByPrimaryKey(id);
		if(selectByPrimaryKey==null){
			return DreamResult.build(400, "没有这个商品");
									}
		else{
				String jedis = jedisClientPool.get(id+"");
				if(jedis!=null){
					return DreamResult.build(200, "该商品已上架");
								}
		else{
		DreamProduct dreamProduct = new DreamProduct();
			dreamProduct = dreamProductMapper.selectByPrimaryKey(id);
			if(dreamProduct.getQuantity()>0){
			dreamProduct.setState(1);
			DreamProductExample dreamProductExample = new DreamProductExample();
			Criteria createCriteria = dreamProductExample.createCriteria();
			createCriteria.andIdEqualTo(id); 
			dreamProductMapper.updateByExampleSelective(dreamProduct, dreamProductExample);
			String objectToJson = JsonUtils.objectToJson(dreamProduct);
			jedisClientPool.set(id+"", objectToJson);//将上架商品存到Redis
			return DreamResult.build(200, "商品上架成功！");				
											}
			else {
					return DreamResult.build(400, "商品库存不足无法上架！");
				 }
			}
			}
	}
	@Override
	public DreamResult outProduct(long id) {
		DreamProduct selectByPrimaryKey = dreamProductMapper.selectByPrimaryKey(id);
		if(selectByPrimaryKey==null){
			return DreamResult.build(400, "没有这个商品");
									}
		else{
			String jedis = jedisClientPool.get(id+"");
			if(jedis==null){
				return DreamResult.build(400, "该商品未上架");
							}
			else{
				DreamProductExample dreamProductExample = new DreamProductExample();
				Criteria createCriteria = dreamProductExample.createCriteria();
				createCriteria.andIdEqualTo(id);  //设置条件id
					DreamProduct dreamProduct = new DreamProduct();
					dreamProduct.setState(0);
						dreamProductMapper.updateByExampleSelective(dreamProduct, dreamProductExample);
						jedisClientPool.del(id+"");
				return DreamResult.build(200, "商品已下架");
				}
			}
		

	}


}
