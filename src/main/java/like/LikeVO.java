package like;

public class LikeVO {

    private int id;

    private String member_id; // 수정된 부분

    private int order_id;

    private int product_id;

    private String likes;

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

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

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }
}
