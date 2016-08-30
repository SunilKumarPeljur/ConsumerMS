/**
 * 
 */
package com.training;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author admin
 *
 */


public class Orders {
	
	public Orders() {
		// TODO Auto-generated constructor stub
	}

	public Orders(int id, String name, Date inceptionDate, Boolean isActive) {
		super();
		this.id = id;
		this.name = name;
		this.orderDate = inceptionDate;
		this.isActive = isActive;
	}


	private int id;
	
	private String name;	
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date orderDate;
	
	private Boolean isActive;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "Orders [id=" + id + ", name=" + name + ", orderDate=" + orderDate + ", isActive=" + isActive + "]";
	}	
}
