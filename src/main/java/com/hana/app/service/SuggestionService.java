package com.hana.app.service;

import com.hana.app.data.entity.suggestion.Suggestion;
import com.hana.app.data.entity.suggestion.SuggestionItem;
import com.hana.app.repository.portfolio.PortfolioRepository;
import com.hana.app.repository.suggestion.SuggestionItemRepository;
import com.hana.app.repository.suggestion.SuggestionRepository;
import com.hana.dto.response.SuggestionDto;
import com.hana.dto.response.SuggestionItemCompositionDto;
import com.hana.dto.response.SuggestionItemDto;
import com.hana.exception.MeteorException;
import com.hana.exception.NotFoundException;
import com.hana.response.ErrorType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class SuggestionService {

    final SuggestionRepository suggestionRepository;
    final SuggestionItemRepository suggestionItemRepository;
    final PortfolioRepository portfolioRepository;

    public SuggestionDto getSuggestionListByUserId(Long userId) {

        Long userPortfolioId = getPortfolioIdByUserId(userId);
        List<Suggestion> suggestions = getSuggestionsByPortfolioId(userPortfolioId);

        return getSuggestions(suggestions);
    }

    private Long getPortfolioIdByUserId(Long userId) {
        Long id = 0L;

        try {
            id = portfolioRepository.findByUserId(userId).getId();
        } catch (MeteorException e) {
            throw new NotFoundException(ErrorType.NOT_FOUND);
        }

        return id;
    }

    private List<Suggestion> getSuggestionsByPortfolioId(Long portfolioId) {
        List<Suggestion> suggestions = null;

        try {
            suggestions = suggestionRepository.findAllByPortfolioId(portfolioId);
        } catch (MeteorException e) {
            throw new NotFoundException(ErrorType.NOT_FOUND);
        }

        return suggestions;
    }

    private SuggestionDto getSuggestions(List<Suggestion> suggestions) {
        List<SuggestionItemDto> suggestionItemDtos = new ArrayList<>();

        for (Suggestion suggestion : suggestions) {

            List<SuggestionItem> suggestionItems = null;
            List<SuggestionItemCompositionDto> suggestionItemCompositionDtoList = new ArrayList<>();

            try {
                suggestionItems = suggestionItemRepository.findAllBySuggestion(suggestion);

                for (SuggestionItem si : suggestionItems) {
                    suggestionItemCompositionDtoList.add(SuggestionItemCompositionDto.from(si));
                }

                suggestionItemDtos.add(SuggestionItemDto.from(suggestion,suggestionItemCompositionDtoList));
            } catch (MeteorException e) {
                throw new NotFoundException(ErrorType.NOT_FOUND);
            }

        }

        return new SuggestionDto(suggestions.getFirst(), suggestionItemDtos);
    }
}
