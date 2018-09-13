package hello.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/*
 * Bảng PERSISTENT_LOGINS sẽ được tạo ra dựa trên lớp PersistentLogin, 
 * bảng này sẽ được sử dụng tự động bởi Spring Security Remember Me. 
 * Bạn không được thay đổi cấu trúc của bảng này
 */
@Entity
@Table(name = "persistent_logins")
public class PersistentLogin {
	@Id
	@Column(name = "series", length = 64, nullable = false)
	private String series;

	@Column(name = "username", length = 64, nullable = false)
	private String userName;

	@Column(name = "token", length = 64, nullable = false)
	private String token;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_used", nullable = false)
	private Date lastUsed;

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getLastUsed() {
		return lastUsed;
	}

	public void setLastUsed(Date lastUsed) {
		this.lastUsed = lastUsed;
	}

}
