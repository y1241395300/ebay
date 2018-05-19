package cn.sdcit.entity;

import java.util.Date;
import java.util.List;

public class EbayProduct {
    private Long id;

    private String title;

    private String images;

    private Double price;

    private Integer quantity;

    private Long cid;

    private String country;

    private String location;

    private String currency;

    private String listingDuration;

    private String paymentMethods;

    private String paypalEmailAddress;

    private Integer dispatchTimeMax;

    private String returnPolicy;

    private Long eid;

    private Long sid;

    private Date created;

    private Date updated;

    private Long uid;

    private String eurl;

    private Integer way;

    private String postalcode;
    
    private  List<EbayDetail> detaillist;
    
    public List<EbayDetail> getDetaillist() {
		return detaillist;
	}

	public void setDetaillist(List<EbayDetail> detaillist) {
		this.detaillist = detaillist;
	}

	public EbayProductDesc getEbayProductDesc() {
		return ebayProductDesc;
	}

	public void setEbayProductDesc(EbayProductDesc ebayProductDesc) {
		this.ebayProductDesc = ebayProductDesc;
	}

	private EbayProductDesc ebayProductDesc;
    
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public String getListingDuration() {
        return listingDuration;
    }

    public void setListingDuration(String listingDuration) {
        this.listingDuration = listingDuration == null ? null : listingDuration.trim();
    }

    public String getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(String paymentMethods) {
        this.paymentMethods = paymentMethods == null ? null : paymentMethods.trim();
    }

    public String getPaypalEmailAddress() {
        return paypalEmailAddress;
    }

    public void setPaypalEmailAddress(String paypalEmailAddress) {
        this.paypalEmailAddress = paypalEmailAddress == null ? null : paypalEmailAddress.trim();
    }

    public Integer getDispatchTimeMax() {
        return dispatchTimeMax;
    }

    public void setDispatchTimeMax(Integer dispatchTimeMax) {
        this.dispatchTimeMax = dispatchTimeMax;
    }

    public String getReturnPolicy() {
        return returnPolicy;
    }

    public void setReturnPolicy(String returnPolicy) {
        this.returnPolicy = returnPolicy == null ? null : returnPolicy.trim();
    }

    public Long getEid() {
        return eid;
    }

    public void setEid(Long eid) {
        this.eid = eid;
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
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

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getEurl() {
        return eurl;
    }

    public void setEurl(String eurl) {
        this.eurl = eurl == null ? null : eurl.trim();
    }

    public Integer getWay() {
        return way;
    }

    public void setWay(Integer way) {
        this.way = way;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode == null ? null : postalcode.trim();
    }
}