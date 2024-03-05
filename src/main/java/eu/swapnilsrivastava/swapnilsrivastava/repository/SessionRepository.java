package eu.swapnilsrivastava.swapnilsrivastava.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.swapnilsrivastava.swapnilsrivastava.model.Session;

public interface SessionRepository extends JpaRepository<Session, Long>{
    
}
