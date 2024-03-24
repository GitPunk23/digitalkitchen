package com.digitalkitchen.enums;

import lombok.Getter;

@Getter
public enum ResponseStatus {
    CREATED("CREATED"),
    DUPLICATE("DUPLICATE"),
    EMPTY("EMPTY"),
    FOUND("FOUND");

    private final String displayName;

    ResponseStatus(final String displayName) { this.displayName = displayName; }
}
