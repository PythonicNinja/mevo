package com.raizlabs.android.dbflow.converter;

import java.util.UUID;

public class UUIDConverter extends TypeConverter<String, UUID> {
    public String getDBValue(UUID uuid) {
        return uuid != null ? uuid.toString() : null;
    }

    public UUID getModelValue(String str) {
        return str == null ? null : UUID.fromString(str);
    }
}
