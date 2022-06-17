package com.jay.fusion.user.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jay.fusion.user.dto.Department;

@FeignClient(value = "DEPARTMENT-SERVICE", url = "http://localhost:9001/")
public interface DepartmentServiceConsumer {

    @RequestMapping(method = RequestMethod.GET, value = "/departments/{deptId}", produces = "application/json")
    Department getDepartmentById(@PathVariable("deptId") Long postId);
}
