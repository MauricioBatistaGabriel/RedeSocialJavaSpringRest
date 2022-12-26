package br.ovlac.redeSocial.controller;

import br.ovlac.redeSocial.model.Post;
import br.ovlac.redeSocial.model.Student;
import br.ovlac.redeSocial.service.PostService;
import br.ovlac.redeSocial.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    PostService postService;

    @GetMapping("/student/{id}")
    public ResponseEntity<Optional<Student>> returnStudentById(@PathVariable Long id){
        return studentService.findById(id);
    }

    @GetMapping("/postsStudent/{id}")
    public List<Post> returnStudentPosts(@PathVariable long id){
        return postService.findByStudent_Id(id);
    }

    @GetMapping("/students")
    public ResponseEntity<List<Student>> listStudents(){
        return studentService.findAll();
    }

    @GetMapping("/studentsByName/{username}")
    public ResponseEntity<List<Student>> listStudentByName(@PathVariable String username){
        return studentService.findByName(username);
    }

    @PostMapping("/registerStudent")
    public ResponseEntity<Student> registerStudent(@RequestBody Student newStudent){
         return studentService.create(newStudent);
    }

    @PutMapping("/editPasswordStudent/{id}")
    public String editPasswordStudentById(@RequestBody String password, @PathVariable long id){
        return studentService.updatePassword(password, id);
    }

    @PutMapping("/editEmailStudent/{id}")
    public String editEmailStudentById(@RequestBody String email, @PathVariable long id){
        return studentService.updateEmail(email, id);
    }

    @DeleteMapping("/deleteStudent/{id}")
    public void deleteStudentById(@PathVariable Long id){
        studentService.deleteById(id);
    }

    @GetMapping("/recoverStudent")
    public void recoverStudentByEmail(@RequestBody Student student){

        int maximumCharacters = 8;
        String[] characteres = {"1", "2", "4", "5", "6", "7", "8",
                "9","0" , "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
                "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w",
                "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I",
                "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
                "V", "W", "X", "Y", "Z" };

        StringBuilder password = new StringBuilder();

        for (int i = 0; i < maximumCharacters; i++) {
            int position = (int) (Math.random() * characteres.length);
            password.append(characteres[position]);
        }
        studentService.updatePassword(password.toString(), student.getId());
        studentService.recoverPassword(student.getEmail(), password.toString());
    }
}
