package de.andrena.testing.tools.image.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Service;

import com.mongodb.gridfs.GridFSDBFile;

@Service
public class ImageService {
	
	@Autowired
	private GridFsOperations gridFsOperations;
	
	public GridFSDBFile getClothingImageFor(String weather) {
		Criteria imageCriteria = Criteria.where("metadata.weather").is(weather);
		Query imageQuery = new Query().addCriteria(imageCriteria);
		
		return gridFsOperations.findOne(imageQuery);
	}

}
