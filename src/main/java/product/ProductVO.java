package product;

public class ProductVO {
    private int product_id;
    private String name;
    private String content;
    private String price;
    private String company;
    private String img;
    private int likes; // New field for likes

    // Getters and setters
    public int getProduct_id() { return product_id; }
    public void setProduct_id(int product_id) { this.product_id = product_id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getPrice() { return price; }
    public void setPrice(String price) { this.price = price; }

    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }

    public String getImg() { return img; }
    public void setImg(String img) { this.img = img; }

    public int getLikes() { return likes; }
    public void setLikes(int likes) { this.likes = likes; }
}