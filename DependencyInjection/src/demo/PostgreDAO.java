package demo;

public class PostgreDAO implements AbstractDAO {

	@Override
	public void insert() {
		System.out.println("Postgre insert");
	}

	@Override
	public void delete() {
		System.out.println("Postgre delete");
	}

	@Override
	public void update() {
		System.out.println("Postgre update");
	}

}
