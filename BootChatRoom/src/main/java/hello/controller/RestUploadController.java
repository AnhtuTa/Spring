package hello.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class RestUploadController {
    @Autowired
    private Environment env;
	
	// Single file upload
	@PostMapping("/rest/upload")
	public ResponseEntity<?> uploadFile(@RequestParam("file_upload") MultipartFile uploadfile) {
		if (uploadfile.isEmpty()) {
			return new ResponseEntity<Object>("please select a file!", HttpStatus.OK);
		}

		System.out.println("uploadfile = " + uploadfile);
		try {
			String filename = saveUploadedFile(uploadfile);
			return new ResponseEntity<Object>("{\"status\": \"Successfully uploaded - " + 
					uploadfile.getOriginalFilename() + "\", \"image_url\": \"/rest/images?name=" + filename + "\"}",
					new HttpHeaders(), HttpStatus.OK);
		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	// save file
	// return: file name
	private String saveUploadedFile(MultipartFile file) throws IOException {
		if (file.isEmpty()) {
			return null;
		}

		String uploadFolder = env.getProperty("image_upload_folder");
		File uploadFolderDir = new File(uploadFolder);
		if(!uploadFolderDir.exists()) {
			uploadFolderDir.mkdirs();
		}
		
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		String uuid = UUID.randomUUID().toString();
		byte[] bytes = file.getBytes();
		Path path = Paths.get(uploadFolder + File.separator + uuid + "." + extension);
		Files.write(path, bytes);

		return uuid + "." + extension;
	}
	
	@RequestMapping(value = "/rest/images", method = RequestMethod.GET, //
			produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE} )
    @ResponseBody
    public byte[] getImage(HttpServletRequest request) throws IOException {
		String imageName = request.getParameter("name");
		String uploadFolder = env.getProperty("image_upload_folder");
        File serverFile = new File(uploadFolder + File.separator + imageName);
        
        return Files.readAllBytes(serverFile.toPath());
    }

	/*
	 * Không dùng được hàm này, vì giả sử truy cập vào: 
	 * https://localhost:8443/chat/rest/images/578eeaa0-27cc-490b-8228-16e24b75a382.PNG
	 * thì name ở đây chỉ là 578eeaa0-27cc-490b-8228-16e24b75a382, do đó ko thể
	 * tìm thấy file này trong thư mục lưu ảnh trên server
	 */
//	@Deprecated
//	@RequestMapping(value = "/rest/images/{name}", method = RequestMethod.GET, //
//			produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE} )
//    @ResponseBody
//    public byte[] getImage2(HttpServletRequest request, @PathVariable("name") String imageName)
//    		throws IOException {
//		String uploadFolder = env.getProperty("image_upload_folder");
//        File serverFile = new File(uploadFolder + File.separator + imageName);
//        
//        return Files.readAllBytes(serverFile.toPath());
//    }
	
//	public static void main(String[] args) {
//		String uuid = UUID.randomUUID().toString();
//	    System.out.println("uuid = " + uuid);
//	}
}
