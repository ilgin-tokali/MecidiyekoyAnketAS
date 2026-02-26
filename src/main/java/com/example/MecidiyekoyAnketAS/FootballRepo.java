package com.example.MecidiyekoyAnketAS;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FootballRepo extends JpaRepository<FootballSurvey, Integer> {

}
