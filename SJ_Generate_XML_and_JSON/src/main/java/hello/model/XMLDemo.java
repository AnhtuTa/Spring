package hello.model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//Với các controller trả về dữ liệu định dạng xml thì ta trả trực 
//tiếp đối tượng và sử dụng annotation @ResponseBody

@Controller
public class XMLDemo {
	@RequestMapping(value = { "/", "/index" })
	public String index() {
		return "index";
	}

	@RequestMapping(value= {"/get-pupil/xml/{id}"})
	@ResponseBody
	public Pupil getXML(@PathVariable("id") String id_str) {
		try {
			int id = Integer.valueOf(id_str);
			PupilDAO pd = new PupilDAO();
			return pd.getPupilById(id);
		} catch(java.lang.NumberFormatException e) {
			return new Pupil(1, "This is a default pupil", "Pupil's id must be an integer!");
		}
	}
	
	@RequestMapping("/get-pupil-list/xml/{start}/{amount}")
	@ResponseBody
	public PupilList getPupilList(
			@PathVariable("start") int start,
			@PathVariable("amount") int amount) {
		PupilList pl = new PupilList();
		pl.setListPupil(new PupilDAO().getPupilList(start, amount));
		return pl;
	}
}
