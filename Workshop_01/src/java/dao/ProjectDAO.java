
package dao;

import dto.ProjectDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

public class ProjectDAO implements IDAO<ProjectDTO, String> {

    @Override
    public boolean create(ProjectDTO entity) {
        String sql = "Insert into [dbo].[tblStartupProjects]"
                + "(project_id, project_name, Description, Status, estimated_launch)"
                + "values (?,?,?,?,?)";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, entity.getProjectId());
            ps.setString(2, entity.getProjectName());
            ps.setString(3, entity.getDescription());
            ps.setString(4, entity.getStatus());
            ps.setDate(5, entity.getEstimatedLaunch());
            int i = ps.executeUpdate();
            return i > 0;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return false;
    }

    @Override
    public List<ProjectDTO> readAll() {
        return null;
    }

    @Override
    public ProjectDTO readbyID(String id) {
        String sql = "SELECT * FROM [dbo].[tblStartupProjects] WHERE project_id = ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new ProjectDTO(
                        rs.getInt("project_id"),
                        rs.getString("project_name"),
                        rs.getString("Description"),
                        rs.getString("Status"),
                        rs.getDate("estimated_launch"));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    @Override
    public boolean update(ProjectDTO entity) {
        String sql = "UPDATE [dbo].[tblStartupProjects] "
                + "SET project_name = ?, Description = ?, Status = ?, estimated_launch = ? "
                + "WHERE project_id = ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, entity.getProjectName());
            ps.setString(2, entity.getDescription());
            ps.setString(3, entity.getStatus());
            ps.setDate(4, entity.getEstimatedLaunch());
            ps.setInt(5, entity.getProjectId());

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0; // Trả về true nếu có dòng được cập nhật
        } catch (Exception e) {
            System.out.println("Error in update: " + e.toString());
        }
        return false;
    }

    @Override
    public List<ProjectDTO> search(String searchTerm) {
        return null;
    }

    public List<ProjectDTO> searchByTitle(String searchTerm) {
        String sql = "SELECT * FROM [dbo].[tblStartupProjects] WHERE project_name LIKE ?";
        List<ProjectDTO> list = new ArrayList<ProjectDTO>();

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + searchTerm + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProjectDTO p = new ProjectDTO(
                        rs.getInt("project_id"),
                        rs.getString("project_name"),
                        rs.getString("Description"),
                        rs.getString("Status"),
                        rs.getDate("estimated_launch"));

                list.add(p);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return list;
    }
}
