package dislike;

import java.sql.*;

public class DislikeDAOImpl implements DislikeDAO {

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
    public int addDislike(DisLikeVO dislike) {

        if (isDisliked(dislike)) {
            return 0;
        }

        int flag = 0;
        String sql = "INSERT INTO shop_dislike(id, member_id, product_id, order_id, dislikes) VALUES (shop_dislike_seq.NEXTVAL, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, dislike.getMember_id());
            pstmt.setInt(2, dislike.getProduct_id());
            pstmt.setInt(3, dislike.getOrder_id());
            pstmt.setInt(4, dislike.getDislikes());

            flag = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return flag;
    }

    @Override
    public boolean isDisliked(DisLikeVO dislike) {
        boolean disliked = false;
        String sql = "SELECT COUNT(*) FROM shop_dislike WHERE member_id = ? AND product_id = ? AND order_id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, dislike.getMember_id());
            pstmt.setInt(2, dislike.getProduct_id());
            pstmt.setInt(3, dislike.getOrder_id());

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    disliked = rs.getInt(1) > 0;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return disliked;
    }

    @Override
    public int getDislikeCount(int productId) {
        int dislikeCount = 0;
        String sql = "SELECT COUNT(*) FROM shop_dislike WHERE product_id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, productId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    dislikeCount = rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return dislikeCount;
    }
}
