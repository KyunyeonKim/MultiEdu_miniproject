package orders;

import member.MemberVO;

import java.util.List;

public interface OrdersDAO {

    public int insert(OrdersVO vo);
    public int delete(OrdersVO vo);
    public List<OrdersSeachVO>OrderSellectAll(String member_id);
}
