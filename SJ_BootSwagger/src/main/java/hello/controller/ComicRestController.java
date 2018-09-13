package hello.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hello.model.Comic;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/rest")
@Api(tags = "Comic rest controller")
public class ComicRestController {
	public static HashMap<Integer, Comic> comicMap = new HashMap<>();

	static {
		comicMap.put(1, new Comic(1, "Yaiba", "Aoyama Gosho"));
		comicMap.put(2, new Comic(2, "Dragonball Super", "Akira Toriyama"));
		comicMap.put(3, new Comic(3, "Conan", "Aoyama Gosho"));
		comicMap.put(4, new Comic(4, "Doraemon", "Fujiko F Fujio"));
		comicMap.put(5, new Comic(5, "One Piece", "Eiichiro Oda"));
	}

	@RequestMapping(value = "/comics", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	// Annotation @ApiOperation dùng để chỉ rõ method nào sẽ được tạo document:
	//	value: mô tả chức năng của method
	//	tags: dùng để nhóm tất cả các api (method) trong class thành một nhóm
	// Annotation @ApiResponses dùng để chỉ rõ code và message trả về
	@ApiOperation(value = "(value) Get all comics", tags = "Comic getting")
	@ApiResponses(value = { //
			@ApiResponse(code = 200, message = "Success"), //
			@ApiResponse(code = 400, message = "Bad request"), //
			@ApiResponse(code = 500, message = "Internal server error") })
	public ResponseEntity<List<Comic>> getAllComic() {
		List<Comic> comicList = new ArrayList<Comic>(comicMap.values());
		return new ResponseEntity<List<Comic>>(comicList, HttpStatus.OK);
	}

	@ApiOperation(value = "Get Comic By ID", tags = "Comic getting")
	@ApiResponses(value = { //
			@ApiResponse(code = 200, message = "Success"), //
			@ApiResponse(code = 400, message = "Bad request"), //
			@ApiResponse(code = 500, message = "Internal server error") })
	@RequestMapping(value = "/comic/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> getComic(@PathVariable int id) {
		Comic comic = comicMap.get(id);
		if (comic != null) {
			return new ResponseEntity<Object>(comic, HttpStatus.OK);
		}
		return new ResponseEntity<Object>("Not Found comic", HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "Create new Comic", tags = "Comic creating")
	@ApiResponses(value = { //
			@ApiResponse(code = 200, message = "Success"), //
			@ApiResponse(code = 400, message = "Bad request"), //
			@ApiResponse(code = 500, message = "Internal server error") })
	@RequestMapping(value = "/comic", method = RequestMethod.POST)
	public ResponseEntity<String> createComic(@RequestBody Comic comic) {
		if (comicMap.containsKey(comic.getId())) {
			return new ResponseEntity<String>("Comic Already Exist!", HttpStatus.CONFLICT);
		}
		comicMap.put(comic.getId(), comic);
		return new ResponseEntity<String>("Created!", HttpStatus.CREATED);
	}

	@ApiOperation(value = "Delete Comic", tags = "Comic modifying")
	@ApiResponses(value = { //
			@ApiResponse(code = 200, message = "Success"), //
			@ApiResponse(code = 400, message = "Bad request"), //
			@ApiResponse(code = 500, message = "Internal server error") })
	@RequestMapping(value = "/comic/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteComic(@PathVariable int id) {
		Comic comic = comicMap.get(id);
		if (comic == null) {
			return new ResponseEntity<String>("Not Found Comic", HttpStatus.OK);
		}
		comicMap.remove(id);
		return new ResponseEntity<String>("Deleted!", HttpStatus.OK);
	}

	@ApiOperation(value = "Update Comic", tags = "Comic modifying")
	@ApiResponses(value = { //
			@ApiResponse(code = 200, message = "Success"), //
			@ApiResponse(code = 400, message = "Bad request"), //
			@ApiResponse(code = 500, message = "Internal server error") })
	@RequestMapping(value = "/comics", method = RequestMethod.PUT)
	public ResponseEntity<String> updateComic(@RequestBody Comic comic) {
		Comic oldComic = comicMap.get(comic.getId());
		if (oldComic == null) {
			return new ResponseEntity<String>("Not Found Comic", HttpStatus.NO_CONTENT);
		}
		comicMap.put(comic.getId(), comic);
		return new ResponseEntity<String>("Updated!", HttpStatus.OK);
	}

}
