package like;

public class LikeVO {

    private int id;

    private int member_id;

    private int order_id;

    private int product_id;

    private int likes;

    @Override
    public String toString() {
        return "LikeVO{" +
                "id=" + id +
                ", member_id=" + member_id +
                ", order_id=" + order_id +
                ", product_id=" + product_id +
                ", likes=" + likes +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
