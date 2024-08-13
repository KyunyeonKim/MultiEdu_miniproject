package member;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAOImpl implements MemberDao{


    private static final String DRIVER_NAME = "oracle.jdbc.OracleDriver";
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "hr";
    private static final String PASSWORD = "hi123456";
    private Connection conn;//커넥션객체
    private PreparedStatement pstmt;//쿼리(sql문-CRUD)실행객체
    private ResultSet rs;//select문 리턴 객체


    @Override
    public int insert(MemberVO vo) {
        System.out.println("member추가");
        System.out.println(vo);
        int flag = 0;

        try {
            conn = DriverManager.getConnection(URL,USER,PASSWORD);
            System.out.println("conn successed...");

            String sql = "insert into shop_member(member_id,pw,name,tel) " +
                    "values(?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,vo.getMember_id());
            pstmt.setString(2,vo.getPw());
            pstmt.setString(3,vo.getName());
            pstmt.setString(4,vo.getTel());

            flag = pstmt.executeUpdate();
            System.out.println("flag : "+ flag);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {

            if(pstmt!=null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(conn!=null) {
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
    public int update(MemberVO vo) {
        System.out.println("update");
        int flag = 0;
        try {
            conn = DriverManager.getConnection(URL,USER,PASSWORD);
            System.out.println("conn successed...");

            String sql = "update shop_member set pw=?,name=?,tel=? " +
                    " where member_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,vo.getPw());
            pstmt.setString(2,vo.getName());
            pstmt.setString(3,vo.getTel());
            pstmt.setString(4,vo.getMember_id());
            flag = pstmt.executeUpdate();
            System.out.println("flag : "+ flag);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {

            if(pstmt!=null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(conn!=null) {
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
    public int delete(MemberVO vo) {
        System.out.println("delete()....");
        System.out.println(vo);
        int flag = 0;
        try {
            conn = DriverManager.getConnection(URL,USER,PASSWORD);
            System.out.println("conn successed...");

            String sql = "delete from shop_member " +
                    " where member_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,vo.getMember_id());

            flag = pstmt.executeUpdate();
            System.out.println("flag : "+ flag);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {

            if(pstmt!=null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(conn!=null) {
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
    public MemberVO selectOne(MemberVO vo) {
        System.out.println("selectOne()....");
        System.out.println(vo);
        MemberVO vo2 = null;
        //3-2 : 커넥션
        try {
            conn = DriverManager.getConnection(URL,USER,PASSWORD);
            System.out.println("conn successed...");

            String sql = "select * from shop_member where member_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,vo.getMember_id());

            rs = pstmt.executeQuery();

            while (rs.next()){
                vo2 = new MemberVO();
                vo2.setMember_id(rs.getString("member_id"));
                vo2.setPw(rs.getString("pw"));
                vo2.setName(rs.getString("name"));
                vo2.setTel(rs.getString("tel"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if(rs!=null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(pstmt!=null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(conn!=null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return vo2;
    }

    @Override
    public List<MemberVO> adminSelectAll() {
        System.out.println("selectAll()");

        List<MemberVO>list = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(URL,USER,PASSWORD);
            System.out.println("conn successed...");

            //4. 쿼리문 전달(요청)
            String sql = "select * from shop_member order by num desc";
            pstmt = conn.prepareStatement(sql);

            //5. 반환(응답)
            rs = pstmt.executeQuery();//select문에서 만 사용하는 함수

            //6. rs >>> list에 할당
            while(rs.next()){//읽어들일 행이 있으면 true
                MemberVO vo = new MemberVO();
                vo.setMember_id(rs.getString("member_id"));
                vo.setPw(rs.getString("pw"));
                vo.setName(rs.getString("name"));
                vo.setTel(rs.getString("tel"));
                list.add(vo);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if(rs!=null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(pstmt!=null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(conn!=null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }


        return list;
    }

    @Override
    public List<MemberVO> adminsearchList(String searchKey, String searchWord) {
        System.out.println("searchList()...");
        System.out.println(searchKey);
        System.out.println(searchWord);

        List<MemberVO>list = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(URL,USER,PASSWORD);
            System.out.println("conn successed...");
            String sql = "";
            if(searchKey.equals("name")){
                sql = "select * from shop_member where name like ? order by num desc";
            }else if(searchKey.equals("id")){
                sql = "select * from shop_member where id like ? order by num desc";
            }

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,"%"+searchWord+"%");


            rs = pstmt.executeQuery();//select문에서 만 사용하는 함수


            while(rs.next()){//읽어들일 행이 있으면 true
                MemberVO vo = new MemberVO();
                vo.setMember_id(rs.getString("member_id"));
                vo.setPw(rs.getString("pw"));
                vo.setName(rs.getString("name"));
                vo.setTel(rs.getString("tel"));
                list.add(vo);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if(rs!=null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(pstmt!=null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(conn!=null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return list;
    }

    @Override
    public int login(String member_id, String pw) {
        String sql = "SELECT COUNT(*) FROM shop_member WHERE member_id = ? AND pw = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // SQL 쿼리 준비
            pstmt.setString(1, member_id);
            pstmt.setString(2, pw);

            // 쿼리 실행
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0 ? 1 : 0; // 로그인 성공 또는 실패
                }
                return -1; // 쿼리 실행 실패
            }
        } catch (SQLException e) {
            e.printStackTrace(); // 로그를 남기세요
            return -1; // 예외 발생 시 실패 코드 반환
        }
    }

    public boolean checkPw(String member_id , String pw) {
        boolean isAvailable = true;

        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "SELECT COUNT(*) FROM shop_member WHERE id =? pw=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, member_id);
            pstmt.setString(2, pw);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    isAvailable = false;
                }
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

        return isAvailable;
    }
}
