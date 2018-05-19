package cn.sdcit.service;

import cn.sdcit.entity.DreamBrand;
import cn.sdcit.entity.DreamUser;
import cn.sdcit.utils.DreamResult;

public interface RegisterService {
	DreamResult save(DreamUser dreamUser,DreamBrand dreamBrand);

}
