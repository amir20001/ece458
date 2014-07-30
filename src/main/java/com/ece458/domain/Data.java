package com.ece458.domain;

public class Data extends BaseEntity {
	private String ip;
	private String domainName;
	private String signature;

	public Data(){
		
	}
	
	public Data(String ip, String domainName,String signature){
		this.ip=ip;
		this.domainName=domainName;
		this.signature=signature;
	}
	
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	@Override
	public String toString() {
		return "ip: " + ip + ", domainName: " + domainName + ", signature:"
				+ signature;
	}

}
