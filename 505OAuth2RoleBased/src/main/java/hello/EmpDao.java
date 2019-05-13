package hello;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpDao extends JpaRepository<Emp,Integer> {
    //Optional<Emp> findByname(String username);

    Emp findByName(String username);
}
