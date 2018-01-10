package demo;

public class Client {
	AbstractDAO dao;
	
	public Client() {
		dao = FactoryDAO.getDAO();
	}
	
	public void execute() {
		dao.insert();
		dao.update();
		dao.delete();
	}
}
