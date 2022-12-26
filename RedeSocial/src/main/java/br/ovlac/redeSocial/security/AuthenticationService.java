package br.ovlac.redeSocial.security;

import br.ovlac.redeSocial.model.Student;
import br.ovlac.redeSocial.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Student> student = studentRepository.findByEmail(username);
        if(student.isPresent())
            return student.get();
        throw new UsernameNotFoundException("Dados inválidos!");
    }
}
