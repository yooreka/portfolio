package gmail.yuchisong7.portfolio.domain;

import java.sql.Timestamp;

public class ShopReview {

	private int shopcommentid;
	private String nickname;
	private int shopid;
	private Timestamp shopcommentdate;
	private String shopmessage;
	
	public int getShopcommentid() {
		return shopcommentid;
	}
	public void setShopcommentid(int shopcommentid) {
		this.shopcommentid = shopcommentid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getShopid() {
		return shopid;
	}
	public void setShopid(int shopid) {
		this.shopid = shopid;
	}

	public Timestamp getShopcommentdate() {
		return shopcommentdate;
	}
	public void setShopcommentdate(Timestamp shopcommentdate) {
		this.shopcommentdate = shopcommentdate;
	}
	public String getShopmessage() {
		return shopmessage;
	}
	public void setShopmessage(String shopmessage) {
		this.shopmessage = shopmessage;
	}
	@Override
	public String toString() {
		return "ShopReview [shopcommentid=" + shopcommentid + ", nickname=" + nickname + ", shopid=" + shopid
				+ ", shopcommentdate=" + shopcommentdate + ", shopmessage=" + shopmessage + "]";
	}
	
}
