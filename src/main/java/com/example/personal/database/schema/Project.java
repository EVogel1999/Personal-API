package com.example.personal.database.schema;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Project {
    private ObjectId _id;
    private String name;
    private String description;
    private Date start;
    private Date end;
    private String image;
    private List<String> tags;
    private String repository;

    public Project() {
        _id = new ObjectId();
        name = "";
        description = "";
        start = new Date();
        end = null;
        image = "";
        tags = new ArrayList<>();
        repository = "";
    }

    public Project(String id, String name, String description, Date start, Date end, String image, List<String> tags, String repository) {
        this._id = new ObjectId(id);
        this.name = name;
        this.description = description;
        this.start = start;
        this.end = end;
        this.image = image;
        this.tags = tags;
        this.repository = repository;
    }

    public String getId() {
        return _id.toHexString();
    }

    public void setId(String _id) {
        this._id = new ObjectId(_id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    public static Project parseDocument(Document doc) {
        String id = doc.getObjectId("_id").toHexString();
        String name = doc.getString("name");
        String description = doc.getString("description");
        Date start = doc.getDate("start");
        Date end = doc.getDate("end");
        String image = doc.getString("image");
        List<String> tags = (List<String>) doc.get("tags");
        String repository = doc.getString("repository");

        return new Project(id, name, description, start, end, image, tags, repository);
    }

    public Document createDocument() {
        Document document = new Document();

        if (_id == null)
            document.append("_id", new ObjectId());
        else
            document.append("_id", _id);
        document.append("name", name);
        document.append("description", description);
        document.append("start", start);
        if (end != null)
            document.append("end", end);
        if (image != null)
            document.append("image", image);
        document.append("tags", tags);
        if (repository != null)
            document.append("repository", repository);

        return document;
    }
}
