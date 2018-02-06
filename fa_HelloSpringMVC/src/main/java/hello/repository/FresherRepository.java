package hello.repository;

import org.springframework.stereotype.Repository;

import hello.model.Fresher;

@Repository
public class FresherRepository {
	public boolean authenticate(Fresher f) {
		/// check fresher in database
		//....
		
		//mô phỏng việc check mà ko dùng CSDL:
		return "att".equals(f.getUsername()) && "1111".equals(f.getPassword());
	}

	public boolean authenRegister(Fresher f) {
		/// check fresher in database
		// VD: xem thằng f này đã tồn tại chưa; validate input,...
		
		// sau đó mới return
		return (!"att".equals(f.getUsername()));
	}
}
