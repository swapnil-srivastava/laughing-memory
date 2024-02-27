package eu.swapnilsrivastava.swapnilsrivastava.repository;

import java.util.List;

import eu.swapnilsrivastava.swapnilsrivastava.model.Speaker;

public interface SpeakerRepository {
    List<Speaker> findAll();
}