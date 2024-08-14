package like;

import java.sql.*;

public class LikeDAOImpl implements LikeDAO {

    private static final String DRIVER_NAME = "oracle.jdbc.OracleDriver";
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "hr";
    private static final String PASSWORD = "hi123456";

    static {
        try {
            Class.forName(DRIVER_NAME);
            System.out.println("Driver loaded successfully...");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver class not found.", e);
        }
    }

    @Override
    public int addLike(LikeVO like) {
        if (isLiked(like)) {
            return 0;
        }

        int flag = 0;
        String sql = "INSERT INTO shop_like(id, member_id, product_id, order_id, likes) VALUES (shop_like_seq.NEXTVAL, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, like.getMember_id());
            pstmt.setInt(2, like.getProduct_id());
            pstmt.setInt(3, like.getOrder_id());
            pstmt.setString(4, "default");

            flag = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return flag;
    }



    @Override
    public boolean isLiked(LikeVO like) {
        boolean liked = false;
        String sql = "SELECT COUNT(*) FROM shop_like WHERE member_id = ? AND product_id = ? AND order_id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, like.getMember_id());
            pstmt.setInt(2, like.getProduct_id());
            pstmt.setInt(3, like.getOrder_id());

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    liked = rs.getInt(1) > 0;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return liked;
    }

    @Override
    public int getLikeCount(int productId) {
        int likeCount = 0;
        String sql = "SELECT COUNT(*) FROM shop_like WHERE product_id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, productId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    likeCount = rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return likeCount;
    }
}
