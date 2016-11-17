package com.mindtree.sdet.entitiy;

public class Employee {

	private String mid;
	private String ename;
	private String vertical;
	private String practice;
	private String competency;

	public Employee() {

	}

	public Employee(String mid, String ename, String vertical, String practice, String competency) {
		super();
		this.mid = mid;
		this.ename = ename;
		this.vertical = vertical;
		this.practice = practice;
		this.competency = competency;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getVertical() {
		return vertical;
	}

	public void setVertical(String vertical) {
		this.vertical = vertical;
	}

	public String getPractice() {
		return practice;
	}

	public void setPractice(String practice) {
		this.practice = practice;
	}

	public String getCompetency() {
		return competency;
	}

	public void setCompetency(String competency) {
		this.competency = competency;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((competency == null) ? 0 : competency.hashCode());
		result = prime * result + ((ename == null) ? 0 : ename.hashCode());
		result = prime * result + ((mid == null) ? 0 : mid.hashCode());
		result = prime * result + ((practice == null) ? 0 : practice.hashCode());
		result = prime * result + ((vertical == null) ? 0 : vertical.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (competency == null) {
			if (other.competency != null)
				return false;
		} else if (!competency.equals(other.competency))
			return false;
		if (ename == null) {
			if (other.ename != null)
				return false;
		} else if (!ename.equals(other.ename))
			return false;
		if (mid == null) {
			if (other.mid != null)
				return false;
		} else if (!mid.equals(other.mid))
			return false;
		if (practice == null) {
			if (other.practice != null)
				return false;
		} else if (!practice.equals(other.practice))
			return false;
		if (vertical == null) {
			if (other.vertical != null)
				return false;
		} else if (!vertical.equals(other.vertical))
			return false;
		return true;
	}

}
