package demo;

public class MSSQLDAO implements AbstractDAO {

	@Override
	public void insert() {
		System.out.println("MSSQL insert");
	}

	@Override
	public void delete() {
		System.out.println("MSSQL delete");
	}

	@Override
	public void update() {
		System.out.println("MSSQL update");
	}

}
