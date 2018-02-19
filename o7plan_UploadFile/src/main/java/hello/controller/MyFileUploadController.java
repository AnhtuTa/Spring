package hello.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import hello.form.MyUploadForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

@Controller
public class MyFileUploadController {

	// Phương thức này được gọi mỗi lần có Submit.
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		Object target = dataBinder.getTarget();
		if (target == null) {
			return;
		}
		System.out.println("Target=" + target);

		if (target.getClass() == MyUploadForm.class) {
			// Đăng ký để chuyển đổi giữa các đối tượng multipart thành byte[]
			dataBinder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
		}
	}

	// GET: Hiển thị trang form upload
	@RequestMapping(value = "/upload-one-file", method = RequestMethod.GET)
	public String uploadOneFileHandler(Model model) {
		MyUploadForm myUploadForm = new MyUploadForm();
		model.addAttribute("myUploadForm", myUploadForm);

		return "uploadOneFile";
	}

	// POST: Sử lý Upload
	@RequestMapping(value = "/upload-one-file", method = RequestMethod.POST)
	public String uploadOneFileHandlerPOST(HttpServletRequest request, //
			Model model, //
			@ModelAttribute("myUploadForm") MyUploadForm myUploadForm) {

		return this.doUpload(request, model, myUploadForm);
	}

	// GET: Hiển thị trang form upload
	@RequestMapping(value = "/upload-multi-file", method = RequestMethod.GET)
	public String uploadMultiFileHandler(Model model) {
		MyUploadForm myUploadForm = new MyUploadForm();
		model.addAttribute("myUploadForm", myUploadForm);

		// Forward to "/WEB-INF/pages/uploadMultiFile.jsp".
		return "uploadMultiFile";
	}

	// POST: Sử lý Upload
	@RequestMapping(value = "/upload-multi-file", method = RequestMethod.POST)
	public String uploadMultiFileHandlerPOST(HttpServletRequest request, //
			Model model, //
			@ModelAttribute("myUploadForm") MyUploadForm myUploadForm) {

		return this.doUpload(request, model, myUploadForm);
	}

	private String doUpload(HttpServletRequest request, Model model, //
			MyUploadForm myUploadForm) {
		String description = myUploadForm.getDescription();
		System.out.println("Description: " + description);

		// Thư mục gốc upload file.
		// String uploadRootPath = request.getServletContext().getRealPath("upload");
		String uploadRootPath = System.getProperty("user.dir") + "\\uploads\\";
		System.out.println("uploadRootPath=" + uploadRootPath);
		// uploadRootPath chính là thư mục chứa file STS.exe, chứ ko phải thư mục project

		File uploadRootDir = new File(uploadRootPath);

		// Tạo thư mục gốc upload nếu nó không tồn tại.
		if (!uploadRootDir.exists()) {
			uploadRootDir.mkdirs();
		}
		CommonsMultipartFile[] fileDatas = myUploadForm.getFileDatas();
		//
		List<File> uploadedFiles = new ArrayList<File>();
		
		// Nếu chạy URL: http://localhost:8080/o7plan_UploadFile/upload-one-file
		// thì fileDatas chỉ là mảng có 1 phần tử
		// Nếu test trên http://localhost:8080/o7plan_UploadFile/upload-multi-file
		// thì fileDatas chỉ là mảng có nhiều phần tử (số phần tử = số lượng file muốn upload)
		for (CommonsMultipartFile fileData : fileDatas) {
			// Tên file gốc tại Client.
			String name = fileData.getOriginalFilename();
			System.out.println("Client File Name = " + name);

			// nếu name == null hoặc name.length() == 0 thì ko có file ở mục này
			if (name != null && name.length() > 0) {
				try {
					// Tạo file tại Server.
					File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);

					// Luồng ghi dữ liệu vào file trên Server.
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(fileData.getBytes());
					stream.close();
					//
					uploadedFiles.add(serverFile);
					System.out.println("Write file: " + serverFile);
				} catch (Exception e) {
					System.out.println("Error Write file: " + name);
				}
			}
		}
		model.addAttribute("description", description);
		model.addAttribute("uploadedFiles", uploadedFiles);
		return "uploadResult";
	}

	public static void main(String[] args) {
		String str = System.getProperty("user.dir") + "\\uploads\\";
		System.out.println(str);
	}
}