package orders;

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

            flag = pstmt.executeUpdate();
            System.out.println("Insert flag: " + flag);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeResources();
        }
        return flag;
    }

    @Override
    public int update(OrdersVO vo) {
        System.out.println("update order");
        int flag = 0;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection established...");

            String sql = "UPDATE shop_orders SET member_id = ?, product_id = ?, order_date = ? WHERE order_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, vo.getMember_id());
            pstmt.setInt(2, vo.getProduct_id());
            pstmt.setDate(3, Date.valueOf(vo.getOrder_date())); // java.sql.Date 사용
            pstmt.setInt(4, vo.getOrder_id());

            flag = pstmt.executeUpdate();
            System.out.println("Update flag: " + flag);
        } catch (SQLException e) {
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
    public OrdersVO selectOne(OrdersVO vo) {
        System.out.println("select one order");
        OrdersVO order = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection established...");

            String sql = "SELECT * FROM shop_orders WHERE order_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, vo.getOrder_id());

            rs = pstmt.executeQuery();

            if (rs.next()) {
                order = new OrdersVO();
                order.setOrder_id(rs.getInt("order_id"));
                order.setMember_id(rs.getString("member_id"));
                order.setProduct_id(rs.getInt("product_id"));
                order.setOrder_date(rs.getDate("order_date").toString()); // DATE 타입은 getDate()로 가져옴
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeResources();
        }
        return order;
    }

    @Override
    public List<OrdersVO> adminSelectAll() {
        System.out.println("select all orders");
        List<OrdersVO> ordersList = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection established...");

            String sql = "SELECT * FROM shop_orders ORDER BY order_id DESC";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                OrdersVO order = new OrdersVO();
                order.setOrder_id(rs.getInt("order_id"));
                order.setMember_id(rs.getString("member_id"));
                order.setProduct_id(rs.getInt("product_id"));
                order.setOrder_date(rs.getDate("order_date").toString());
                ordersList.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeResources();
        }
        return ordersList;
    }

    @Override
    public List<OrdersVO> adminsearchList(String searchKey, String searchWord) {
        System.out.println("search orders");
        List<OrdersVO> ordersList = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection established...");

            String sql = "SELECT * FROM shop_orders WHERE " + searchKey + " LIKE ? ORDER BY order_id DESC";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + searchWord + "%");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                OrdersVO order = new OrdersVO();
                order.setOrder_id(rs.getInt("order_id"));
                order.setMember_id(rs.getString("member_id"));
                order.setProduct_id(rs.getInt("product_id"));
                order.setOrder_date(rs.getDate("order_date").toString());
                ordersList.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeResources();
        }
        return ordersList;
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
