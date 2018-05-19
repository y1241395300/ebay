package cn.sdcit.service;

import cn.sdcit.utils.DreamResult;

public interface ListService {
public DreamResult addproduct(long uid,long pid);
public DreamResult updateproduct(long uid,long pid);
public DreamResult deleteproduct(long uid,long pid);
public DreamResult findproduct(long uid);
}
