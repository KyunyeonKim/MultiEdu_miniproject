package member;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import product.ProductDAO;
import product.ProductDAOImpl;
import product.ProductVO;

import java.io.IOException;
import java.util.List;

@WebServlet({"/m_insert.do", "/m_update.do", "/m_logout.do", "/m_insertOK.do",
        "/m_selectAll.do", "/m_updateOK.do", "/m_login.do", "/m_loginOK.do","/m_head.do"})
public class MemberController extends HttpServlet {
    private MemberDao dao = new MemberDAOImpl();
    private ProductDAO dao2 = new ProductDAOImpl();

    private boolean isLoggedIn(HttpSession session) {
        return session != null && session.getAttribute("loggedInUser") != null;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String sPath = request.getServletPath();
        System.out.println("sPath: " + sPath);

        HttpSession session = request.getSession(false);

        if (sPath.equals("/m_insert.do")) {
            RequestDispatcher rd = request.getRequestDispatcher("member/insert.jsp");
            rd.forward(request, response);
        } else if (sPath.equals("/m_head.do")) {
            List<ProductVO> list = dao2.adminSelectAll();
            request.setAttribute("list", list);
            RequestDispatcher rd = request.getRequestDispatcher("product/selectAll.jsp");
            rd.forward(request, response);
        } else if (sPath.equals("/m_update.do")) {
            if (!isLoggedIn(session)) {
                response.sendRedirect("m_login.do");
                return;
            }

            String memberId = (String) session.getAttribute("loggedInUser");
            System.out.println(memberId);

            MemberVO vo = new MemberVO();
            vo.setMember_id(memberId);

            MemberVO vo2 = dao.selectOne(vo);

            request.setAttribute("vo2", vo2);

            RequestDispatcher rd = request.getRequestDispatcher("member/update.jsp");
            rd.forward(request, response);
        } else if (sPath.equals("/m_updateOK.do")) {
            if (!isLoggedIn(session)) {
                response.sendRedirect("m_login.do");
                return;
            }

            // 세션에서 member_id 가져오기
            String memberId = (String) session.getAttribute("loggedInUser");
            String pw = request.getParameter("pw");
            String tel = request.getParameter("tel");
            String name = request.getParameter("name");

            MemberVO vo = new MemberVO();
            vo.setMember_id(memberId);
            vo.setPw(pw);
            vo.setTel(tel);
            vo.setName(name);

            int result = dao.update(vo);
            if (result == 1) {
                List<ProductVO> list = dao2.adminSelectAll();
                request.setAttribute("list", list);
                RequestDispatcher rd = request.getRequestDispatcher("product/selectAll.jsp");
                rd.forward(request, response);
            } else {
                System.out.println("Update failed...");
                response.sendRedirect("m_update.do?error=1");
            }
        } else if (sPath.equals("/m_logout.do")) {
            if (session != null) {
                session.invalidate();
            }
            List<ProductVO> list = dao2.adminSelectAll();
            request.setAttribute("list", list);
            RequestDispatcher rd = request.getRequestDispatcher("product/selectAll.jsp");
            rd.forward(request, response);
        } else if (sPath.equals("/m_login.do")) {
            // 이미 로그인된 경우 다른 페이지로 리다이렉트
            if (isLoggedIn(session)) {
                response.sendRedirect("product/selectAll.jsp");
                return;
            }

            RequestDispatcher rd = request.getRequestDispatcher("member/login.jsp");
            rd.forward(request, response);
        } else if (sPath.equals("/m_loginOK.do")) {
            String member_id = request.getParameter("member_id");
            String pw = request.getParameter("pw");

            int loginResult = dao.login(member_id, pw);

            if (loginResult == 1) {
                HttpSession newSession = request.getSession(true);
                newSession.setAttribute("loggedInUser", member_id);

                List<ProductVO> list = dao2.adminSelectAll();
                request.setAttribute("list", list);

                RequestDispatcher rd = request.getRequestDispatcher("product/selectAll.jsp");
                rd.forward(request, response);
            } else {
                response.sendRedirect("m_login.do?error=1");
            }
        } else if (sPath.equals("/m_insertOK.do")) {
            String memberId = request.getParameter("member_id");
            String pw = request.getParameter("pw");
            String tel = request.getParameter("tel");
            String name = request.getParameter("name");

            MemberVO vo = new MemberVO();
            vo.setMember_id(memberId);
            vo.setPw(pw);
            vo.setTel(tel);
            vo.setName(name);

            int result = dao.insert(vo);
            if (result == 1) {
                System.out.println("Member inserted");
                List<ProductVO> list = dao2.adminSelectAll();
                request.setAttribute("list", list);
                RequestDispatcher rd = request.getRequestDispatcher("product/selectAll.jsp");
                rd.forward(request, response);
            } else {
                System.out.println("Insert failed...");
                response.sendRedirect("m_insert.do");
            }
        } else {
            response.sendRedirect("m_login.do");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        doGet(request, response);
    }

    @Override
    public void destroy() {
        // 자원 해제 코드가 필요한 경우 여기에 추가
    }
}
