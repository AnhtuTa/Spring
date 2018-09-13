package hello.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(
	name = "user_role",
	uniqueConstraints = {
		@UniqueConstraint(
			name = "USER_ROLE_UK", //tên khóa unique
			columnNames = { "USER_ID", "ROLE_ID" }	//các cột của khóa trên
		)
	}
)
public class UserRole {

	@Id
	@GeneratedValue
	@Column(name = "ID", nullable = false)
	private Long id;

	//field USER_ID sẽ tham chiếu tới bảng app_user
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", nullable = false)
	private AppUser appUser;

	//field ROLE_ID sẽ tham chiếu tới bảng app_role
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLE_ID", nullable = false)
	private AppRole appRole;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public AppRole getAppRole() {
		return appRole;
	}

	public void setAppRole(AppRole appRole) {
		this.appRole = appRole;
	}
}
