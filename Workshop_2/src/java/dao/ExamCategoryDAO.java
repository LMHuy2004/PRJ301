package dao;

import dto.ExamCategoryDTO;
import dto.ExamDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

public class ExamCategoryDAO {
    public List<ExamCategoryDTO> getAllCategories() throws Exception {
        List<ExamCategoryDTO> categories = new ArrayList<>();
        String sql = "SELECT category_id, category_name, description FROM tblExamCategories";
        
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                int categoryId = rs.getInt("category_id");
                String name = rs.getString("category_name");
                String description = rs.getString("description");
                categories.add(new ExamCategoryDTO(categoryId, name, description));
            }
        }
        return categories;
    }

    // New method to get exams by category
    public List<ExamDTO> getExamsByCategory(int categoryId) throws Exception {
        List<ExamDTO> exams = new ArrayList<>();
        String sql = "SELECT exam_id, exam_title, subject, total_marks, duration FROM tblExams WHERE category_id = ?";
        
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, categoryId);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                ExamDTO exam = new ExamDTO(
                    rs.getInt("exam_id"),
                    rs.getString("exam_title"),
                    rs.getString("subject"),
                    rs.getInt("total_marks"),
                    rs.getInt("duration")
                );
                exams.add(exam);
            }
        }
        return exams;
    }
}