package orders;

import member.MemberVO;

import java.util.List;

public interface OrdersDAO {

    public int insert(OrdersVO vo);
    public int update(OrdersVO vo);
    public int delete(OrdersVO vo);
    public OrdersVO selectOne(OrdersVO vo);
    public List<OrdersVO> adminSelectAll();
    public List<OrdersVO>adminsearchList(String searchKey, String searchWord);

}
