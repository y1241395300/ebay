package cn.sdcit.entity;

import java.io.Serializable;

public class DreamBrand implements Serializable {
    private Long brandId;

    private Long userId;

    private String brandName;

    private String brandCredentials;

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName == null ? null : brandName.trim();
    }

    public String getBrandCredentials() {
        return brandCredentials;
    }

    public void setBrandCredentials(String brandCredentials) {
        this.brandCredentials = brandCredentials == null ? null : brandCredentials.trim();
    }
}