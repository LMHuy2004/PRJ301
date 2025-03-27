package dao;

import dto.CategoryDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;



public class CategoryDAO implements IDAO<CategoryDTO, String>{

    @Override
    public boolean create(CategoryDTO entity) {
        String sql = "INSERT INTO tblExamCategories "
                + " (category_id, category_name, description) "
                + " VALUES (?, ?, ?) ";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, entity.getCategory_id());
            ps.setString(2, entity.getCategory_name());
            ps.setString(3, entity.getDescription());

            int i = ps.executeUpdate();
            return i > 0;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return false;
    }

    @Override
    public List<CategoryDTO> readAll() {
        return null;
    }

    @Override
    public CategoryDTO readById(String id) {
        return null;
    }

    @Override
    public boolean update(CategoryDTO entity) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public List<CategoryDTO> search(String searchTerm) {
        return null;
    }
    
    public List<CategoryDTO> searchByTitle(String searchTerm) {
        String sql = "SELECT * FROM tblExamCategories WHERE category_name LIKE ?";
        List<CategoryDTO> list = new ArrayList<CategoryDTO>();

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + searchTerm + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CategoryDTO b = new CategoryDTO(
                        rs.getString("category_id"),
                        rs.getString("category_name"),
                        rs.getString("description"));

                list.add(b);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return list;
    }

    public static List<CategoryDTO> searchByTitle2(String searchTerm) {
        String sql = "SELECT * FROM tblExamCategories WHERE category_name LIKE ?";
        List<CategoryDTO> list = new ArrayList<CategoryDTO>();

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + searchTerm + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CategoryDTO c = new CategoryDTO(
                        rs.getString("category_id"),
                        rs.getString("category_name"),
                        rs.getString("description"));

                list.add(c);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return list;
    }
    
}



