package security_demo.dao;

import java.util.List;

import security_demo.model.Spitter;

/*
 * Ko annotate thằng này là @Service hay @Repository..., vì nó
 * là 1 interface. Ta phải annotate ở 1 class cụ thể implement nó
 */
public interface SpitterDetailsDAO {
	/**
	 * Find a Spitter object with the given username
	 * @param - username username of Spitter
	 * @return an security_demo.model.Spitter object
	 */
	public Spitter findSpitter(String username);
	
	/**
	 * Get all roles of a spitter with the given username
	 * @param username - username of Spitter
	 * @return a list of roles
	 */
	public List<String> getSpitterRoles(String username);
}
