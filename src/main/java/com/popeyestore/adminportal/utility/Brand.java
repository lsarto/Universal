package com.popeyestore.adminportal.utility;

public class Brand {

	private Boolean wismec;
	private Boolean drops;
	private Boolean ijoy;
	private Boolean geekVape;
	
	public Brand(){
		
	}
	
	public Brand(Boolean wismec, Boolean drops, Boolean ijoy, Boolean geekVape) {
		super();
		this.wismec = wismec;
		this.drops = drops;
		this.ijoy = ijoy;
		this.geekVape = geekVape;
	}

	public Boolean getWismec() {
		return wismec;
	}

	public void setWismec(Boolean wismec) {
		this.wismec = wismec;
	}

	public Boolean getDrops() {
		return drops;
	}

	public void setDrops(Boolean drops) {
		this.drops = drops;
	}

	public Boolean getIjoy() {
		return ijoy;
	}

	public void setIjoy(Boolean ijoy) {
		this.ijoy = ijoy;
	}

	public Boolean getGeekVape() {
		return geekVape;
	}

	public void setGeekVape(Boolean geekVape) {
		this.geekVape = geekVape;
	}
	

}
