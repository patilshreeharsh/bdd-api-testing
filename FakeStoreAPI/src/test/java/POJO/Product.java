package POJO;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private int id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("price")
    private double price;

    @JsonProperty("description")
    private String description;

    @JsonProperty("category")
    private String category;

    @JsonProperty("image")
    private String image;

    // Default constructor
    public Product() {
    }

    // Parameterized constructor
    public Product(int id, String title, double price, String description, String category, String image) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.category = category;
        this.image = image;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
               Double.compare(product.price, price) == 0 &&
               Objects.equals(title, product.title) &&
               Objects.equals(description, product.description) &&
               Objects.equals(category, product.category) &&
               Objects.equals(image, product.image);
    }

    // hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(id, title, price, description, category, image);
    }

    // toString method
    @Override
    public String toString() {
        return "Product{" +
               "id=" + id +
               ", title='" + title + '\'' +
               ", price=" + price +
               ", description='" + description + '\'' +
               ", category='" + category + '\'' +
               ", image='" + image + '\'' +
               '}';
    }

    // Builder pattern for easy object creation
    public static class ProductBuilder {
        private int id;
        private String title;
        private double price;
        private String description;
        private String category;
        private String image;

        public ProductBuilder withId(int id) {
            this.id = id;
            return this;
        }

        public ProductBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public ProductBuilder withPrice(double price) {
            this.price = price;
            return this;
        }

        public ProductBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public ProductBuilder withCategory(String category) {
            this.category = category;
            return this;
        }

        public ProductBuilder withImage(String image) {
            this.image = image;
            return this;
        }

        public Product build() {
            return new Product(id, title, price, description, category, image);
        }
    }

    // Static method to create builder
    public static ProductBuilder builder() {
        return new ProductBuilder();
    }
}
