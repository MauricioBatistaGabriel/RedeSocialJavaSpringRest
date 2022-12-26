package br.ovlac.redeSocial.service;

import br.ovlac.redeSocial.model.Student;
import br.ovlac.redeSocial.repository.StudentRepository;
import org.hibernate.dialect.PostgreSQL9Dialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.sql.*;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    private JavaMailSender javaMailSender;


    private Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dbSocial",
            "postgres", "senai");

    private static final String UPDATE_USERS_SQL = "UPDATE student SET email = ? WHERE id = ?;";

    public StudentServiceImpl() throws SQLException {
    }

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public ResponseEntity<List<Student>> findAll() {
        return ResponseEntity.ok().body(studentRepository.findAll());
    }

    @Override
    public ResponseEntity<Optional<Student>> findById(long id) {
        return ResponseEntity.ok().body(studentRepository.findById(id));
    }

    @Override
    public ResponseEntity<List<Student>> findByName(String username){
        return ResponseEntity.ok().body(studentRepository.findByName(username));
    }

    @Override
    public ResponseEntity<Student> create(Student newStudent) {
        if (newStudent == null)
            return ResponseEntity.badRequest().build();
        else{
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String password = newStudent.getPassword();
            newStudent.setPassword(encoder.encode(password));
            return ResponseEntity.ok().body(studentRepository.save(newStudent));
        }
    }

    @Override
    public String updatePassword(String password, long id) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Student studentAux = studentRepository.findById(id).get();
        studentAux.setPassword(encoder.encode(password));
        return "senha alterada!";
    }

    @Override
    public String updateEmail(String email, long id) {
        try{
            PreparedStatement preparedStatement = con.prepareStatement(UPDATE_USERS_SQL);
            preparedStatement.setString(1, email);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
            return "email alterado!";
        }catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void deleteById(long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public void disabled(Student disabledStudent) {
        disabledStudent.setIsDesabled(true);
    }

    @Override
    public void enable(Student enableStudent) {
        enableStudent.setIsDesabled(false);
    }

    @Override
    public void recoverPassword(String student, String messageText){
        var message = new SimpleMailMessage();
        message.setTo(student);
        message.setSubject("Recuperação de senha!");
        message.setText(messageText);
        javaMailSender.send(message);
    }
}
