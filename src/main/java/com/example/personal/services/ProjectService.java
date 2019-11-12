package com.example.personal.services;

import com.example.personal.database.DatabaseAccess;
import com.example.personal.database.schema.Project;
import com.example.personal.exceptions.BadRequestError;
import com.example.personal.exceptions.NotFoundError;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

import static com.example.personal.database.DatabaseAccess.PERSONAL_DB;
import static com.example.personal.database.DatabaseAccess.PROJECT_COLLECTION;

public class ProjectService {
    private MongoCollection collection;

    public ProjectService() {
        DatabaseAccess access = DatabaseAccess.getInstance();
        collection = access.getDatabase(PERSONAL_DB).getCollection(PROJECT_COLLECTION);
    }

    public List<Project> getProjects() {
        List<Project> projects = new ArrayList<>();

        MongoCursor cursor = collection.find().iterator();
        while (cursor.hasNext()) {
            projects.add(Project.parseDocument((Document) cursor.next()));
        }

        return projects;
    }

    public Project getProject(String id) throws Exception {
        Document doc = (Document) collection.find(eq("_id", new ObjectId(id))).first();
        if (doc == null) {
            throw new NotFoundError(id, Project.TYPE);
        }
        return Project.parseDocument(doc);
    }

    public Project createProject(Project project) throws Exception {
        checkMissingFields(project);
        Document doc = project.createDocument();
        collection.insertOne(doc);
        return project;
    }

    public Project updateProject(String id, Project project) throws Exception {
        getProject(id);
        if (!project.getId().equals(id)) {
            throw new BadRequestError("ID in route does not match the Project ID");
        }
        checkMissingFields(project);

        collection.findOneAndUpdate(eq("_id", new ObjectId(id)), new Document("$set", project.createDocument()));
        return project;
    }

    public void checkMissingFields(Project project) throws Exception {
        List<String> missing = new ArrayList<>();
        if (project.getDescription() == null)
            missing.add("Description");
        if (project.getName() == null)
            missing.add("Name");
        if (project.getStart() == null)
            missing.add("Start Date");

        if (missing.size() != 0)
            throw new BadRequestError(missing, Project.TYPE);
    }
}
