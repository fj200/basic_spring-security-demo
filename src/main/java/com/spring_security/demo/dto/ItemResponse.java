package com.spring_security.demo.dto;

import com.spring_security.demo.common.ItemState;

public record ItemResponse(String id, String data, String userId, ItemState itemState) {
}
