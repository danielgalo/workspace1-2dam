package dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class Products implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer productId;
	private Integer categoryId;
	private Integer supplierId;
	private String productName;
	private String quantityPerUnit;
	private BigDecimal unitPrice;
	private Short unitsInStock;
	private Short unitsOnOrder;
	private Short reorderLevel;
	private boolean discontinued;

	public Products() {
	}

	public Products(String productName, boolean discontinued) {
		this.productName = productName;
		this.discontinued = discontinued;
	}

	public Products(Integer categoryId, Integer supplierId, String productName, String quantityPerUnit,
			BigDecimal unitPrice, Short unitsInStock, Short unitsOnOrder, Short reorderLevel, boolean discontinued) {
		this.categoryId = categoryId;
		this.supplierId = supplierId;
		this.productName = productName;
		this.quantityPerUnit = quantityPerUnit;
		this.unitPrice = unitPrice;
		this.unitsInStock = unitsInStock;
		this.unitsOnOrder = unitsOnOrder;
		this.reorderLevel = reorderLevel;
		this.discontinued = discontinued;
	}

	/**
	 * @param productId
	 * @param categoryId
	 * @param supplierId
	 * @param productName
	 * @param quantityPerUnit
	 * @param unitPrice
	 * @param unitsInStock
	 * @param unitsOnOrder
	 * @param reorderLevel
	 * @param discontinued
	 */
	public Products(Integer productId, Integer categoryId, Integer supplierId, String productName,
			String quantityPerUnit, BigDecimal unitPrice, Short unitsInStock, Short unitsOnOrder, Short reorderLevel,
			boolean discontinued) {
		this.productId = productId;
		this.categoryId = categoryId;
		this.supplierId = supplierId;
		this.productName = productName;
		this.quantityPerUnit = quantityPerUnit;
		this.unitPrice = unitPrice;
		this.unitsInStock = unitsInStock;
		this.unitsOnOrder = unitsOnOrder;
		this.reorderLevel = reorderLevel;
		this.discontinued = discontinued;
	}

	public void imprimirAtributos() {
		System.out.println("productId = " + productId);
		System.out.println("categoryId = " + categoryId);
		System.out.println("supplierId = " + supplierId);
		System.out.println("productName = " + productName);
		System.out.println("quantityPerUnit = " + quantityPerUnit);
		System.out.println("unitPrice = " + unitPrice);
		System.out.println("unitsInStock = " + unitsInStock);
		System.out.println("unitsOnOrder = " + unitsOnOrder);
		System.out.println("reorderLevel = " + reorderLevel);
		System.out.println("discontinued = " + discontinued);
	}

	public Integer getProductId() {
		return this.productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getSupplierId() {
		return this.supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getQuantityPerUnit() {
		return this.quantityPerUnit;
	}

	public void setQuantityPerUnit(String quantityPerUnit) {
		this.quantityPerUnit = quantityPerUnit;
	}

	public BigDecimal getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Short getUnitsInStock() {
		return this.unitsInStock;
	}

	public void setUnitsInStock(Short unitsInStock) {
		this.unitsInStock = unitsInStock;
	}

	public Short getUnitsOnOrder() {
		return this.unitsOnOrder;
	}

	public void setUnitsOnOrder(Short unitsOnOrder) {
		this.unitsOnOrder = unitsOnOrder;
	}

	public Short getReorderLevel() {
		return this.reorderLevel;
	}

	public void setReorderLevel(Short reorderLevel) {
		this.reorderLevel = reorderLevel;
	}

	public boolean isDiscontinued() {
		return this.discontinued;
	}

	public void setDiscontinued(boolean discontinued) {
		this.discontinued = discontinued;
	}

}
