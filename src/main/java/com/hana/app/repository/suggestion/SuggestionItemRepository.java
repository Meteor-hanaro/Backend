package com.hana.app.repository.suggestion;

import com.hana.app.data.entity.suggestion.Suggestion;
import com.hana.app.data.entity.suggestion.SuggestionItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuggestionItemRepository extends JpaRepository<SuggestionItem, Long> {
    List<SuggestionItem> findAllBySuggestion(Suggestion suggestion);
}
