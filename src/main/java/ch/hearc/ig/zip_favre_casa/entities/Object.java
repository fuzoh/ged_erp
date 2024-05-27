package ch.hearc.ig.zip_favre_casa.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Object {
    @JsonProperty("ObjectID")
    int ObjectID;
    @JsonProperty("ContentTypeID")
    int ContentTypeID;
    @JsonProperty("IsInWorkflow")
    boolean IsInWorkflow;
    @JsonProperty("Fields")
    List<Field> Fields;
    @JsonProperty("ContentType")
    String ContentType;
    @JsonProperty("Version")
    String Version;
    @JsonProperty("Author")
    String Author;
    @JsonProperty("CreationDate")
    String CreationDate;
    @JsonProperty("Extension")
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

    public int getObjectID() {
        return ObjectID;
    }

    public void setObjectID(int objectID) {
        ObjectID = objectID;
    }

    public int getContentTypeID() {
        return ContentTypeID;
    }

    public void setContentTypeID(int contentTypeID) {
        ContentTypeID = contentTypeID;
    }

    public boolean isInWorkflow() {
        return IsInWorkflow;
    }

    public void setInWorkflow(boolean inWorkflow) {
        IsInWorkflow = inWorkflow;
    }

    public List<Field> getFields() {
        return Fields;
    }

    public void setFields(List<Field> fields) {
        Fields = fields;
    }

    public String getContentType() {
        return ContentType;
    }

    public void setContentType(String contentType) {
        ContentType = contentType;
    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String version) {
        Version = version;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getCreationDate() {
        return CreationDate;
    }

    public void setCreationDate(String creationDate) {
        CreationDate = creationDate;
    }

    public String getExtension() {
        return Extension;
    }

    public void setExtension(String extension) {
        Extension = extension;
    }
}
