package com.blanc.market.domain.searchHistory.mapper;

import com.blanc.market.domain.ingredient.mapper.IngredientMapper;
import com.blanc.market.domain.searchHistory.dto.SearchHistoryDto;
import com.blanc.market.domain.searchHistory.entity.SearchHistory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SearchHistoryMapper {

    SearchHistoryDto toDto(SearchHistory searchHistory);
}
