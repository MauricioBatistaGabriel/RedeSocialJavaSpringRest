package br.ovlac.redeSocial.repository;

import br.ovlac.redeSocial.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    public List<Student> findByName(String username);
    public Optional<Student> findByEmail(String email);
}
