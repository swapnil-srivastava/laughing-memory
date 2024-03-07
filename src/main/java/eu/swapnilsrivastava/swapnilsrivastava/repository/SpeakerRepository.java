package eu.swapnilsrivastava.swapnilsrivastava.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.swapnilsrivastava.swapnilsrivastava.model.Speaker;

public interface SpeakerRepository extends JpaRepository<Speaker, Long>{
    
}
