package com.project.meetinglive.modal;
import java.io.Serializable;
import java.util.Date;

/**
 * 资源实体类
 * @author hejinguo
 * @version $Id: MenuModel.java, v 0.1 2020年7月20日 上午11:48:16
 */
public class MenuModel implements Serializable {
    private static final long serialVersionUID = 741231858441822688L;

    //========== properties ==========

    /**
     * This property corresponds to db column <tt>ID</tt>.
     */
    private Long              id;

    /**
     * This property corresponds to db column <tt>NAME</tt>.
     */
    private String            name;

    /**
     * This property corresponds to db column <tt>PARENT_ID</tt>.
     */
    private Long              parentId;

    /**
     * This property corresponds to db column <tt>URL</tt>.
     */
    private String            url;

    /**
     * This property corresponds to db column <tt>TYPE</tt>.
     */
    private String            type;

    /**
     * This property corresponds to db column <tt>CREATED_AT</tt>.
     */
    private Date              createdAt;

    /**
     * This property corresponds to db column <tt>CREATED_MAN</tt>.
     */
    private String            createdMan;

    /**
     * This property corresponds to db column <tt>UPDATE_AT</tt>.
     */
    private Date              updatedAt;

    /**
     * This property corresponds to db column <tt>UPDATE_MAN</tt>.
     */
    private String            updatedMan;

    /**
     * This property corresponds to db column <tt>IS_USEABLE</tt>.
     */
    private String            isUseable;

    /**
     * This property corresponds to db column <tt>ORDER_NUM</tt>.
     */
    private Long              orderNum;
    /**
     * This property corresponds to db column <tt>LOAD_TYPE</tt>.
     */
    private String            loadType;

    //========== getters and setters ==========

    /**
     * Getter method for property <tt>id</tt>.
     *
     * @return property value of id
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter method for property <tt>id</tt>.
     * 
     * @param id value to be assigned to property id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter method for property <tt>name</tt>.
     *
     * @return property value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for property <tt>name</tt>.
     * 
     * @param name value to be assigned to property name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method for property <tt>parentId</tt>.
     *
     * @return property value of parentId
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * Setter method for property <tt>parentId</tt>.
     * 
     * @param parentId value to be assigned to property parentId
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * Getter method for property <tt>url</tt>.
     *
     * @return property value of url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Setter method for property <tt>url</tt>.
     * 
     * @param url value to be assigned to property url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Getter method for property <tt>type</tt>.
     *
     * @return property value of type
     */
    public String getType() {
        return type;
    }

    /**
     * Setter method for property <tt>type</tt>.
     * 
     * @param type value to be assigned to property type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Getter method for property <tt>createdAt</tt>.
     *
     * @return property value of createdAt
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * Setter method for property <tt>createdAt</tt>.
     * 
     * @param createdAt value to be assigned to property createdAt
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Getter method for property <tt>createdMan</tt>.
     *
     * @return property value of createdMan
     */
    public String getCreatedMan() {
        return createdMan;
    }

    /**
     * Setter method for property <tt>createdMan</tt>.
     * 
     * @param createdMan value to be assigned to property createdMan
     */
    public void setCreatedMan(String createdMan) {
        this.createdMan = createdMan;
    }

    /**
     * Getter method for property <tt>updateAt</tt>.
     *
     * @return property value of updateAt
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Setter method for property <tt>updateAt</tt>.
     * 
     * @param updateAt value to be assigned to property updateAt
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * Getter method for property <tt>updateMan</tt>.
     *
     * @return property value of updateMan
     */
    public String getUpdatedMan() {
        return updatedMan;
    }

    /**
     * Setter method for property <tt>updateMan</tt>.
     * 
     * @param updateMan value to be assigned to property updateMan
     */
    public void setUpdatedMan(String updatedMan) {
        this.updatedMan = updatedMan;
    }

    /**
     * Getter method for property <tt>isUseable</tt>.
     *
     * @return property value of isUseable
     */
    public String getIsUseable() {
        return isUseable;
    }

    /**
     * Setter method for property <tt>isUseable</tt>.
     * 
     * @param isUseable value to be assigned to property isUseable
     */
    public void setIsUseable(String isUseable) {
        this.isUseable = isUseable;
    }

    /**
     * Getter method for property <tt>orderNum</tt>.
     *
     * @return property value of orderNum
     */
    public Long getOrderNum() {
        return orderNum;
    }

    /**
     * Setter method for property <tt>orderNum</tt>.
     * 
     * @param orderNum value to be assigned to property orderNum
     */
    public void setOrderNum(Long orderNum) {
        this.orderNum = orderNum;
    }

    public String getLoadType() {
        return loadType;
    }

    public void setLoadType(String loadType) {
        this.loadType = loadType;
    }

}
