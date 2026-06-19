package in.ashokit.service;

import org.springframework.stereotype.Service;

import in.ashokit.dto.DashboardResponseDto;
import in.ashokit.entity.Counsellor;
@Service
public interface CounsellorService {

	
	public boolean register(Counsellor counsellor);
	
	public boolean isEmailUnique(String email);

    public Counsellor login(String email, String Pwd);
    
    public DashboardResponseDto getDashboardInfo(Integer counsellorId);

    
}


