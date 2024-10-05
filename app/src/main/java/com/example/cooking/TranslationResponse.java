package com.example.cooking;

import com.google.gson.annotations.SerializedName;

public class TranslationResponse {
    @SerializedName("data")
    private TranslationData data;

    public TranslationData getData() {
        return data;
    }

    public static class TranslationData {
        @SerializedName("translations")
        private Translation[] translations;

        public Translation[] getTranslations() {
            return translations;
        }
    }

    public static class Translation {
        @SerializedName("translatedText")
        private String translatedText;

        public String getTranslatedText() {
            return translatedText;
        }
    }
}
