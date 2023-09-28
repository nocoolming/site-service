package com.ming.site.model;

import com.baomidou.mybatisplus.annotation.TableField;

import java.time.LocalDateTime;

public class Comment implements IdLongPrimaryKey {

    private  long id;
    private String content;
    private LocalDateTime createAt;


    @TableField(exist = false)
    private User createUser;

    @TableField(exist = false)
    private Note note;

    @TableField(exist = false)
    private Product product;

    @TableField(exist = false)
    private Comment comment;


    private long createUserId;

    public long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(long createUserId) {
        this.createUserId = createUserId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
}
