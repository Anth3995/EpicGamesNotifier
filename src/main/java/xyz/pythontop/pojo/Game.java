package xyz.pythontop.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Game {

    @JsonProperty(value = "id")
    private String id;

    @JsonProperty(value = "title")
    private String title;

    @JsonProperty(value = "effectiveDate")
    private LocalDateTime effectiveDate;

    @JsonProperty(value = "productSlug")
    private String productSlug;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDateTime effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getProductSlug() {
        return productSlug;
    }

    public void setProductSlug(String productSlug) {
        this.productSlug = productSlug;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", effectiveDate=" + effectiveDate +
                ", productSlug='" + productSlug + '\'' +
                '}';
    }
}
