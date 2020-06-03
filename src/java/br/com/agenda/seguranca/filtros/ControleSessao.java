/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agenda.seguranca.filtros;

import br.com.agenda.seguranca.VOLogin;
import br.com.agenda.util.Constantes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Felipessca
 */
public class ControleSessao {
    
    public static Integer ID_USUARIO = 0;

    public static void manterSessao(VOLogin vo, HttpServletRequest request) {
        HttpSession sessao = request.getSession(false);
        sessao.setAttribute("LOGIN", vo);
        ID_USUARIO = vo.getId();
        
    }
    
  


    public static void verificarSessaoInicial(VOLogin vo) {
        vo.setUsuario("VISITANTE");
        vo.setPermicao(Constantes.PERMICAO_USUARIO);
        vo.setId(0);

    }
    
    

    public static Boolean autorizarDeletetarEditar(VOLogin vo) {

        if (vo.getPermicao().equals(Constantes.PERMICAO_ADMIN)) {
            return true;
        } else {

            return false;
        }
    }

    public static Boolean autorizarSalvar(VOLogin vo) {

        if (vo.getPermicao().equals(Constantes.PERMICAO_ADMIN) || vo.getPermicao().equals(Constantes.PERMICAO_MASTER)) {
            return true;
        } else {

            return false;
        }
    }
}
