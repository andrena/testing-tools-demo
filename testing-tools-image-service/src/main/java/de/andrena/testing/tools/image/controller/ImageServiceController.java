package de.andrena.testing.tools.image.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.gridfs.GridFSDBFile;

import de.andrena.testing.tools.image.service.ImageService;

@RestController
public class ImageServiceController {

	@Autowired
	private ImageService imageService;

	@RequestMapping(value = "/image/{weather}/", method = RequestMethod.GET, produces = { "image/png" })
	@ResponseBody
	public byte[] getImage(@PathVariable("weather") String weather) throws IOException {
		GridFSDBFile image = imageService.getClothingImageFor(weather);

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		image.writeTo(outputStream);
		return outputStream.toByteArray();
	}

}
