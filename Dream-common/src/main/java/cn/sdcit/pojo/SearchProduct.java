package cn.sdcit.pojo;

import java.io.Serializable;

public class SearchProduct implements Serializable {
	private String id;
	private String title;
	private float price;
	private String images;
	private String category_name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public float getPrice() {
		return price;
	}

	public void setPrice(float f) {
		this.price = f;
	}

	public String getimages() {
		return images;
	}

	public void setimages(String images) {
		this.images = images;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String[] getimagess() {
		if(images!=null){
			String[] split = images.split(",");
			return split;
		}
		else{
			String[] split = null;
			split[0]="aaa";
			return split;
		}
		
			
	

	}

}
