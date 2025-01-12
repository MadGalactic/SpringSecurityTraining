package com.madgalactic.SpringSecurityTraining;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    private List<Student> students = new ArrayList<>(List.of(
           new Student(1, "Allen", 87),
           new Student(2, "Sara", 98)
    ));

    @GetMapping("/students")
    public List<Student> getStudents(){
        return students;
    }

    // It's not a good idea to get a token from the browser, return the token from the code instead by requesting for it and sending it
    /* Referencing back to the Welcome controller, specifically the HttpServerletRequest, through that request object you
    can get a lot of data, including session ID and the token. With this request object, you can apply one of the many request methods,
    getAttribute. The attribute name can be found in the view page.

    Get attribute gives you a type of Object, and we want a token. Simply type cast
     */
   @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student) {
        students.add(student);
        return student;
    }

}
