/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personas.test;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import personas.dto.PersonaDTO;
import personas.jdbc.PersonaDaoJDBC;
import personas.jdbc.PersonaDao;
/**
 *
 * @author l0611824
 */
public class TestPersonas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        PersonaDao personaDao = new PersonaDaoJDBC();
        
        PersonaDTO persona= new PersonaDTO();
        persona.setNombre("Mario");
        persona.setApellido("Lopez");
        
        try{
            //personaDao.insert(persona);
            
            personaDao.delete(new PersonaDTO(3));
            
            //PersonaDTO personaTmp=new PersonaDTO();
            
            List<PersonaDTO> personas = personaDao.select();
            for (PersonaDTO personaDTO : personas){
                System.out.println(personaDTO);
                
            }
            
        }catch (SQLException ex){
            System.out.println("Excepcion en la capa de prueba");
            ex.printStackTrace();
        }
        
    }
    
}
