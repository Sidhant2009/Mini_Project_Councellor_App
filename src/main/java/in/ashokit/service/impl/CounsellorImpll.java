package in.ashokit.service.impl;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.dto.DashboardResponseDto;
import in.ashokit.entity.Counsellor;
import in.ashokit.entity.Enquiry;
import in.ashokit.repo.CounsellorRepo;
import in.ashokit.repo.EnquiryRepo;
import in.ashokit.service.CounsellorService;

@Service
public class CounsellorImpll implements CounsellorService {

	@Autowired
	private CounsellorRepo counsellorRepo;
	
	@Autowired
	private EnquiryRepo enquiryRepo;
	
	public boolean register(Counsellor counsellor) {
		
		Counsellor savedCounsellor= counsellorRepo.save(counsellor);
		
		if(savedCounsellor.getCounsellorId()!=null) {
			return true;
		}
		
		return false ;
	}

	
	public boolean isEmailUnique(String email) {
		
		Optional<Counsellor> optional = counsellorRepo.findByEmail(email);	
		
		if(optional.isPresent()) {
			
			return false;
		}
		return true;
	}

	
	public Counsellor login(String email, String Pwd) {
  
		 Optional<Counsellor> byEmailAndPwd = counsellorRepo.findByEmailAndPwd(email, Pwd);
		
		 if(byEmailAndPwd.isPresent()) {
			 
		 
		return  byEmailAndPwd.get();
		 }
		 return null;
	}
	
	@Override
	public DashboardResponseDto getDashboardInfo(Integer counsellorId) {

	    List<Enquiry> enquiryList =
	            enquiryRepo.findByCounsellorCounsellorId(counsellorId);

	    int totalEnqs = enquiryList.size();

	    Map<String, Long> statusWiseMap = enquiryList.stream()
	            .collect(Collectors.groupingBy(
	                    Enquiry::getEnqStatus,
	                    Collectors.counting()));

	    int openCnt = statusWiseMap.getOrDefault("Open", 0L).intValue();

	    int enrolledCnt = statusWiseMap.getOrDefault("Enrolled", 0L).intValue();

	    int lostCnt = statusWiseMap.getOrDefault("Lost", 0L).intValue();

	    return DashboardResponseDto.builder()
	            .totalEnqs(totalEnqs)
	            .openEnqs(openCnt)
	            .enrolledEnqs(enrolledCnt)
	            .lostEnqs(lostCnt)
	            .build();
	}
	}




