package ch.hearc.ig.zip_favre_casa.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class Field {
    String Title;
    String Code;
    String Value;
    boolean IsRequired;
    boolean IsReadOnly;
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
}
