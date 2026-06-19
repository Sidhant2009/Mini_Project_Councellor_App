package in.ashokit.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Enquiry {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer enqId;
	private String  stuName;
	private Long  stuPhno;
	private String  classMode;
	private String enqStatus;
	
	  
	
	
	@ManyToOne
	@JoinColumn(name ="course_id")
    private Course course;
	
	
	@ManyToOne
	@JoinColumn(name ="counsellor_id")
    private Counsellor counsellor;
	
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@CreationTimestamp
	private LocalDateTime updatedAt;
	
	
	
	
	}

