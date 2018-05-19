package cn.sdcit.service;

import cn.sdcit.entity.DreamUser;
import cn.sdcit.utils.DreamResult;

public interface LoginService {
	DreamResult login(DreamUser dreamUser);

}
