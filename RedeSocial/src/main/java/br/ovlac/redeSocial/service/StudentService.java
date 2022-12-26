package br.ovlac.redeSocial.service;

import br.ovlac.redeSocial.model.Student;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;

public interface StudentService {

    public ResponseEntity<List<Student>> findAll();
    public ResponseEntity<Optional<Student>> findById(long id);
    public ResponseEntity<List<Student>> findByName(String username);
    public ResponseEntity<Student> create(Student newStudent);
    public String updatePassword(String password, long id);
    public String updateEmail(String email, long id);
    public void deleteById(long id);
    public void disabled(Student disabledStudent);
    public void enable(Student desabilitaUsuario);
    public void recoverPassword(String para, String conteudo);
}
