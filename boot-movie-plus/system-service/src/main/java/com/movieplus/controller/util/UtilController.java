package com.movieplus.controller.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.movieplus.domain.service.CommonService;

import lombok.Data;

@RestController
@RequestMapping("/util")
public class UtilController {
	
	@Data
	public static class UploadFileRequest{
		private String fileBase64;
		private String fileName;
		private String targetFolder = "";
	}
	
	@Autowired
	private CommonService commonService;
	
	@RequestMapping(value="/upload-file", method=RequestMethod.POST)
    @ResponseBody
	public String handleFileUpload(@RequestBody UploadFileRequest request) throws IOException
    {       
		String[] imageBase64 = request.getFileBase64().split(",");

		String imageName = getFileName(imageBase64[0].split("/")[1], request.fileName);
		byte[] imageByte = Base64.getDecoder().decode(imageBase64[1].trim());
		InputStream inputStream = new ByteArrayInputStream(imageByte);
		
		commonService.saveFile(request.getTargetFolder(), imageName, inputStream);
		return imageName;
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
