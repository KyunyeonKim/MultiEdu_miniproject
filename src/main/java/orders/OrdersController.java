package orders;

import com.oracle.wls.shaded.org.apache.xpath.operations.Or;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import product.ProductDAO;
import product.ProductDAOImpl;
import product.ProductVO;

import java.io.IOException;
import java.util.List;

@WebServlet({"/o_insert.do", "/o_update.do", "/o_logout.do", "/o_insertOK.do",
        "/o_selectAll.do", "/o_updateOK.do", "/o_login.do", "/o_loginOK.do","/o_pselectAll.do"})
public class OrdersController extends HttpServlet {
    private OrdersDAO dao = new OrdersDAOImpl();
    private ProductDAO dao2 = new ProductDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String sPath = request.getServletPath();
        System.out.println("sPath: " + sPath);

        if (sPath.equals("/o_insert.do")) {
            List<ProductVO> list = dao2.adminSelectAll();
            request.setAttribute("list", list);
            RequestDispatcher rd = request.getRequestDispatcher("product/selectAll.jsp");
            rd.forward(request, response);
        } else if (sPath.equals("/o_insertOK.do")) {
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("loggedInUser") == null) {
                // 세션이 없거나 로그인된 사용자가 없는 경우 로그인 페이지로 리다이렉트
                response.sendRedirect("m_login.do");
                return;
            }

            // 세션에서 memberId 가져오기
            String memberId = (String) session.getAttribute("loggedInUser");
            int productId = Integer.parseInt(request.getParameter("product_id"));

            OrdersVO vo = new OrdersVO();
            vo.setMember_id(memberId);
            vo.setProduct_id(productId);

            int result = dao.insert(vo);
            if (result == 1) {
                System.out.println("Order successfully placed");
                response.sendRedirect("p_selectAll.do"); // 주문 성공 후 상품 목록 페이지로 이동
            } else {
                System.out.println("Order failed");
                response.sendRedirect("p_selectAll.do?error=1"); // 주문 실패 시 에러 메시지 전달
            }
        } else if  (sPath.equals("/o_pselectAll.do")) {
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("loggedInUser") == null) {
                // 세션이 없거나 로그인된 사용자가 없는 경우 로그인 페이지로 리다이렉트
                response.sendRedirect("m_login.do");
                return;
            }

            // 세션에서 member_id 가져오기
            String memberId = (String) session.getAttribute("loggedInUser");

            // OrdersDAO의 OrderSellectAll 메서드 호출


            List<OrdersSeachVO> ordersList = dao.OrderSellectAll(memberId);

            // 요청 속성에 ordersList를 설정
            request.setAttribute("ordersList", ordersList);

            // JSP 페이지로 포워딩
            RequestDispatcher rd = request.getRequestDispatcher("order/orderSelectAll.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
