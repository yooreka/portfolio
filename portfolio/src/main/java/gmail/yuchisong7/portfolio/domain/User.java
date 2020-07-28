package gmail.yuchisong7.portfolio.domain;

import java.util.Date;

public class User {
	private String email; 
	private String userpw ;
	private String nickname;
	private String profile;
	private Date logindate;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserpw() {
		return userpw;
	}
	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public Date getLogindate() {
		return logindate;
	}
	public void setLogindate(Date logindate) {
		this.logindate = logindate;
	}
	@Override
	public String toString() {
		return "User [email=" + email + ", userpw=" + userpw + ", nickname=" + nickname + ", profile=" + profile
				+ ", logindate=" + logindate + "]";
	}
	
	
}
