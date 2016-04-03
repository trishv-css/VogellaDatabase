package com.vogella.vogelladatabase;

/**
 * Created by Trish Valeri on 4/1/2016.
 */
public class Comment {

    private long id;
    private String comment;
    private String rating;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return comment + " " + rating;
    }
}
