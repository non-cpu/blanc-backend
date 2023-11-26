package com.blanc.market.domain.ingredient.dto;

import jakarta.websocket.server.ServerEndpoint;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IngredientRequest {
    private String name;
    private Long risk;
}
