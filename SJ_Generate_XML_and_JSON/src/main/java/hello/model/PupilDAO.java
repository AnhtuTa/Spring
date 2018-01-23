package hello.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PupilDAO extends DatabaseManagement {
	public Pupil getPupilById(int id) {
		connect();
		try {
			pst = conn.prepareStatement("SELECT * FROM pupil WHERE id = " + id);
	        rs = pst.executeQuery();
	        while(rs.next()) {
	        	return new Pupil(rs.getInt(1), rs.getString(2), rs.getString(3));
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Pupil_JSON getPupilById_JSON(int id) {
		connect();
		try {
			pst = conn.prepareStatement("SELECT * FROM pupil WHERE id = " + id);
	        rs = pst.executeQuery();
	        while(rs.next()) {
	        	return new Pupil_JSON(rs.getInt(1), rs.getString(2), rs.getString(3));
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Pupil> getPupilList(int start, int amount) {
		connect();
		List<Pupil> pList = new ArrayList<Pupil>();
		try {
			pst = conn.prepareStatement("SELECT * FROM pupil LIMIT " + start + ", " + amount);
	        rs = pst.executeQuery();
	        while(rs.next()) {
	        	pList.add(new Pupil(rs.getInt(1), rs.getString(2), rs.getString(3)));
	        }
	        return pList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Pupil_JSON> getPupilList_JSON(int start, int amount) {
		connect();
		List<Pupil_JSON> pList = new ArrayList<Pupil_JSON>();
		try {
			pst = conn.prepareStatement("SELECT * FROM pupil LIMIT " + start + ", " + amount);
	        rs = pst.executeQuery();
	        while(rs.next()) {
	        	pList.add(new Pupil_JSON(rs.getInt(1), rs.getString(2), rs.getString(3)));
	        }
	        return pList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
