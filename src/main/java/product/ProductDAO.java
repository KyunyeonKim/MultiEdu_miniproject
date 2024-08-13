package product;

import orders.OrdersVO;

import java.util.List;

public interface ProductDAO {

    public int insert(ProductVO vo);
    public int update(ProductVO vo);
    public int delete(ProductVO vo);
    public ProductVO selectOne(ProductVO vo);
    public List<ProductVO> adminSelectAll();
    public List<ProductVO>adminsearchList(String searchKey, String searchWord);

}