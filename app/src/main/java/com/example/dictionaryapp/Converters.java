package com.example.dictionaryapp;

import androidx.room.TypeConverter;

import java.util.UUID;

public class Converters {
    @TypeConverter
    public static UUID strToUUID(String uuid){
        return UUID.fromString(uuid);
    }

    @TypeConverter
    public static String UUIDToStr(UUID uuid){
        return uuid.toString();
    }
}
