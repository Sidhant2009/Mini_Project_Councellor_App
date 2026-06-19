package in.ashokit.service;

import java.util.List;

import in.ashokit.dto.EnqFilterRequestDto;
import in.ashokit.dto.EnquiryDto;
import in.ashokit.entity.Enquiry;

public interface EnquiryService {

	public boolean addEnquiry(EnquiryDto enquiryDto,Integer counsellorId);
	
	public List<Enquiry> getAllEquiries(Integer coursellorId);

	public List<Enquiry> getEnquiriesWithFilter(EnqFilterRequestDto filterRequestDto,Integer coursellorId);
	
	public boolean updateEnquiry(EnquiryDto enquiryDto);
	
	public Enquiry getEnquiriesById(Integer enqId);
	
	public boolean EDitAddEnquiry(EnquiryDto dto, Integer cid);
}
