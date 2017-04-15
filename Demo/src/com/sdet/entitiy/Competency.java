package com.mindtree.sdet.entitiy;

public class Competency {
	private String code;
	private String comptencyName;
	
	public Competency(String code, String comptencyName) {
		super();
		this.code = code;
		this.comptencyName = comptencyName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getComptencyName() {
		return comptencyName;
	}
	public void setComptencyName(String comptencyName) {
		this.comptencyName = comptencyName;
	}
	

}
