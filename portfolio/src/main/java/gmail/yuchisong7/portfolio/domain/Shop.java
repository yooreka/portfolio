package gmail.yuchisong7.portfolio.domain;

public class Shop {
	 private int shopid; 
	 private String shopname;
	 private String	businesshour; 
	 private String	mobile; 
	 private String	roadaddress;
	 private String	address;
	public int getShopid() {
		return shopid;
	}
	public void setShopid(int shopid) {
		this.shopid = shopid;
	}
	public String getShopname() {
		return shopname;
	}
	public void setShopname(String shopname) {
		this.shopname = shopname;
	}
	public String getBusinesshour() {
		return businesshour;
	}
	public void setBusinesshour(String businesshour) {
		this.businesshour = businesshour;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getRoadaddress() {
		return roadaddress;
	}
	public void setRoadaddress(String roadaddress) {
		this.roadaddress = roadaddress;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Shop [shopid=" + shopid + ", shopname=" + shopname + ", businesshour=" + businesshour + ", mobile="
				+ mobile + ", roadaddress=" + roadaddress + ", address=" + address + "]";
	}
}
