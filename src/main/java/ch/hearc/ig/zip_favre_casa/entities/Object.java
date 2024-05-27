package ch.hearc.ig.zip_favre_casa.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties
public class Object {
    int ObjectID;
    int ContentTypeID;
    boolean IsInWorkflow;
    List<Field> Fields;
    String ContentType;
    String Version;
    String Author;
    String CreationDate;
    String Extension;

    @Override
    public String toString() {
        return "Object{" +
                "ObjectID=" + ObjectID +
                ", ContentTypeID=" + ContentTypeID +
                ", IsInWorkflow=" + IsInWorkflow +
                ", Fields=" + Fields +
                ", ContentType='" + ContentType + '\'' +
                ", Version='" + Version + '\'' +
                ", Author='" + Author + '\'' +
                ", CreationDate='" + CreationDate + '\'' +
                ", Extension='" + Extension + '\'' +
                '}';
    }
}
