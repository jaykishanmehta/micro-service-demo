package com.jay.fusion.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jay.fusion.user.dto.Department;
import com.jay.fusion.user.dto.ResponseTemplateVO;
import com.jay.fusion.user.entity.User;
import com.jay.fusion.user.repository.UserRepository;
import com.jay.fusion.user.service.feign.DepartmentServiceConsumer;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

//    private static final String DEPARTMENT_SERVICE_URL = "http://DEPARTMENT-SERVICE/departments/";
//    private static final String DEPARTMENT_SERVICE_URL = "http://localhost:9001/departments/";

	@Autowired
    private UserRepository userRepository;

	@Autowired
	private DepartmentServiceConsumer departmentServiceClient;
	
//    @Autowired
//    private RestTemplate restTemplate;

    public User saveUser(User user) {
        log.info("Inside saveUser of UserService");
        return userRepository.save(user);
    }

    public ResponseTemplateVO getUserWithDepartment(Long userId) {
        log.info("Inside getUserWithDepartment of UserService");
        ResponseTemplateVO vo = new ResponseTemplateVO();
        User user = userRepository.findByUserId(userId);

//        Department department =
//                restTemplate.getForObject(DEPARTMENT_SERVICE_URL + user.getDepartmentId()
//                        , Department.class);
        Department department = departmentServiceClient.getDepartmentById(user.getDepartmentId());
        vo.setUser(user);
        vo.setDepartment(department);

        return  vo;
    }
}