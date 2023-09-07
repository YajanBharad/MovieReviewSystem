package dev.com.movies.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.com.movies.Service.ReviewService;
import dev.com.movies.models.Review;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin("http://localhost:3000")
public class ReviewController {
	
	@Autowired
	ReviewService reviewService;
	
	@PostMapping
	public ResponseEntity<Review> createReview(@RequestBody Map<String,String> data)
	{
		return new ResponseEntity<Review>(reviewService.createReview(data.get("body"),data.get("imdbId")),HttpStatus.CREATED);
	}

}
