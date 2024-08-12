package orders;

import java.util.Date;

public class Orders {

    private int order_id;

    private String member_id;

    private int product_id;

    private Date order_date;

    @Override
    public String toString() {
        return "Orders{" +
                "order_id=" + order_id +
                ", member_id='" + member_id + '\'' +
                ", product_id=" + product_id +
                ", order_date=" + order_date +
                '}';
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }
}
