package com.ming.site.model;

import com.mybatisflex.annotation.Id;

public class Value
        implements IdLongPrimaryKey {
    @Id
    private Long id;
    private String title;
    private String icon;
    private Long optionId;
    private Option option;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Long getOptionId() {
        return optionId;
    }

    public void setOptionId(Long optionId) {
        this.optionId = optionId;
    }

    public Option getVariant() {
        return option;
    }

    public void setVariant(Option option) {
        this.option = option;
    }
}
