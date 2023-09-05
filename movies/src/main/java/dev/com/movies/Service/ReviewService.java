package dev.com.movies.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import dev.com.movies.Repository.ReviewRepository;
import dev.com.movies.models.Movies;
import dev.com.movies.models.Review;

@Service
public class ReviewService {
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public Review createReview(String body,String imdbId)
	{
		Review review=reviewRepository.insert(new Review(body));
		
		mongoTemplate.update(Movies.class)
			.matching(Criteria.where("imdbId").is(imdbId))
			.apply(new Update().push("reviewIds").value(review))
			.first();
		
		return review;
	}
}
