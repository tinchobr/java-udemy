/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personas.jdbc;
import java.sql.*;
import java.util.*;
import personas.dto.PersonaDTO;
/**
 *
 * @author l0611824
 */
public class PersonaDaoJDBC implements PersonaDao{
    
    private Connection userConn;
    
    private final String SQL_INSERT = "insert into persona(nombre, apellido) values (?,?)";
    private final String SQL_UPDATE = "update persona set nombre=?, apellido=? where id_persona=?";
    private final String SQL_DELETE = "delete from persona where id_persona=?";
    private final String SQL_SELECT = "select id_persona, nombre, apellido from persona order by id_persona";

    public PersonaDaoJDBC() {
    }

    public PersonaDaoJDBC(Connection userConn) {
        this.userConn = userConn;
    }
    
    
    
    @Override
    public int insert(PersonaDTO persona) throws SQLException {
        Connection conn=null;
        PreparedStatement stnt=null;
        
        
        int rows=0;
        try{
            conn=(this.userConn!=null) ? this.userConn : Conexion.getConnection();
            stnt=conn.prepareStatement(SQL_INSERT);
            int index=1;
            stnt.setString(index++, persona.getNombre());
            stnt.setString(index++, persona.getApellido());
            System.out.println("Ejecutando query: "+ SQL_INSERT);
            rows=stnt.executeUpdate();
            System.out.println("Registros afectados: "+ rows);
        }finally{
            Conexion.close(stnt);
            if (this.userConn==null){
                Conexion.close(conn);
            }
            
        }
        return rows;
    }

    @Override
    public int update(PersonaDTO persona) throws SQLException {
        Connection conn=null;
        PreparedStatement stnt=null;
                
        int rows=0;
        try{
            conn=(this.userConn!=null) ? this.userConn : Conexion.getConnection();
            System.out.println("Ejecutando query: "+ SQL_UPDATE);
            stnt=conn.prepareStatement(SQL_UPDATE);
            int index=1;
            
            stnt.setString(index++, persona.getNombre());
            stnt.setString(index++, persona.getApellido());
            stnt.setInt(index, persona.getId_persona());

            rows=stnt.executeUpdate();
            System.out.println("Registros actualizados: "+ rows);
        }finally{
            Conexion.close(stnt);
            if (this.userConn==null){
                Conexion.close(conn);
            }
        }
        return rows;
    }

    @Override
    public int delete(PersonaDTO persona) throws SQLException {
        Connection conn=null;
        PreparedStatement stnt=null;
                
        int rows=0;
        try{
            conn=(this.userConn!=null) ? this.userConn : Conexion.getConnection();
            System.out.println("Ejecutando query: "+ SQL_DELETE);
            stnt=conn.prepareStatement(SQL_DELETE);
            
            stnt.setInt(1, persona.getId_persona());

            rows=stnt.executeUpdate();
            System.out.println("Registros eliminados: "+ rows);
        }finally{
            Conexion.close(stnt);
            if (this.userConn==null){
                Conexion.close(conn);
            }
        }
        return rows;
    }

    @Override
    public List<PersonaDTO> select() throws SQLException {
        Connection conn=null;
        PreparedStatement stnt=null;
        ResultSet rs=null;
        PersonaDTO personaDTO=null;
        List<PersonaDTO> personas = new ArrayList<PersonaDTO>();
        try{
            conn=(this.userConn!=null) ? this.userConn : Conexion.getConnection();
            System.out.println("Ejecutando query: "+ SQL_SELECT);
            stnt=conn.prepareStatement(SQL_SELECT);
            rs =stnt.executeQuery();
            while(rs.next()){
                int idPersonaTemp=rs.getInt(1);
                String nombreTemp=rs.getString(2);
                String apellidoTemp=rs.getString(3);
                personaDTO=new PersonaDTO();
                personaDTO.setId_persona(idPersonaTemp);
                personaDTO.setNombre(nombreTemp);
                personaDTO.setApellido(apellidoTemp);
                personas.add(personaDTO);
            }
            
        }finally{
            Conexion.close(rs);
            Conexion.close(stnt);
            if (this.userConn==null){
                Conexion.close(conn);
            }
        }
        return personas;
    }
    
}
