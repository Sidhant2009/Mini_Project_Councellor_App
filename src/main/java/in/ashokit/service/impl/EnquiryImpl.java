

package in.ashokit.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import in.ashokit.dto.EnqFilterRequestDto;
import in.ashokit.dto.EnquiryDto;
import in.ashokit.entity.Counsellor;
import in.ashokit.entity.Course;
import in.ashokit.entity.Enquiry;
import in.ashokit.repo.CounsellorRepo;
import in.ashokit.repo.CourseRepo;
import in.ashokit.repo.EnquiryRepo;
import in.ashokit.service.EnquiryService;


@Service
public class EnquiryImpl implements EnquiryService{
    
	@Autowired
	private CourseRepo courseRepo;
	
	@Autowired
	private CounsellorRepo counsellorRepo;
	
	@Autowired
	private EnquiryRepo enquiryRepo;
	
	public boolean addEnquiry(EnquiryDto enquiryDto,Integer counsellorId) {

		
		Counsellor counsellor = counsellorRepo.findById(counsellorId).orElseThrow();
		 Course course = courseRepo.findById(enquiryDto.getCourseId()).orElseThrow();
		
		
		Enquiry entity=new Enquiry();
		
		BeanUtils.copyProperties(enquiryDto, entity);
		
		entity.setCourse(course);
		entity.setCounsellor(counsellor);
		
		Enquiry savedEnq = enquiryRepo.save(entity);
		
		return savedEnq.getEnqId()!=null;
	}


	public List<Enquiry> getAllEquiries(Integer counsellorId) {
		
		return enquiryRepo.findByCounsellorCounsellorId(counsellorId);
	}

	
	@Override
	public List<Enquiry> getEnquiriesWithFilter( EnqFilterRequestDto filterRequestDto,Integer counsellorId) {

	    // method body
	
	   ;

	    Enquiry entity = new Enquiry();
	    Counsellor counsellor = counsellorRepo.findById(counsellorId).orElseThrow();

	    entity.setCounsellor(counsellor);

	    // if class mode selected add in where clause
	    if (StringUtils.hasText(filterRequestDto.getClassMode())){
	    	entity.setClassMode(filterRequestDto.getClassMode());
	    }
	    	// if EnqStstustatus selected add in where clause
	    if (StringUtils.hasText(filterRequestDto.getEnqStatus())){
	 	    	entity.setEnqStatus(filterRequestDto.getEnqStatus());
	    }
	 	    	//if course selected then add where clause
	 	 if(filterRequestDto.getCourseId()!=null && filterRequestDto.getCourseId()>0) {
	 		 
	 		 Course course = courseRepo.findById(filterRequestDto.getCourseId()).orElseThrow();
	 		 entity.setCourse(course);
	 		 }
	 	
	    return enquiryRepo.findAll(Example.of(entity));
	    }
	    
	
	
	public boolean updateEnquiry(EnquiryDto enquiryDto) {

		Optional<Enquiry> byId = enquiryRepo.findById(enquiryDto.getEnqId());
		if(byId.isPresent()) {
			
			Enquiry enquiry = byId.get();
			enquiry.setStuPhno(enquiryDto.getStuPhno());
			enquiry.setStuName(enquiryDto.getStuName());
			enquiry.setEnqStatus(enquiryDto.getEnqStatus());
			enquiryRepo.save(enquiry);
			return true;
		}
		
		return false;
	}


	@Override
	public Enquiry getEnquiriesById(Integer enqId) {
		
		return  enquiryRepo.findById(enqId)
                .orElseThrow();
	}


	public boolean EditAddEnquiry(EnquiryDto dto, Integer cid) {

	    Enquiry entity;

	    if (dto.getEnqId() != null) {
	        // UPDATE FLOW
	        entity = enquiryRepo.findById(dto.getEnqId())
	                .orElseThrow(() -> new RuntimeException("Enquiry not found"));
	    } else {
	        // INSERT FLOW
	        entity = new Enquiry();
	    }

	    BeanUtils.copyProperties(dto, entity);

	    Course course = courseRepo.findById(dto.getCourseId())
	            .orElseThrow();

	    entity.setCourse(course);

	    Counsellor counsellor = counsellorRepo.findById(cid)
	            .orElseThrow();

	    entity.setCounsellor(counsellor);

	    enquiryRepo.save(entity);

	    return true;
	}


	@Override
	public boolean EDitAddEnquiry(EnquiryDto dto, Integer cid) {
		Enquiry entity;

	    if (dto.getEnqId() != null) {
	        // UPDATE FLOW
	        entity = enquiryRepo.findById(dto.getEnqId())
	                .orElseThrow(() -> new RuntimeException("Enquiry not found"));
	    } else {
	        // INSERT FLOW
	        entity = new Enquiry();
	    }

	    BeanUtils.copyProperties(dto, entity);

	    Course course = courseRepo.findById(dto.getCourseId())
	            .orElseThrow();

	    entity.setCourse(course);

	    Counsellor counsellor = counsellorRepo.findById(cid)
	            .orElseThrow();

	    entity.setCounsellor(counsellor);

	    enquiryRepo.save(entity);

	    return true;
	}


	

	

}
