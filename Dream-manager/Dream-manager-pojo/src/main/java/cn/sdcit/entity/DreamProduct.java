package cn.sdcit.entity;

import java.io.Serializable;
import java.util.Date;

public class DreamProduct 	implements Serializable{
    private Long id;

    private String title;

    private String images;

    private Double price;

    private Integer quantity;

    private Long cid;

    private Date created;

    private Date updated;

    private Integer state;

    private Long uid;
    
    private DreamProductDesc dreamProductDesc;
    

    public DreamProductDesc getDreamProductDesc() {
		return dreamProductDesc;
	}

	public void setDreamProductDesc(DreamProductDesc dreamProductDesc) {
		this.dreamProductDesc = dreamProductDesc;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images == null ? null : images.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }
}