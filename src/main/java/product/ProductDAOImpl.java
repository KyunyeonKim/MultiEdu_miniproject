package product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {

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
    public int insert(ProductVO vo) {
        String sql = "INSERT INTO shop_product(product_id, name, content, price, company, img) VALUES(?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, vo.getProduct_id());
            pstmt.setString(2, vo.getName());
            pstmt.setString(3, vo.getContent());
            pstmt.setString(4, vo.getPrice());
            pstmt.setString(5, vo.getCompany());
            pstmt.setString(6, vo.getImg());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting product.", e);
        }
    }

    @Override
    public int update(ProductVO vo) {
        String sql = "UPDATE shop_product SET name=?, content=?, price=?, company=?, img=? WHERE product_id=?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, vo.getName());
            pstmt.setString(2, vo.getContent());
            pstmt.setString(3, vo.getPrice());
            pstmt.setString(4, vo.getCompany());
            pstmt.setString(5, vo.getImg());
            pstmt.setInt(6, vo.getProduct_id());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating product.", e);
        }
    }

    @Override
    public int delete(ProductVO vo) {
        String sql = "DELETE FROM shop_product WHERE product_id=?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, vo.getProduct_id());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting product.", e);
        }
    }

    @Override
    public ProductVO selectOne(ProductVO vo) {
        String sql = "SELECT * FROM shop_product WHERE product_id=?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, vo.getProduct_id());

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    ProductVO selectedProduct = new ProductVO();
                    selectedProduct.setProduct_id(rs.getInt("product_id"));
                    selectedProduct.setName(rs.getString("name"));
                    selectedProduct.setContent(rs.getString("content"));
                    selectedProduct.setPrice(rs.getString("price"));
                    selectedProduct.setCompany(rs.getString("company"));
                    selectedProduct.setImg(rs.getString("img"));
                    return selectedProduct;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error selecting product.", e);
        }
        return null;
    }

    @Override
    public List<ProductVO> adminSelectAll() {
        List<ProductVO> productList = new ArrayList<>();
        String sql = "SELECT * FROM shop_product ORDER BY product_id DESC";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                ProductVO product = new ProductVO();
                product.setProduct_id(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setContent(rs.getString("content"));
                product.setPrice(rs.getString("price"));
                product.setCompany(rs.getString("company"));
                product.setImg(rs.getString("img"));
                productList.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error selecting all products.", e);
        }
        return productList;
    }

    @Override
    public List<ProductVO> adminsearchList(String searchKey, String searchWord) {
        List<ProductVO> productList = new ArrayList<>();
        String sql = "";

        if (searchKey.equalsIgnoreCase("name")) {
            sql = "SELECT * FROM shop_product WHERE name LIKE ? ORDER BY product_id DESC";
        } else if (searchKey.equalsIgnoreCase("company")) {
            sql = "SELECT * FROM shop_product WHERE company LIKE ? ORDER BY product_id DESC";
        } else {
            throw new IllegalArgumentException("Invalid search key: " + searchKey);
        }

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + searchWord + "%");

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    ProductVO product = new ProductVO();
                    product.setProduct_id(rs.getInt("product_id"));
                    product.setName(rs.getString("name"));
                    product.setContent(rs.getString("content"));
                    product.setPrice(rs.getString("price"));
                    product.setCompany(rs.getString("company"));
                    product.setImg(rs.getString("img"));
                    productList.add(product);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error searching products.", e);
        }
        return productList;
    }
}
