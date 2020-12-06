package com.movies.domain;

public class Theatre {
	private int theatreId;
	private String theatreName;
	private String location;
	private int seatingCapacity;
	private String phoneNo;
	private STATUS _3d;
	private STATUS iMax;

	public int getTheatreId() {
		return theatreId;
	}

	public void setTheatreId(int theatreId) {
		this.theatreId = theatreId;
	}

	public String getTheatreName() {
		return theatreName;
	}

	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getSeatingCapacity() {
		return seatingCapacity;
	}

	public void setSeatingCapacity(int seatingCapacity) {
		this.seatingCapacity = seatingCapacity;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public STATUS get_3d() {
		return _3d;
	}

	public void set_3d(STATUS _3d) {
		this._3d = _3d;
	}

	public STATUS getiMax() {
		return iMax;
	}

	public void setiMax(STATUS iMax) {
		this.iMax = iMax;
	}

	@Override
	public String toString() {
		return "Theatre [theatreId=" + theatreId + ", theatreName=" + theatreName + ", location=" + location
				+ ", seatingCapacity=" + seatingCapacity + ", phoneNo=" + phoneNo + ", _3d=" + _3d + ", iMax=" + iMax
				+ "]";
	}


}
