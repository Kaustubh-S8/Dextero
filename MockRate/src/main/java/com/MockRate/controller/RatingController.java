package com.MockRate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MockRate.dto.RatingDto;
import com.MockRate.entity.USERS;
import com.MockRate.enums.Role;
import com.MockRate.exception.USERSNotFound;
import com.MockRate.repository.USERSRepository;
import com.MockRate.service.RatingService;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/rating")
public class RatingController {

	@Autowired
	private RatingService ratingService;
	@Autowired
	private USERSRepository userRepo;
	
	@PostMapping("/admin/{aid}/rate/userid/{uid}/mockid/{mid}")
	public ResponseEntity<String> RateMock(@RequestBody RatingDto ratingDto, @PathVariable Integer uid,@PathVariable Integer mid,@PathVariable Integer aid) {
		USERS user=userRepo.findById(aid).orElseThrow(()->new USERSNotFound("Admin not found"));
		if(user.getRole()!=Role.ADMIN)throw new USERSNotFound("only Admin access allowed");
		ratingDto.setUid(uid);
		ratingDto.setMid(mid);
		return ratingService.RateMock(ratingDto);
	}
	
	@GetMapping("/getrating/userid/{uid}")
	public List<RatingDto> getRatingsByUserid(@PathVariable Integer uid) {
		return ratingService.getAllRatingsbyUserid(uid);
	}
	
	@GetMapping("/getrating/mockid/{mid}")
	public List<RatingDto> getMethodName(@PathVariable Integer mid) {
		return ratingService.getAllRatingbyMockid(mid);
	}
	
	
	
}
