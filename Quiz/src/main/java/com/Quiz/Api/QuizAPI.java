package com.Quiz.Api;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Quiz.Login.LoginUserService;
import com.Quiz.Login.Session.LoginSession;
import com.Quiz.Login.Session.LoginSessionService;
import com.Quiz.mainMaster.ExcelHelper;
import com.Quiz.mainMaster.Question;
import com.Quiz.mainMaster.QuestionService;
import com.Quiz.mainMaster.QuizResult;
import com.Quiz.mainMaster.QuizResultService;
import com.Quiz.mainMaster.Result;
import com.Quiz.mainMaster.ResultService;







@CrossOrigin(origins = "*")
@RestController
@RequestMapping("Quiz")
public class QuizAPI {

	@Autowired
	private QuestionService questionsService;
	
	@Autowired
	private ResultService resultService;
	@Autowired
	private QuizResultService quizResultService;
	@Autowired
	private LoginUserService loginuserService;
	@Autowired
	private LoginSessionService loginSessionService;
	
	
	
	//////////////////////////////////////////////SHOW QUIZ////////////////////////////////////////
	
	
	
	@GetMapping("/take/test/{user_job_id}/{exam_mode_id}/{course_id}")
    public QuizAllDataModel takeQuiz1(@PathVariable("user_job_id") Integer user_job_id,@PathVariable("exam_mode_id") Integer exam_mode_id,
    		@PathVariable("course_id") Integer course_id,@RequestBody String requestbody) {
		
		JSONObject object = new JSONObject(requestbody);
		Integer perquestiontime= object.optInt("time_per_question");
		Integer questionlimit = object.optInt("number_of_questions");
//		JSONArray jsonArray = object.getJSONArray("selected_question_ids");
//		List<Integer> selectedQuestionIds = new ArrayList<>();
//		for (int i = 0; i < jsonArray.length(); i++) {
//		    selectedQuestionIds.add(jsonArray.getInt(i));
//		}
		
		QuizAllDataModel q = new QuizAllDataModel();
		q.setUser_job_id(user_job_id);
		q.setExam_mode_id(exam_mode_id);
		q.setCourse_id(course_id);
		q.setTimelimit(questionlimit*perquestiontime);// in seconds
		List<QuizModel> qm = new LinkedList<>();
//	Fetch questions based on the provided testId
//		if(selectedQuestionIds!=null && !selectedQuestionIds.isEmpty()) {
//		System.out.print(false);
//       List<Question> questions = questionsService.findAllQuestionsByIdAndCourseid(selectedQuestionIds,course_id).stream().limit(questionlimit).collect(Collectors.toList());
//		}
		 List<Question> questions=questionsService.findAllQuestionsByCourseid(course_id).stream().limit(questionlimit).collect(Collectors.toList());
        Collections.shuffle(questions);
        for (Question question : questions) {
        	QuizModel quizModel = new QuizModel();
            quizModel.setQuestion_id(question.getId());
            quizModel.setQuestion_title(question.getQuestionTitle());
            quizModel.setOption1(question.getOption1());
            quizModel.setOption2(question.getOption2());
            quizModel.setOption3(question.getOption3());
            quizModel.setOption4(question.getOption4());
            qm.add(quizModel);
            q.setCompany_id(question.getCompany_id());
        }
        q.setQuiz_model(qm);
        return q;
    }
	
	
	
	
	//////////////////////////////////////SUBMIT QUIZ///////////////////////////////////////
	
	
	
	private String getCorrectAnswer(Question question) {
		Integer correctOptionIndex = Integer.parseInt(question.getAnswer());
	    try {
	    	switch (correctOptionIndex) {
	    	case 1:
	    		return question.getOption1();
	    	case 2:
	    		return question.getOption2();
	    	case 3:
	    		return question.getOption3();
	    	case 4:
	    		return question.getOption4();
	    	default:
//	Handle the case where the correct answer index is out of the valid range
	    		return null;
	    	}
	    } 
	    catch (NumberFormatException e) {
	        
	    }
//	Handle the case where the format is not as expected
	    return null;
	}

	@PostMapping("/submitTest/{accesstoken}")
	public ResponseEntity<?> submitTest(@PathVariable("accesstoken") String accesstoken,@RequestBody String requestbody) {
		if(loginSessionService.GetCountAccessToken(accesstoken) == 0) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Access Token Expire Or Invalid");
		}
		else {
			LoginSession l = loginSessionService.FindByAccessToken(accesstoken);
			LocalDateTime currentDateAndTime = LocalDateTime.now();
		    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
			String s = "Request Is Not Check";
			int totalQuestions = 0;
		    int totalCorrect = 0;
		    int totalIncorrect = 0;
		    int totalAttempted = 0;
		    Integer user_job_id = 0;
			try {
				JSONObject object = new JSONObject(requestbody);
				user_job_id = object.optInt("job_post_id");
				Integer course_id = object.optInt("course_id");
				Integer company_id = object.optInt("company_id");
				JSONArray array = object.getJSONArray("quiz_model");
				totalQuestions = array.length();
				
				List<QuizResult> qrlist=new ArrayList<>();
				
				for(int i = 0 ; i < array.length() ; i++) {
					JSONObject jsonObject_i = array.getJSONObject(i);
					Integer question_id = jsonObject_i.optInt("question_id");
					String selectedOption = jsonObject_i.optString("answer");
					Question question = questionsService.get(question_id);
					
					QuizResult qr=new QuizResult();
					qr.setQuestionid(question_id);
					qr.setSeletedOption(selectedOption);
//					qr.setUserid(candidateService.Update(l.getUsername()));
					qr.setUserid((Integer)1);
					
					
					if (selectedOption.equals("")) {
						qr.setCorrect(false);
					}
					else {
						totalAttempted++;
			            String correctAnswer = getCorrectAnswer(question);
			            if (correctAnswer != null && selectedOption.equals(correctAnswer)) {
			            	qr.setCorrect(true);
			                totalCorrect++;
			            } else {
			                totalIncorrect++;
			                qr.setCorrect(false);
			            }
					}
					qrlist.add(qr);
					
				}
//				Integer q_count = questionsService.getTotalQuestionCount(course_id,company_id);
//			    System.out.println("Tol Q : " + q_count);
				int totalUnattempted = totalQuestions - totalAttempted;
//				double percentage = (double) totalCorrect / q_count * 100;
				double percentage = (double) totalCorrect / totalQuestions * 100;
				double roundedPercentage = Math.round(percentage * 100.0) / 100.0;
			    Result result = new Result();
//			    result.setUser_id(candidateService.Update(l.getUsername()));
			    result.setUser_id((Integer)1);
			    result.setTotal_correct_answer(totalCorrect);
			    result.setTotal_incorrect_answer(totalIncorrect);
			    
//			    result.setTotal_question(q_count);
			    result.setTotal_question(totalQuestions);
//			    result.setTestname(coursesService.getCourseName(course_id));
			    result.setCompany_id(company_id);
			    result.setJob_post_id(user_job_id);
			    result.setPercentage(roundedPercentage);
			    result.setAttempted_question(totalAttempted);
//			    result.setNot_attempted_question(q_count - totalAttempted);
			    result.setNot_attempted_question(totalQuestions-totalAttempted);
			    result.setCurrentdate(currentDateAndTime.format(dateFormatter));
			    result.setCurrenttime(currentDateAndTime.format(timeFormatter));
//			    result.setCollege_id(newCollegeDetailsService.getCollegeIDByCollegeName(candidateService.getCandidateCollegeName(l.getUsername())));
			    resultService.save(result);
			    
//			    Result r=resultService.getTestResultsForCandidatePost(candidateService.Update(l.getUsername()),user_job_id);
			    Result r=resultService.findByUseridAndJobpostid((Integer)1,user_job_id);
			    for(QuizResult qr:qrlist) {
			    	qr.setResultid(r.getId());
			    }
			    quizResultService.saveAll(qrlist);
			    s = "Test submitted successfully !";
			    
			} catch (Exception e) {
				e.printStackTrace();
			}
//			Integer count = resultService.getResultCount(candidateService.Update(l.getUsername()), user_job_id, currentDateAndTime.format(dateFormatter));
//		    if(count == 0) {
//		    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Test not submitted.");
//		    }
//		    else {
//		    	Result r = resultService.getTestResultsForCandidatePost(candidateService.Update(l.getUsername()), user_job_id);
//		    	return ResponseEntity.status(HttpStatus.OK).body(r);
//		    }
//			
			
			return ResponseEntity.status(HttpStatus.OK).body(s);
		}
	}
	
	
	///////////////////////////UPLOAD QUIZ FROM EXCEL///////////////////////////////////////

	
	
	
	 @PostMapping("/upload")
	    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
	        if (!ExcelHelper.hasExcelFormat(file)) {
	            return ResponseEntity.badRequest().body("Please upload a valid Excel file (.xlsx)");
	        }

	        try {
	            List<Question> questions = ExcelHelper.excelToQuestions(file.getInputStream());
	            System.out.print(questions);
	            for (Question q : questions) {
	                if (!questionsService.questionExists(q.getQuestionTitle(), q.getCourse_id())) {
	                    questionsService.save(q);
	                    System.out.print(true);
	                }
	            }
	            return ResponseEntity.ok("Questions uploaded successfully.");
	        } catch (Exception e) {
	            return ResponseEntity.status(500).body("Failed to upload: " + e.getMessage());
	        }
	    }
	 
	
	 
	 ///////////////////////////////VIEW RESULT(ALL QUESITION AND ANSWERS)////////////////////////////////
	 
	 @GetMapping("/user/result/viewresult/{accesstoken}/{id}")
		public ResponseEntity<?> getMethodName(@PathVariable("accesstoken") String accesstoken,@PathVariable Integer id) {
//			if(loginSessionService.GetCountAccessToken(accesstoken) == 0) {
//				List<String> la = new LinkedList<>();
//				la.add("Access Token Expire Or Invalid");
//				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(la);
//			}
//			else {
//				LoginSession l = loginSessionService.FindByAccessToken(accesstoken);
//			List<QuizResult> list= quizResultService.listAllByUseridAndResultid(candidateService.Update(l.getUsername()),id);
			List<QuizResult>list=quizResultService.listAllByUseridAndResultid(1, id);
		 	List<QuizResultModel> qrmlist=new ArrayList<>();
			for(QuizResult qr:list) {
				Question q = questionsService.get(qr.getQuestionid());
				QuizResultModel qrm=new QuizResultModel();
				qrm.setQuestionTitle(q.getQuestionTitle());
				String correctAnswer = getCorrectAnswer(q);
				qrm.setAnswer(correctAnswer);
				
				qrm.setCorrect(qr.isCorrect());
				qrm.setSelectedOption(qr.getSeletedOption());
				qrmlist.add(qrm);
			}
			
			return ResponseEntity.status(HttpStatus.OK).body(qrmlist);
//			}
		}
	 
	 
	 
	 /////////////////////////////////////////////////USER RESULT/////////////////////////////////////////
	 
	 
	 
	 @GetMapping("/user/result/{accesstoken}")
		public ResponseEntity<List<?>> showJobPost(@PathVariable("accesstoken") String accesstoken) {
//			if(loginSessionService.GetCountAccessToken(accesstoken) == 0) {
//				List<String> la = new LinkedList<>();
//				la.add("Access Token Expire Or Invalid");
//				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(la);
//			}
//			else {
//				LoginSession l = loginSessionService.FindByAccessToken(accesstoken);
		        List<CandidateResultModel> resultModels = new ArrayList<>();
//		        List<Result> results = resultService.getTestResultsForCandidate(candidateService.Update(l.getUsername()));
		        List<Result> results = resultService.getTestResultsForCandidate((Integer)1);
		        
		        for (Result r : results) {
		            if (r.getUser_id().equals((Integer)1)) {
		                CandidateResultModel model = new CandidateResultModel();
		                model.setId(r.getId());
		                model.setAttempted_question(r.getAttempted_question());
		                model.setNot_attempted_question(r.getNot_attempted_question());
		                model.setCompany_id(r.getCompany_id());
		                model.setCurrentdate(r.getCurrentdate());
		                model.setCurrenttime(r.getCurrenttime());
		                model.setPercentage(r.getPercentage());
		                model.setTestname(r.getTestname());
		                model.setTotal_question(r.getTotal_question());
		                model.setTotal_incorrect_answer(r.getTotal_incorrect_answer());
		                model.setTotal_correct_answer(r.getTotal_correct_answer());
		                
		                List<QuizResult>list=quizResultService.listAllByUseridAndResultid(1, r.getId());
		                model.setQuizResult(list);
		                
//		                Candidate candidate = candidateService.getCandidateDataByID(r.getUser_id());
//		                if (candidate != null) {
//		                    model.setUser_id(candidate.getId());
//		                    model.setCandidate_name(candidate.getFirst_name() + " " + candidate.getLast_name());
//		                }
		                
//		                JobPost jobPost = jobService.get(r.getJob_post_id());
//		                if (jobPost != null) {
//		                    PostName postName = postNameService.get(jobPost.getDesignation());
//		                    if (postName != null) {
//		                        model.setDesignation_id(jobPost.getDesignation());
//		                        model.setDesignation_name(postName.getPost());
//		                    }
//		                }
		                
		                resultModels.add(model);
		            }
		        }
		        
		        return ResponseEntity.status(HttpStatus.OK).body(resultModels);
		    }


}
		
	 
	 

