package comment;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet({"/c_update.do", "/c_delete.do",
        "/c_insertOK.do","/c_updateOK.do","/c_deleteOK.do"})
public class CommentController extends HttpServlet {
    private CommentDAO dao = new CommentDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sPath = request.getServletPath();
        System.out.println("sPath:" + sPath);

        if(sPath.equals("/c_insertOK.do")){
            HttpSession session = request.getSession(false);
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
                System.out.println("Insert succeeded...");
                response.sendRedirect("p_selectAll.do"); // 서블릿 패스
            } else {
                System.out.println("Insert failed...");
                response.sendRedirect("p_insert.do"); // 서블릿 패스
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp); // POST 요청을 GET 요청으로 처리하도록 설정
    }
}
