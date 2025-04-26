package com.MockRate.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.MockRate.dto.RatingDto;
import com.MockRate.entity.MockInterview;
import com.MockRate.entity.Rating;
import com.MockRate.entity.USERS;
import com.MockRate.enums.Role;
import com.MockRate.exception.USERSNotFound;
import com.MockRate.repository.MockInterviewRepository;
import com.MockRate.repository.RatingRepository;
import com.MockRate.repository.USERSRepository;

@Service
public class RatingService {
	@Autowired
	private USERSRepository userRepo;
	
	@Autowired
	private RatingRepository ratingRepo;
	@Autowired
	private MockInterviewRepository mockRepo;

	public ResponseEntity<String> RateMock(RatingDto request) {
		USERS user= userRepo.findById(request.getUid()).orElseThrow(() -> new USERSNotFound("User not found"));
		
		if (user.getRole() == Role.ADMIN) {
			throw new USERSNotFound("Admins don't have any presentations.");
		}
		
		MockInterview mock = mockRepo.findById(request.getMid()).orElseThrow(() -> new USERSNotFound("mock not found"));
		if(mock.getUsers().getUid()!=user.getUid()) throw new USERSNotFound("This user "+user.getUid()+" does not have mock with id "+mock.getMid());
		
		Rating	rating = new Rating();	
		BeanUtils.copyProperties(request, rating);
		rating.setUsers(user);
		rating.setMock(mock);
		
		// Calculate the average of all the ratings
		double averageScore = (rating.getCommunication() + rating.getContent() + rating.getInteraction() + rating.getLiveliness() + rating.getConfidence()) / 5.0;
		rating.setTotalScore(averageScore);
		
		ratingRepo.save(rating);
		
		UpdateMockAvgScore(mock.getMid());
		
		UpdateUserTotalScore(user.getUid());
		
		return new ResponseEntity<String>("rating is completed",HttpStatus.OK);
	}

	private void UpdateUserTotalScore(Integer uid) {
		List<MockInterview> mocks = mockRepo.findByUid(uid);

		List<MockInterview> completedmocks = new ArrayList<>();
		for (MockInterview mock : mocks) {
			if ( mock.getMockAvgScore() != null && mock.getMockAvgScore() > 0) {
				completedmocks.add(mock);
			}
		}

		double userTotalScore = 0.0;
		int count = 0;
		for (MockInterview mock : completedmocks) {
			userTotalScore += mock.getMockAvgScore();
			count++;
		}

		if (count > 0) {
			userTotalScore /= count;
		}

		USERS user = userRepo.findById(uid).orElseThrow(() -> new USERSNotFound("User not found"));

		user.setUserTotalScore(userTotalScore);
		userRepo.save(user);
	}

	private void UpdateMockAvgScore(Integer mid) {
		List<Rating> ratings = ratingRepo.findByMid(mid);

		double totalScore = 0.0;
		int count = 0;
		for (Rating rating : ratings) {
			totalScore += rating.getTotalScore();
			count++;
		}

		if (count > 0) {
			totalScore /= count;
		}

		MockInterview mock = mockRepo.findById(mid).orElseThrow(() -> new USERSNotFound("mock not found"));

		mock.setMockAvgScore(totalScore);
		mockRepo.save(mock);
	}
	public List<RatingDto> getAllRatingsbyUserid(Integer uid) {
		List<Rating> ratings = ratingRepo.findByUid(uid);
		List<RatingDto> responseList = new ArrayList<>();

		for (Rating rating : ratings) {
			RatingDto response = new RatingDto();
			BeanUtils.copyProperties(rating, response);
			response.setUid(uid);
			response.setMid(rating.getMock().getMid());
			responseList.add(response);
		}

		return responseList;
	}
	
	public List<RatingDto> getAllRatingbyMockid(Integer mid) {
		List<Rating> ratings = ratingRepo.findByMid(mid);
		List<RatingDto> responseList = new ArrayList<>();

		for (Rating rating : ratings) {
			RatingDto response = new RatingDto();
			BeanUtils.copyProperties(rating, response);
			response.setMid(mid);
			response.setUid(rating.getUsers().getUid());
			responseList.add(response);
		}

		return responseList;
	}

}
