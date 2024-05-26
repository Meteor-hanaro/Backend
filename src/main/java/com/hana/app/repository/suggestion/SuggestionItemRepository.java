package com.hana.app.repository.suggestion;

import com.hana.app.data.entity.suggestion.SuggestionItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuggestionItemRepository extends JpaRepository<SuggestionItem, Long> {
}
