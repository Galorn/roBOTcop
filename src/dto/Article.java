package dto;

import java.util.List;

public class Article {
	
	private String name;
	private String color;
	private String price;
	private List<String> sizes;
	private boolean isSoldOut;
	private String link;
	
	public Article(String name, String color, String price, List<String> sizes, boolean isSoldOut, String link) {
		this.name = name;
		this.color = color;
		this.price = price;
		this.sizes = sizes;
		this.isSoldOut = isSoldOut;
		this.link = link;
	}
	public Article() {
		
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public List<String> getSizes() {
		return sizes;
	}

	public void setSizes(List<String> sizes) {
		this.sizes = sizes;
	}

	public boolean isSoldOut() {
		return isSoldOut;
	}

	public void setSoldOut(boolean isSoldOut) {
		this.isSoldOut = isSoldOut;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	
	
	
}
