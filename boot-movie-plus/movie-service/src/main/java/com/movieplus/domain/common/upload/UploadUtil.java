package com.movieplus.domain.common.upload;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.Data;

@Component
public class UploadUtil {

	@Data
	public static class UploadFileRequest{
		private String fileBase64;
		private String fileName;
		private String targetFolder = "";
	}
	
	public String handleFileUpload(@RequestBody UploadFileRequest request) throws IOException
    {       
		String[] imageBase64 = request.getFileBase64().split(",");

		String imageName = getFileName(imageBase64[0].split("/")[1], request.fileName);
		byte[] imageByte = Base64.getDecoder().decode(imageBase64[1].trim());
		InputStream inputStream = new ByteArrayInputStream(imageByte);
		
		saveFile(request.getTargetFolder(), imageName, inputStream);
		return imageName;
    }
	
	private final String UPLOAD_DIR = "../local-store/movie/";

	public boolean saveFile(String targetFolder, String fileName, InputStream inputStream) throws IOException {
		Path uploadPath = Paths.get(UPLOAD_DIR + "/" + targetFolder);
		long fileStore = 0;
		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}

		try {
			Path filePath = uploadPath.resolve(fileName.replace(" ", ""));
			fileStore = Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			throw new IOException("Could not save file:  " + fileName, e);
		}
		return fileStore > 0;
	}
	
	public static String getFileName(String base64Type, String fileName) {
		String extension;
		
		switch (base64Type) {
		case "jpeg;base64":
			extension = ".jpeg";
			break;
		case "png;base64":
			extension = ".png";
			break;
		case "pdf;base64":
			extension = ".pdf";
			break;
		case "vnd.openxmlformats-officedocument.wordprocessingml.document;base64":
			extension = ".docx";
			break;
		case "vnd.openxmlformats-officedocument.spreadsheetml.sheet;base64":
			extension = ".docx";
			break;
		default:
			extension = ".jpg";
			break;
		}

		return (fileName != null ? fileName : UUID.randomUUID().toString()) + extension;
	}
}
