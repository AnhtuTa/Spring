package hello.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * USER_CONNECTION & USER_ACCOUNTS
	Nếu bạn có sẵn một ứng dụng Web Spring, và đăng nhập vào hệ thống 
	này thông qua các tài khoản được lưu trong một bảng, chẳng hạn USER_ACCOUNTS. 
	(Chú ý rằng có thể cấu trúc bảng Users của bạn có thể khác với cấu 
	trúc của bảng này).
	
	Bạn có thể tích hợp chức năng đăng nhập thông qua mạng xã hội 
	vào Website của bạn. Bạn cần tạo ra một bảng USERCONNECTION. Chú ý rằng 
	cấu trúc của bảng này được cung cấp bởi Spring Social Security API, vì 
	vậy việc insert, update dữ liệu vào bảng này được hỗ trợ bởi API này. 
	Tuy nhiên bạn cũng cũng có thể tùy biến cấu trúc của nó.
	
	Chú ý: Việc insert, update vào bảng USERCONNECTION được thực hiện 
	bởi lớp JdbcUsersConnectionRepository của Spring Social Security API.
 */
/*
 * Bảng USER_CONNECTION sẽ được tạo ra dựa trên lớp UserConnection, 
 * bảng này sẽ được sử dụng tự động bởi Spring Social API, nó lưu 
 * trữ các thông tin công khai của người dùng lấy được từ mạng xã 
 * hội như ProviderId, ProviderUserId, Displayname, Imageurl, ... 
 * Bạn KHÔNG được thay đổi cấu trúc của bảng này.
 */

@Entity
@Table(name = "user_connection")
public class UserConnection implements Serializable {
	private static final long serialVersionUID = 1636780986878218851L;

	@Id
	@Column(name = "user_id", length = 255, nullable = false)
	private String userId;

	@Id
	@Column(name = "provider_id", length = 255, nullable = false)
	private String providerId;

	@Id
	@Column(name = "provider_user_id", length = 255, nullable = false)
	private String providerUserId;

	@Column(name = "rank", nullable = false)
	private int rank;

	@Column(name = "display_name", length = 255, nullable = true)
	private String displayName;

	@Column(name = "profile_url", length = 512, nullable = true)
	private String profileUrl;

	@Column(name = "image_url", length = 512, nullable = true)
	private String imageUrl;

	@Column(name = "access_token", length = 512, nullable = true)
	private String accessToken;

	@Column(name = "secret", length = 512, nullable = true)
	private String secret;

	@Column(name = "refresh_token", length = 512, nullable = true)
	private String refreshToken;

	@Column(name = "expire_time", nullable = true)
	private Long expireTime;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public String getProviderUserId() {
		return providerUserId;
	}

	public void setProviderUserId(String providerUserId) {
		this.providerUserId = providerUserId;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getProfileUrl() {
		return profileUrl;
	}

	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public Long getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Long expireTime) {
		this.expireTime = expireTime;
	}
}
