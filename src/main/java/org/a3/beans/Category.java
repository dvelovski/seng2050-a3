package org.a3.beans;

import java.io.Serializable;

public class Category implements Serializable {
	private int categoryId;
	private int subcategoryOf; 
	private String categoryName;
	
	public Category () {
		
		this.categoryId = 0;	
		this.subcategoryOf = 0; 
		this.categoryName = null;
    }
	
	public void setCategoryId (int newCategoryId) {
		this.categoryId = newCategoryId;
	}

	public void setCategoryName (String newCategoryName) {
		this.categoryName = newCategoryName;
	}


	public void setSubCategoryOf (int newSubcategoryOf) {
		this.subcategoryOf = newSubcategoryOf;
	}

	public int getCategoryId () {
		return this.categoryId;
	}

	public String getCategoryName () {
		return this.categoryName;
	}

	public int getsubCategoryOf () {
		return this.subcategoryOf;
	}
}