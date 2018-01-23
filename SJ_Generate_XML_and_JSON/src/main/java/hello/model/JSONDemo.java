package hello.model;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//Với các controller trả về dữ liệu định dạng xml thì ta trả trực 
//tiếp đối tượng và sử dụng annotation @ResponseBody

@Controller
public class JSONDemo {
	@RequestMapping(value= {"/get-pupil/json/{id}"})
	@ResponseBody
	public Pupil_JSON getXML(@PathVariable("id") String id_str) {
		try {
			int id = Integer.valueOf(id_str);
			PupilDAO pd = new PupilDAO();
			return pd.getPupilById_JSON(id);
		} catch(java.lang.NumberFormatException e) {
			return new Pupil_JSON(1, "This is a default pupil", "Pupil's id must be an integer!");
		}
	}
	
	@RequestMapping("/get-pupil-list/json/{start}/{amount}")
	@ResponseBody
	public List<Pupil_JSON> getPupilList(
			@PathVariable("start") int start,
			@PathVariable("amount") int amount) {
		return new PupilDAO().getPupilList_JSON(start, amount);
	}
}
