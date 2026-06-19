package in.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.Counsellor;
import in.ashokit.entity.Course;

public interface CourseRepo extends JpaRepository<Course, Integer> {

	
}
