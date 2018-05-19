package cn.sdcit.service;

import cn.sdcit.utils.DreamResult;

public interface TokenService {
	public DreamResult getuserByToken(String token);
}
