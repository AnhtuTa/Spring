package hello.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "pupil-list")
public class PupilList {
	private List<Pupil> listPupil;

	public PupilList() {}
	
	public PupilList(List<Pupil> listPupil) {
		super();
		this.listPupil = listPupil;
	}

	public List<Pupil> getListPupil() {
		return listPupil;
	}

	@XmlElement(name="pupil")
	public void setListPupil(List<Pupil> listPupil) {
		this.listPupil = listPupil;
	}
	
}
