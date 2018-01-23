package spring;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

/*
 * Biến jdbcTemplate truy vấn dữ liệu thông qua datasource nên 
 * có thể hiểu jdbcTemplate được quản lý bởi transactionManager
 * Method updateBankAccount được đánh dấu @Transactional (enable quản lý transaction)
 * tức là method này sẽ nằm trong 1 transaction, khi method này 
 * xảy ra 1 lỗi thì tất cả những truy vấn trong method này đều bị rollback.
 */
public class BankAccountDAO {
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Transactional
	public void updateBankAccount(List<BankAccount> baList) {
		String sql = "UPDATE bank_account SET amount = ? WHERE name = ?";
		for(BankAccount ba : baList) {
			jdbcTemplate.update(sql, ba.getAmount(), ba.getName());
		}
		System.out.println("updated!");
	}
}
