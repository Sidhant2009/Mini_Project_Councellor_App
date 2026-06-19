package in.ashokit.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.Counsellor;
import in.ashokit.entity.Enquiry;

public interface CounsellorRepo extends JpaRepository<Counsellor, Integer> {

	 //select * from Counsellor table where email name =
	
	public Optional<Counsellor> findByEmail(String email);
	
	public Optional <Counsellor> findByEmailAndPwd(String email,String pwd);
	
	
}
