package com.simple.server.auto.generic.dto;

import java.io.Serializable;

public class RecommendDetailRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long              id;

    private String            name;

    private String            image;

    private String            description;

    private String            path;

    private Long              positionId;

    public RecommendDetailRequest() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getPositionId() {
        return this.positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

}
