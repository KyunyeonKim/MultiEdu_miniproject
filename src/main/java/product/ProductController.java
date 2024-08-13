package product;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@WebServlet({
        "/p_insert.do", "/p_update.do", "/p_delete.do",
        "/p_selectOne.do", "/p_selectAll.do", "/p_searchList.do",
        "/p_insertOK.do", "/p_updateOK.do", "/p_deleteOK.do"
})
@MultipartConfig
public class ProductController extends HttpServlet {

    private ProductDAO dao = new ProductDAOImpl();
    private final String UPLOAD_DIRECTORY = "upload";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String sPath = request.getServletPath();
        System.out.println("sPath:" + sPath);

        // 분기 처리
        if (sPath.equals("/p_insert.do")) {
            RequestDispatcher rd = request.getRequestDispatcher("product/insert.jsp");
            rd.forward(request, response);
        } else if (sPath.equals("/p_update.do")) {
            String productId = request.getParameter("product_id");
            System.out.println(productId);

            ProductVO vo = new ProductVO();
            vo.setProduct_id(Integer.parseInt(productId));

            ProductVO vo2 = dao.selectOne(vo);

            request.setAttribute("vo2", vo2);

            RequestDispatcher rd = request.getRequestDispatcher("product/update.jsp");
            rd.forward(request, response);
        } else if (sPath.equals("/p_delete.do")) {
            RequestDispatcher rd = request.getRequestDispatcher("product/delete.jsp");
            rd.forward(request, response);
        } else if (sPath.equals("/p_selectOne.do")) {
            String productId = request.getParameter("product_id");
            System.out.println(productId);

            ProductVO vo = new ProductVO();
            vo.setProduct_id(Integer.parseInt(productId));

            ProductVO vo2 = dao.selectOne(vo);

            request.setAttribute("vo2", vo2);

            RequestDispatcher rd = request.getRequestDispatcher("product/selectOne.jsp");
            rd.forward(request, response);
        } else if (sPath.equals("/p_selectAll.do")) {
            List<ProductVO> list = dao.adminSelectAll();
            request.setAttribute("list", list);

            RequestDispatcher rd = request.getRequestDispatcher("product/selectAll.jsp");
            rd.forward(request, response);
        } else if (sPath.equals("/p_searchList.do")) {
            String searchKey = request.getParameter("searchKey");
            String searchWord = request.getParameter("searchWord");
            System.out.println(searchKey);
            System.out.println(searchWord);

            List<ProductVO> list = dao.adminsearchList(searchKey, searchWord);
            request.setAttribute("list", list);

            RequestDispatcher rd = request.getRequestDispatcher("product/selectAll.jsp");
            rd.forward(request, response);
        } else if (sPath.equals("/p_insertOK.do")) {
            // 파일 업로드를 위한 multipart request 처리
            Part filePart = request.getPart("img"); // Retrieve the file part
            String fileName = UUID.randomUUID().toString() + "_" + Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String filePath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY + File.separator + fileName;

            // Save file to the server
            File uploadDir = new File(getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            filePart.write(filePath);

            // Retrieve other form parameters
            String productId = request.getParameter("product_id");
            String name = request.getParameter("name");
            String content = request.getParameter("content");
            String price = request.getParameter("price");
            String company = request.getParameter("company");

            System.out.println(productId);
            System.out.println(name);
            System.out.println(content);
            System.out.println(price);
            System.out.println(company);
            System.out.println(fileName);

            ProductVO vo = new ProductVO();
            vo.setProduct_id(Integer.parseInt(productId));
            vo.setName(name);
            vo.setContent(content);
            vo.setPrice(price);
            vo.setCompany(company);
            vo.setImg(fileName); // Save the file name in the database

            int result = dao.insert(vo);
            if (result == 1) {
                System.out.println("Insert succeeded...");
                response.sendRedirect("p_selectAll.do"); // 서블릿 패스
            } else {
                System.out.println("Insert failed...");
                response.sendRedirect("p_insert.do"); // 서블릿 패스
            }

        } else if (sPath.equals("/p_updateOK.do")) {
            // 파일 업로드 처리
            Part filePart = request.getPart("img");
            String fileName = null;
            if (filePart != null && filePart.getSize() > 0) {
                fileName = UUID.randomUUID().toString() + "_" + Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                String filePath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY + File.separator + fileName;
                filePart.write(filePath);
            } else {
                fileName = request.getParameter("existingImg");
            }

            String productId = request.getParameter("product_id");
            String name = request.getParameter("name");
            String content = request.getParameter("content");
            String price = request.getParameter("price");
            String company = request.getParameter("company");

            ProductVO vo = new ProductVO();
            vo.setProduct_id(Integer.parseInt(productId));
            vo.setName(name);
            vo.setContent(content);
            vo.setPrice(price);
            vo.setCompany(company);
            vo.setImg(fileName);

            int result = dao.update(vo);
            if (result == 1) {
                System.out.println("Update succeeded...");
                response.sendRedirect("p_selectOne.do?product_id=" + productId); // 서블릿 패스
            } else {
                System.out.println("Update failed...");
                response.sendRedirect("p_update.do?product_id=" + productId); // 서블릿 패스
            }

        } else if (sPath.equals("/p_deleteOK.do")) {
            String productId = request.getParameter("product_id");
            System.out.println(productId);

            ProductVO vo = new ProductVO();
            vo.setProduct_id(Integer.parseInt(productId));

            int result = dao.delete(vo);
            if (result == 1) {
                System.out.println("Delete succeeded...");
                response.sendRedirect("p_selectAll.do"); // 서블릿 패스
            } else {
                System.out.println("Delete failed...");
                response.sendRedirect("p_delete.do?product_id=" + productId); // 서블릿 패스
            }
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
