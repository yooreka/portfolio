package gmail.yuchisong7.portfolio.domain;

import java.sql.Timestamp;

public class SpotReview {
	private int commentid;
	private String nickname;
	private int spotid;
	private int score;
	private Timestamp commentdate;
	private String message;
	public int getCommentid() {
		return commentid;
	}
	public void setCommentid(int commentid) {
		this.commentid = commentid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getSpotid() {
		return spotid;
	}
	public void setSpotid(int spotid) {
		this.spotid = spotid;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Timestamp getCommentdate() {
		return commentdate;
	}
	public void setCommentdate(Timestamp commentdate) {
		this.commentdate = commentdate;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "SpotReview [commentid=" + commentid + ", nickname=" + nickname + ", spotid=" + spotid + ", score="
				+ score + ", commentdate=" + commentdate + ", message=" + message + "]";
	}
	

}
