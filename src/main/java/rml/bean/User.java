package rml.bean;

public class User {
	private String id;
 
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	private String code; //观看码
	private Double count;   //观看次数
	private Double count_static;
	private int isdeleted;

	private String crtDate;
	public String getCrtDate() {
		return crtDate;
	}
	public void setCrtDate(String crtDate) {
		this.crtDate = crtDate;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Double getCount() {
		return count;
	}
	public void setCount(Double count) {
		this.count = count;
	}
	public Double getCount_static() {
		return count_static;
	}
	public void setCount_static(Double count_static) {
		this.count_static = count_static;
	}
	public int getIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(int isdeleted) {
		this.isdeleted = isdeleted;
	}
	
}
