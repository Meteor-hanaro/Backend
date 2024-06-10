package com.hana.app.repository.suggestion;

import com.hana.app.data.entity.suggestion.Suggestion;
import com.hana.app.data.entity.suggestion.SuggestionItem;
import io.lettuce.core.dynamic.annotation.Param;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuggestionItemRepository extends JpaRepository<SuggestionItem, Long> {
    List<SuggestionItem> findAllBySuggestion(Suggestion suggestion);

    List<SuggestionItem> findAllBySuggestionId(Long suggestionId);

	void deleteAllBySuggestionId(Long suggestionId);

    @Modifying
    @Transactional
    @Query("UPDATE SuggestionItem si SET si.fundValue = :fundValue WHERE si.id = :suggestionItemId")
    int updateFundValueById(@Param("suggestionItemId") Long suggestionItemId, @Param("fundValue") Long fundValue);

    SuggestionItem findSuggestionItemById(Long suggestionItemId);

    void deleteSuggestionItemById(Long suggestionItemId);
}
