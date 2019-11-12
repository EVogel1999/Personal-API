package com.example.personal.database.schema;

import org.bson.types.ObjectId;

public class Project {
    private ObjectId _id;
    private String name;
    private String description;
    private long start;
    private long end;
    private String image;
    private String[] tags;
    private String repository;

    public Project() {
        _id = new ObjectId();
        name = "";
        description = "";
        start = 0;
        end = 0;
        image = "";
        tags = new String[0];
        repository = "";
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
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

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

}
