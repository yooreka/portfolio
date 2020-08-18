
package gmail.yuchisong7.portfolio.domain;

public class TouristSpot {
	private int spotid;
	private String spotname;
	private String roadaddress;
	private String touraddress;
	private String tell;
	private String businesshour;
	private String price;
	
	public int getSpotid() {
		return spotid;
	}
	public void setSpotid(int spotid) {
		this.spotid = spotid;
	}
	public String getSpotname() {
		return spotname;
	}
	public void setSpotname(String spotname) {
		this.spotname = spotname;
	}
	public String getRoadaddress() {
		return roadaddress;
	}
	public void setRoadaddress(String roadaddress) {
		this.roadaddress = roadaddress;
	}
	public String getTouraddress() {
		return touraddress;
	}
	public void setTouraddress(String touraddress) {
		this.touraddress = touraddress;
	}
	public String getTell() {
		return tell;
	}
	public void setTell(String tell) {
		this.tell = tell;
	}
	public String getBusinesshour() {
		return businesshour;
	}
	public void setBusinesshour(String businesshour) {
		this.businesshour = businesshour;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "TouristSpot [spotid=" + spotid + ", spotname=" + spotname + ", roadaddress=" + roadaddress
				+ ", touraddress=" + touraddress + ", tell=" + tell + ", businesshour=" + businesshour + ", price="
				+ price + "]";
	}
	
}