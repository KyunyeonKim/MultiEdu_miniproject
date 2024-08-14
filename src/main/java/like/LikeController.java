package like;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import like.LikeDAO;
import like.LikeDAOImpl;
import like.LikeVO;

import java.io.IOException;

@WebServlet({
        "/like_add.do",
        "/like_remove.do",
        "/like_count.do"
})
public class LikeController extends HttpServlet {

    private LikeDAO likeDAO = new LikeDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sPath = request.getServletPath();
        System.out.println("sPath: " + sPath);

        if (sPath.equals("/like_add.do")) {
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("loggedInUser") == null) {
                response.sendRedirect("m_login.do");
                return;
            }

            String memberId = (String) session.getAttribute("loggedInUser");
            int productId = Integer.parseInt(request.getParameter("product_id"));
            int orderId = Integer.parseInt(request.getParameter("order_id"));

            LikeVO like = new LikeVO();
            like.setMember_id(memberId);
            like.setProduct_id(productId);
            like.setOrder_id(orderId);

            int result = likeDAO.addLike(like);
            if (result == 1) {
                response.sendRedirect("p_selectAll.do");
            } else {
                request.setAttribute("errorMessage", "이미 좋아요를 추가한 상품입니다.");
                RequestDispatcher rd = request.getRequestDispatcher("errorPage.jsp");
                rd.forward(request, response);
            }
        } else if (sPath.equals("/like_count.do")) {
            int productId = Integer.parseInt(request.getParameter("product_id"));
            int likeCount = likeDAO.getLikeCount(productId);

            request.setAttribute("likeCount", likeCount);
            RequestDispatcher rd = request.getRequestDispatcher("productDetails.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
