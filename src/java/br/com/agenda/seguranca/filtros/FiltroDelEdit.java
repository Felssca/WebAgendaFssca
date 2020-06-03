/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.agenda.seguranca.filtros;

import br.com.agenda.exception.AgendaExceptions;
import br.com.agenda.seguranca.VOLogin;
import br.com.agenda.util.Constantes;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Felipessca
 */
public class FiltroDelEdit implements Filter {
    
     private FilterConfig filterConfig;

    public void doFilter(ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        boolean autorizacao = false;
        String usuario = "";
        Integer idUsuario = null;
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        if (request instanceof HttpServletRequest) {
            HttpSession sessao = ((HttpServletRequest) request).getSession(true);
            if (sessao.getAttribute("LOGIN")!=null) {
                VOLogin vo = (VOLogin)sessao.getAttribute("LOGIN");
                usuario = vo.getUsuario();
                idUsuario = vo.getId();
                autorizacao=ControleSessao.autorizarDeletetarEditar(vo);
            } if (autorizacao) {
                sessao.setAttribute("LOGIN_USUARIO",usuario);
                sessao.setAttribute("ID_USUARIO",idUsuario);
                chain.doFilter(request, response);
                return;
            }else{
                filterConfig.getServletContext().getRequestDispatcher("/redirecionaLogin.jsp").
 	        forward(request, response);
                return;
            }
              

        

    }
    
    }


    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    public void destroy() {
    }

}
