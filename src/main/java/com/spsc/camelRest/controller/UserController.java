/**
 * 
 */
package com.spsc.camelRest.controller;




import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.spsc.camelRest.Util.Utility;

/**
 * @author Sachin Pratap Singh
 *
 */
@RestController
@RequestMapping("datashare")
public class UserController {
	
	@RequestMapping("Ping")
	@GetMapping
	public String getPing() {
		return "Status OK";		
	}
	
	@RequestMapping(value = "upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public @ResponseBody String upload(@RequestParam("file") final MultipartFile file,
			@RequestParam("name") final String json) {
		System.out.println("json ------------->" + json);
		String fileName = null;
		if (!file.isEmpty()) {
			try {
				fileName = file.getOriginalFilename();
				byte[] bytes = file.getBytes();
				OutputStream os = new FileOutputStream("C:\\Users\\Sachin Pratap Singh\\Desktop\\DND\\" + fileName);
				os.write(bytes);
				os.close();
				Utility util = new Utility();
				util.sendRequest(json, "http://localhost:9090/orderNEW");
				return "You have successfully uploaded " + fileName;

			} catch (Exception e) {
				return "You failed to upload " + fileName + ": " + e.getMessage();
			}
		} else {
			return "Unable to upload. File is empty.";
		}

	}

}
