package com.digitalkitchen.enums;

import lombok.Getter;

@Getter
public enum ResponseStatus {
    CREATED("CREATED"),
    DUPLICATE("DUPLICATE");

    private final String displayName;

    ResponseStatus(final String displayName) { this.displayName = displayName; }
}
