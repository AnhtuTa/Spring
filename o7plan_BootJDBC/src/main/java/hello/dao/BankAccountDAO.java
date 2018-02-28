package hello.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hello.exception.BankTransactionException;
import hello.model.BankAccountInfo;
import hello.model.BankAccountMapper;

@Repository
@Transactional
public class BankAccountDAO extends JdbcDaoSupport {

	@Autowired
	public BankAccountDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	public List<BankAccountInfo> getBankAccounts() {
		String sql = "SELECT * FROM bank_account";
		return this.getJdbcTemplate().query(sql, new BankAccountMapper());
	}

	public BankAccountInfo findBankAccountById(Long id) {
		String sql = "SELECT * FROM bank_account WHERE id = ?";
		Object[] params = new Object[] { id };
		try {
			return this.getJdbcTemplate().queryForObject(sql, params, new BankAccountMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	/**
	 * Thêm lượng tiền = ammount vào tài khoản có id = id
	 * @param id : id của tài khoản cần thêm tiền
	 * @param amount : số lượng tiền cần thêm, nếu amount < 0 nghĩa là tài khoản này sẽ trừ bớt tiền
	 * @throws BankTransactionException
	 */
	// MANDATORY: Giao dịch bắt buộc phải được tạo sẵn trước đó.
	@Transactional(propagation = Propagation.MANDATORY)
	public void addAmount(Long id, double amount) throws BankTransactionException {
		BankAccountInfo bai = this.findBankAccountById(id);
		if(bai == null) throw new BankTransactionException("Account '" + id + "' not found!");
		
		double newBalance = bai.getBalance() + amount;
		if(newBalance < 0) {
			throw new BankTransactionException(
				"The money in account '" + id + "' is NOT enough! (" + bai.getBalance() + ")");
		}
		
		String sqlUpdate = "UPDATE bank_account SET balance = ? WHERE id = ?";
		this.getJdbcTemplate().update(sqlUpdate, newBalance, id);
	}

	/*
	 * Sử dụng @Transactional(rollbackFor = BankTransactionException.class) 
	 * chú thích (annotate) trên một phương thức để nói với "Spring Transaction"
	 * rằng hãy áp dụng AOP cho phương thức này.
	 * 
	 * Spring Transaction áp dụng Spring AOP cho phương thức của bạn, 
	 * nó giống như hành động thay đổi code của phương thức, thêm vào 
	 * đoạn code bắt ngoại lệ và gọi Rollback giao dịch khi ngoại lệ 
	 * xẩy ra, sau đó nó ném tiếp (rethrow) ngoại lệ ra ngoài phương thức. 
	 * Tất cả giống như minh họa dưới đây:
	 */
	// Không được bắt BankTransactionException trong phương thức này.
	@Transactional(propagation = Propagation.REQUIRES_NEW, 
			rollbackFor = BankTransactionException.class)
	public void sendMoney(Long fromAccountId, Long toAccountId, double amount) 
			throws BankTransactionException {
		addAmount(toAccountId, amount);
		addAmount(fromAccountId, -amount);
	}
	/*
	 * Code trên tương đương với (Spring AOP code):
	 public void sendMoney(Long fromAccountId, Long toAccountId, double amount) 
			throws BankTransactionException {
		try {
			addAmount(toAccountId, amount);
			addAmount(fromAccountId, -amount);
		} catch (BankTransactionException e) {
			throw e;
		}
	}
	 */
}
