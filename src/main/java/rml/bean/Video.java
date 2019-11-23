package rml.bean;


public class Video {
	private String id;

	private String vid;
	private String vtitle;
	private String vname;
	private String vlenght;
	private String vsize;
	private String crt_date;
	private String vkind;
	
	 
		public Video() {
		super();
		this.id = "";
		this.vid = "";
		this.vtitle = "";
		this.vname = "";
		this.vlenght = "";
		this.vsize = "0";
		this.crt_date = "";
		this.vkind = "0";
	}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
	public String getVkind() {
		return vkind;
	}
	public void setVkind(String vkind) {
		this.vkind = vkind;
	}
	public String getCrt_date() {
		return crt_date;
	}
	public void setCrt_date(String crt_date) {
		this.crt_date = crt_date;
	}
	public String getVid() {
		return vid;
	}
	public void setVid(String vid) {
		this.vid = vid;
	}
	public String getVtitle() {
		return vtitle;
	}
	public void setVtitle(String vtitle) {
		this.vtitle = vtitle;
	}
	public String getVname() {
		return vname;
	}
	public void setVname(String vname) {
		this.vname = vname;
	}
	public String getVlenght() {
		return vlenght;
	}
	public void setVlenght(String vlenght) {
		this.vlenght = vlenght;
	}
	public String getVsize() {
		return vsize;
	}
	public void setVsize(String vsize) {
		this.vsize = vsize;
	}
}
