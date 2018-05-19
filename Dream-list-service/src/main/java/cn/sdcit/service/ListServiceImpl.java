package cn.sdcit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sdcit.reids.JedisClient;
import cn.sdcit.utils.DreamResult;
@Service
public class ListServiceImpl implements ListService{
	@Autowired
	private JedisClient jedisClient;
	@Autowired
	private PR
	public DreamResult addproduct(long uid, long pid) {
		// TODO 自动生成的方法存根
		
		return null;
	}

	@Override
	public DreamResult updateproduct(long uid, long pid) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public DreamResult deleteproduct(long uid, long pid) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public DreamResult findproduct(long uid) {
		// TODO 自动生成的方法存根
		return null;
	}


}
