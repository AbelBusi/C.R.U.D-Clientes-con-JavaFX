package com.java.FxGui.control;

import com.java.FxGui.model.persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRespository extends JpaRepository<persona, Integer> {
}
