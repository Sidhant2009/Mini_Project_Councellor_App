package in.ashokit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.Enquiry;

public interface EnquiryRepo extends JpaRepository<Enquiry, Integer>{

	public List<Enquiry> findByCounsellorCounsellorId(Integer counsellorId);
	

}
