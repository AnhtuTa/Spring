package hello.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(
	name = "app_role",
	uniqueConstraints = {
			@UniqueConstraint(name = "APP_ROLE_UK", columnNames = "ROLE_NAME") 
	}
)
public class AppRole {

	public static final String ROLE_USER = "ROLE_USER";
	public static final String ROLE_ADMIN = "ROLE_ADMIN";

	@Id
	@GeneratedValue
	@Column(name = "ROLE_ID", nullable = false)
	private Long roleId;

	@Column(name = "ROLE_NAME", length = 30, nullable = false)
	private String roleName;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}