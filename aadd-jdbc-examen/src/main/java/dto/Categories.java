package dto;

public class Categories {

	private Integer categoryId;
	private String categoryName;
	private String description;
	private byte[] picture;
	private Integer product;

	public Categories() {
	}

	public Categories(String categoryName) {
		this.categoryName = categoryName;
	}

	public Categories(String categoryName, String description, byte[] picture, Integer product) {
		this.categoryName = categoryName;
		this.description = description;
		this.picture = picture;
		this.product = product;
	}

	public Integer getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getPicture() {
		return this.picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public Integer getProduct() {
		return this.product;
	}

	public void setProduct(Integer product) {
		this.product = product;
	}

}