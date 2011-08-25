package org.webhop.ywdc.extras;

public class Status 
{
	public Integer statusid;
	public String status;

	public Status(Integer statusid, String status)
	{
		setStatusid(statusid);
		setStatus(status);
	}
	public Integer getStatusid() {
		return statusid;
	}

	public void setStatusid(Integer statusid) {
		this.statusid = statusid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
