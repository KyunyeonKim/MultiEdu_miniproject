package comment;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet({"/c_update.do", "/c_delete.do",
        "/c_insertOK.do","/c_updateOK.do","/c_deleteOK.do"})
public class CommentController extends HttpServlet {
    private CommentDAO dao = new CommentDAOImpl();

    private boolean isLoggedIn(HttpSession session) {
        return session != null && session.getAttribute("loggedInUser") != null;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sPath = request.getServletPath();
        System.out.println("sPath:" + sPath);

        HttpSession session = request.getSession(false);
        if (sPath.equals("/c_insertOK.do")) {
            if (session == null || session.getAttribute("loggedInUser") == null) {
                response.sendRedirect("m_login.do");
                return;
            }

            String member_id = (String) session.getAttribute("loggedInUser");
            int product_id = Integer.parseInt(request.getParameter("product_id"));
            String title = request.getParameter("title");
            String content = request.getParameter("content");

            CommentVO vo = new CommentVO();
            vo.setMember_id(member_id);
            vo.setProduct_id(product_id);
            vo.setTitle(title);
            vo.setContent(content);

            int result = dao.commentinsert(vo);
            if (result == 1) {
                System.out.println("댓글 등록 성공...");
                response.sendRedirect("p_selectAll.do");
            } else {
                System.out.println("댓글 등록 실패...");
                response.sendRedirect("p_insert.do");
            }
        } else if (sPath.equals("/c_updateOK.do")) {
            if (!isLoggedIn(session)) {
                response.sendRedirect("m_login.do");
                return;
            }
            int id = Integer.parseInt(request.getParameter("id"));
            String memberId = (String) session.getAttribute("loggedInUser");
            int productId = Integer.parseInt((request.getParameter("product_id")));
            String title = (request.getParameter("title"));
            String content = (request.getParameter("content"));

            CommentVO vo = new CommentVO();
            vo.setId(id);
            vo.setMember_id(memberId);
            vo.setProduct_id(productId);
            vo.setTitle(title);
            vo.setContent(content);

            int result = dao.commentupdate(vo);
            if(result == 1){
                System.out.println("댓글 수정 성공");
                response.sendRedirect("p_selectOne.do?product_id=" + productId);
            }else {
                System.out.println("댓글 수정 실패");
                response.sendRedirect("c_update.do?product_id=" + productId);
            }
        }else if (sPath.equals("/c_deleteOK.do")){
            String id= (request.getParameter("comment_id"));
            String productId=request.getParameter("product_id");

            CommentVO vo = new CommentVO();
            vo.setId(Integer.parseInt(id));

            int result = dao.commentdelete(vo);

            if (result == 1) {
                System.out.println("댓글 삭제 성공...");
                response.sendRedirect("p_selectOne.do?product_id=" + productId);
            } else {
                System.out.println("댓글 삭제 실패...");
                response.sendRedirect("p_selectOne.do?product_id=" + productId);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
