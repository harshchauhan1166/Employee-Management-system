package com.springBoot.SpringBootPractice.employee.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ResponseDto {
    String message;
    Object body;
}
