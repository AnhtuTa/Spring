package hello.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/*
Annotation @XmlRootElement định nghĩa đây là root cho file xml
Annotation @XmlType(propOrder = { “id”, “name”, “email” }) định nghĩa 
  thứ tự cho các thuộc tính (nếu bạn ko định nghĩa thứ thự cho các
  thuộc tính thì nó sẽ sắp xếp theo thứ tự bảng chữ cái)
Annotation @XmlElement định nghĩa các element cho file xml
 */
@XmlRootElement(name = "pupil")
@XmlType(propOrder = { "id", "name", "address" })
public class Pupil {
	private int id;
	private String name;
	private String address;
	
	public Pupil() {}
	
	public Pupil(int id, String name, String address) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
	}

	public int getId() {
		return id;
	}

	@XmlElement
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	@XmlElement
	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	@XmlElement
	public void setAddress(String address) {
		this.address = address;
	}	
}
