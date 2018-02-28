package hello.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/*
 * Một lớp được sử dụng để ánh xạ (mapping) tương ứng 1-1 
 * giữa 1 cột trong câu truy vấn và 1 trường (field) trong 
 * lớp model được gọi là lớp mapper. 
 * BankAccountMapper là một lớp như vậy.
 */
public class BankAccountMapper implements RowMapper<BankAccountInfo> {

	@Override
	public BankAccountInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new BankAccountInfo(rs.getLong("id"), rs.getString("full_name"), rs.getDouble("balance"));
	}

}
