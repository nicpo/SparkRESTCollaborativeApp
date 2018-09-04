package com.np;

import com.univocity.parsers.annotations.Parsed;
import com.univocity.parsers.annotations.Trim;

import java.io.Serializable;

/**
 * For file fb.csv
 */
public class FacebookLikesBean implements Serializable {
    @Parsed(index = 0)
    public String pageID;

    @Parsed(index = 1)
    public String type;

    @Parsed(index = 2)
    public String dateTime;

    @Parsed(index = 3)
    public String fbid;

    @Trim
    @Parsed(index = 4)
    public String content;

    @Parsed(index = 5, defaultNullRead = "0")
    public Integer likesCount;

    @Parsed(index = 6, defaultNullRead = "0")
    public Integer characterCount;

    public FacebookLikesBean() {
    }

    public String getPageID() {
        return pageID;
    }

    public void setPageID(String pageID) {
        this.pageID = pageID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getFbid() {
        return fbid;
    }

    public void setFbid(String fbid) {
        this.fbid = fbid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(Integer likesCount) {
        this.likesCount = likesCount;
    }

    public Integer getCharacterCount() {
        return characterCount;
    }

    public void setCharacterCount(Integer characterCount) {
        this.characterCount = characterCount;
    }

    @Override
    public String toString() {
        return "FacebookLikesBean{" +
                "pageID='" + pageID + '\'' +
                ", type='" + type + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", fbid='" + fbid + '\'' +
                ", content='" + content + '\'' +
                ", likesCount=" + likesCount +
                ", characterCount=" + characterCount +
                '}';
    }
}
