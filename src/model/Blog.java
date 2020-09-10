package model;

import java.time.LocalDate;

public class Blog {
	private int blogId;
	private String blogTitle;
	private String blogDescription;
	private LocalDate postedOn;

	
	public Blog() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Blog(int blogId, String blogTitle, String blogDescription, LocalDate postedOn) {
		super();
		this.blogId = blogId;
		this.blogTitle = blogTitle;
		this.blogDescription = blogDescription;
		this.postedOn = postedOn;
	}

	public int getBlogId() {
		return blogId;
	}

	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public String getBlogDescription() {
		return blogDescription;
	}

	public void setBlogDescription(String blogDescription) {
		this.blogDescription = blogDescription;
	}

	public LocalDate getPostedOn() {
		return postedOn;
	}

	public void setPostedOn(LocalDate postedOn) {
		this.postedOn = postedOn;
	}

}
