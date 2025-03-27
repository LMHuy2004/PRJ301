package dao;

import dto.CategoryDTO;
import dto.ExamsDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author l26m1
 */
public class ExamsDAO implements IDAO<ExamsDTO, String>{

    @Override
    public boolean create(ExamsDTO entity) {
        String sql = "INSERT INTO tblExams "
                + " (exam_id, exam_title, Subject, category_id, total_marks, Duration) "
                + " VALUES (?, ?, ?, ?, ?, ?) ";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, entity.getExam_id());
            ps.setString(2, entity.getExam_title());
            ps.setString(3, entity.getSubject());            
            ps.setInt(4, entity.getCategory_id());
            ps.setInt(5, entity.getTotal_marks());
            ps.setInt(6, entity.getDuration());


            int i = ps.executeUpdate();
            return i > 0;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return false;
    }

    @Override
    public List<ExamsDTO> readAll() {
        return null;
    }

    @Override
    public ExamsDTO readById(String id) {
        return null;
    }

    @Override
    public boolean update(ExamsDTO entity) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public List<ExamsDTO> search(String searchTerm) {
        return null;
    }
    
    public List<ExamsDTO> getExamsByCategory(int categoryId) throws ClassNotFoundException {
    List<ExamsDTO> examsList = new ArrayList<>();
    String sql = "SELECT * FROM tblExams WHERE category_id = ?";
    
    try (Connection conn = DBUtils.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, categoryId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            ExamsDTO exam = new ExamsDTO(
                rs.getString("exam_id"),
                rs.getString("exam_title"),
                rs.getString("Subject"),
                rs.getInt("category_id"),
                rs.getInt("total_marks"),
                rs.getInt("Duration")
            );
            examsList.add(exam);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return examsList;
}


    public static List<ExamsDTO> ExamsByCategory2(String searchTerm) {
        String sql = "SELECT exam_id, exam_title, Subject, category_id, total_marks, Duration FROM Exams WHERE category_id = ?";
        List<ExamsDTO> list = new ArrayList<ExamsDTO>();

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + searchTerm + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ExamsDTO ex = new ExamsDTO(
                        rs.getString("exam_id"),
                        rs.getString("exam_title"),
                        rs.getString("Subject"),
                        rs.getInt("category_id"),
                        rs.getInt("total_marks"),
                        rs.getInt("Duration"));

                list.add(ex);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return list;
    }
    
}
