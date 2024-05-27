package ch.hearc.ig.zip_favre_casa.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Field {
    @JsonProperty("Title")
    String Title;
    @JsonProperty("Code")
    String Code;
    @JsonProperty("Value")
    String Value;
    @JsonProperty("IsRequired")
    boolean IsRequired;
    @JsonProperty("IsReadOnly")
    boolean IsReadOnly;
    @JsonProperty("IsVisible")
    boolean IsVisible;

    @Override
    public String toString() {
        return "Field{" +
                "Title='" + Title + '\'' +
                ", Code='" + Code + '\'' +
                ", Value='" + Value + '\'' +
                ", IsRequired=" + IsRequired +
                ", IsReadOnly=" + IsReadOnly +
                ", IsVisible=" + IsVisible +
                '}';
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    public boolean isRequired() {
        return IsRequired;
    }
}
