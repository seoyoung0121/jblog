package jblog.vo;

public class CategoryVo {
	private Long id;
	private String name;
	private String description;
	private String blog_id;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getBlog_id() {
		return blog_id;
	}
	public void setBlog_id(String blog_id) {
		this.blog_id = blog_id;
	}
	
	@Override
	public String toString() {
		return "CategoryVo [id=" + id + ", name=" + name + ", description=" + description + ", blog_id=" + blog_id
				+ "]";
	}
}
