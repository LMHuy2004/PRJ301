package dao;

import dto.PaintingDTO;
import dto.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;
import static utils.DBUtils.getConnection;

public class PaintingDAO extends DBUtils {

    public List<PaintingDTO> searchPainting(String txtSearch) throws SQLException {
        List<PaintingDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        //SELECT * FROM [tblPainting] WHERE [description] LIKE '%sown%' and status = 1
        String sql = "SELECT * FROM [tblPainting] WHERE [description] LIKE ? and status = 1";
        try {
            conn = getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(sql);
                ptm.setString(1, "%" + txtSearch + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("id");
                    String creator = rs.getString("creator");
                    String description = rs.getString("description");
                    int height = rs.getInt("height");
                    int width = rs.getInt("width");
                    double price = rs.getDouble("price");
                    boolean roleID = rs.getBoolean("roleID");
                    PaintingDTO painting = new PaintingDTO(id, creator, description, height, width, price, roleID);
                    list.add(painting);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }
    
    //delete
    public void deletePainting(String pid) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
//        "UPDATE [dbo].[tblPainting]\n"
//                + " SET [status] = 0\n"
//                + " WHERE id = 'p005'"
        String sql = "UPDATE [dbo].[tblPainting]\n"
                + " SET [status] = 0\n"
                + " WHERE id = ?";
        try {
            conn = getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(sql);
                ptm.setString(1, pid.trim());
                ptm.executeUpdate();
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        
    }
}
