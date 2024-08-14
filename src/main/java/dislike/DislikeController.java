package dislike;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet({
        "/dislike_add.do",
        "/dislike_count.do"
})
public class DislikeController extends HttpServlet {

    private DislikeDAO dislikeDAO = new DislikeDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sPath = request.getServletPath();
        System.out.println("sPath: " + sPath);

        if (sPath.equals("/dislike_add.do")) {
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("loggedInUser") == null) {
                response.sendRedirect("m_login.do");
                return;
            }

            int memberId = Integer.parseInt(session.getAttribute("loggedInUser").toString());
            int productId = Integer.parseInt(request.getParameter("product_id"));
            int orderId = Integer.parseInt(request.getParameter("order_id"));

            DisLikeVO dislike = new DisLikeVO();
            dislike.setMember_id(memberId);
            dislike.setProduct_id(productId);
            dislike.setOrder_id(orderId);
            dislike.setDislikes(1);

            int result = dislikeDAO.addDislike(dislike);
            if (result == 1) {
                response.sendRedirect("p_selectAll.do");
            } else {
                request.setAttribute("errorMessage", "이미 싫어요를 추가한 상품입니다.");
                RequestDispatcher rd = request.getRequestDispatcher("errorPage.jsp");
                rd.forward(request, response);
            }
        } else if (sPath.equals("/dislike_count.do")) {
            int productId = Integer.parseInt(request.getParameter("product_id"));
            int dislikeCount = dislikeDAO.getDislikeCount(productId);

            request.setAttribute("dislikeCount", dislikeCount);
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
