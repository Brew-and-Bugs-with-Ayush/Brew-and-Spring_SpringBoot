package com.Ayush.RestApi.Entity;

public class RestApiEntity {

    private long id;
    private String name;

    public RestApiEntity() {
    }

    public RestApiEntity(long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    private String email;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "RestApiEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
