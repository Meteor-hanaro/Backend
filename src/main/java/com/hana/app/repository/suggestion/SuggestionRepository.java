package com.hana.app.repository.suggestion;

import com.hana.app.data.entity.suggestion.Suggestion;
import io.lettuce.core.dynamic.annotation.Param;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuggestionRepository extends JpaRepository<Suggestion, Long> {
    List<Suggestion> findAllByPortfolioId(Long portfolioId);

    void removeSuggestionById(Long suggestionId);

    @Modifying
    @Transactional
    @Query("UPDATE Suggestion s SET s.name = :suggestion_name WHERE s.id = :suggestionId")
    int updateSuggestionNameById(@Param("suggestionItemId") Long suggestionId, @Param("suggestionName") String suggestion_name);
}
