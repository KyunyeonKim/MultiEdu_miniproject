package orders;

import product.ProductVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdersDAOImpl implements OrdersDAO {

    private static final String DRIVER_NAME = "oracle.jdbc.OracleDriver";
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "hr";
    private static final String PASSWORD = "hi123456";
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    static {
        try {
            Class.forName(DRIVER_NAME);
            System.out.println("Driver loaded successfully...");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver class not found.", e);
        }
    }
    @Override
    public int insert(OrdersVO vo) {
        System.out.println("insert order");
        int flag = 0;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection established...");

            String sql = "INSERT INTO shop_orders(order_id, member_id, product_id, order_date) " +
                    "VALUES(shop_orders_seq.NEXTVAL, ?, ?, SYSDATE)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, vo.getMember_id());
            pstmt.setInt(2, vo.getProduct_id());

            System.out.println("Executing SQL: " + sql);
            System.out.println("Member ID: " + vo.getMember_id());
            System.out.println("Product ID: " + vo.getProduct_id());

            flag = pstmt.executeUpdate();
            System.out.println("Insert flag: " + flag);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeResources();
        }
        return flag;
    }


    @Override
    public int delete(OrdersVO vo) {
        System.out.println("delete order");
        int flag = 0;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection established...");

            String sql = "DELETE FROM shop_orders WHERE order_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, vo.getOrder_id());

            flag = pstmt.executeUpdate();
            System.out.println("Delete flag: " + flag);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeResources();
        }
        return flag;
    }

    @Override
    public List<OrdersSeachVO> OrderSellectAll(String member_id) {
        System.out.println("orderSelectALL");
        List<OrdersSeachVO> list = new ArrayList<>();
        String sql =
                "SELECT\n" +
                        "    o.order_id,\n" +
                        "    p.product_id,\n" +
                        "    p.name,\n" +
                        "    p.content,\n" +
                        "    p.price,\n" +
                        "    p.company,\n" +
                        "    p.img,\n" +
                        "    o.order_date\n" +
                        "FROM\n" +
                        "    shop_orders o\n" +
                        "INNER JOIN\n" +
                        "    shop_product p ON o.product_id = p.product_id\n" +
                        "WHERE\n" +
                        "    o.member_id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, member_id);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    OrdersSeachVO order = new OrdersSeachVO();
                    order.setOrder_id(rs.getInt("order_id"));
                    order.setProduct_id(rs.getInt("product_id"));
                    order.setName(rs.getString("name"));
                    order.setContent(rs.getString("content"));
                    order.setPrice(rs.getInt("price")); // 가격을 int로 가져오므로 데이터 타입 맞춤
                    order.setCompany(rs.getString("company"));
                    order.setImg(rs.getString("img"));
                    order.setOrder_date(rs.getString("order_date")); // 날짜를 문자열로 가져옵니다
                    list.add(order);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error selecting all orders.", e);
        }
        return list;
    }


    private void closeResources() {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
