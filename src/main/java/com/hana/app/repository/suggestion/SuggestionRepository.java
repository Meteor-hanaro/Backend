package com.hana.app.repository.suggestion;

import com.hana.app.data.entity.suggestion.Suggestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuggestionRepository extends JpaRepository<Suggestion, Long> {
}
