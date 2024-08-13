package comment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDAOImpl implements CommentDAO {

    private static final String DRIVER_NAME = "oracle.jdbc.OracleDriver";
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "hr";
    private static final String PASSWORD = "hi123456";
    private Connection conn;  // 커넥션 객체
    private PreparedStatement pstmt;  // 쿼리(sql문-CRUD) 실행 객체
    private ResultSet rs;  // select문 리턴 객체

    @Override
    public int commentinsert(CommentVO vo) {
        System.out.println("commentinsert 추가");
        System.out.println(vo);
        int flag = 0;

        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("conn successed...");

            String sql = "INSERT INTO shop_comment(id, member_id, product_id, title, content) " +
                    "VALUES(shop_comment_seq.NEXTVAL, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, vo.getMember_id());
            pstmt.setInt(2, vo.getProduct_id());
            pstmt.setString(3, vo.getTitle());
            pstmt.setString(4, vo.getContent());

            flag = pstmt.executeUpdate();
            System.out.println("flag : " + flag);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return flag;
    }

    @Override
    public int commentupdate(CommentVO vo) {
        System.out.println("commentupdate 수정");
        int flag = 0;

        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("conn successed...");

            String sql = "UPDATE shop_comment SET title = ?, content = ? WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, vo.getTitle());
            pstmt.setString(2, vo.getContent());
            pstmt.setInt(3, vo.getId());

            flag = pstmt.executeUpdate();
            System.out.println("flag : " + flag);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return flag;
    }

    @Override
    public int commentdelete(CommentVO vo) {
        System.out.println("commentdelete 삭제");
        int flag = 0;

        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("conn successed...");

            String sql = "DELETE FROM shop_comment WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, vo.getId());

            flag = pstmt.executeUpdate();
            System.out.println("flag : " + flag);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return flag;
    }

    @Override
    public List<CommentVO> selectAll() {
        System.out.println("selectAll 조회");
        List<CommentVO> commentList = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("conn successed...");

            String sql = "SELECT * FROM shop_comment ORDER BY id DESC";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                CommentVO comment = new CommentVO();
                comment.setId(rs.getInt("id"));
                comment.setMember_id(rs.getString("member_id"));
                comment.setProduct_id(rs.getInt("product_id"));
                comment.setTitle(rs.getString("title"));
                comment.setContent(rs.getString("content"));
                commentList.add(comment);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return commentList;
    }
}
