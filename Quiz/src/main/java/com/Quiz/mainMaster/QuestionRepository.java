package com.Quiz.mainMaster;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer>{
	
	@Query(value = "SELECT COUNT(*) FROM ( SELECT id FROM questions  WHERE course_id = :courseId AND company_id = :companyId   LIMIT 30) AS subquery", nativeQuery = true)
	public Integer getTotalQuestionCount(@Param("courseId") Integer courseId, @Param("companyId") Integer companyId);

	 

	@Query(value = "SELECT EXISTS (SELECT 1 FROM questions WHERE questions.question_title = :title AND questions.course_id = :courseId)", nativeQuery = true)
	boolean existsQuestion(@Param("title") String title, @Param("courseId") Integer courseId);


	@Query(nativeQuery = true, value="SELECT * FROM questions  WHERE course_id =:companyId Order by Random()")
	public List<Question> findAllQuestionsByCourseid(@Param("companyId") Integer companyId);


	@Query("SELECT q FROM Question q WHERE q.id IN :selectedQuestionIds AND q.course_id = :courseId")
	public List<Question> findAllByIdInAndCourseid(@Param("selectedQuestionIds") List<Integer> selectedQuestionIds, @Param("courseId") Integer courseId);

	

}
