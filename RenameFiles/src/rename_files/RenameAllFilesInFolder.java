package rename_files;

import java.io.File;

public class RenameAllFilesInFolder {

	/**
	 * Đổi tên tất cả các file trong 1 thư mục thành tên mới có dạng: newName.extension
	 * @param folderPath folder chứa các file cần đổi tên
	 * @param newName tên mới cho tất cả các file 
	 * @param extension extension mới cho tất cả các file
	 */
	public static void renameAllFilesInFolder(String folderPath, String newName, String extension) {
		if(newName == null || newName.equals("")) {
			System.out.println("New name cannot be null or empty");
			return;
		}
		if(extension == null || extension.equals("")) {
			System.out.println("Extension cannot be null or empty");
			return;
		}
		
		File dir = new File(folderPath);

		int i = 1;
		if (dir.isDirectory()) { // make sure it's a directory
		    for (final File f : dir.listFiles()) {
		        try {
		            File newfile = new File(folderPath + "\\" + newName + "_" + i + "." + extension);

		            if(f.renameTo(newfile)){
		                System.out.println("Rename succesful: " + newName + "_" + i + "." + extension);
					} else {
						System.out.println("Rename failed");
					}
		            i++;
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
		}

	}

	public static void main(String[] args) {
		RenameAllFilesInFolder.renameAllFilesInFolder("E:\\Downloads\\pepe", "pepe_the_frog", "gif");
	}

}
