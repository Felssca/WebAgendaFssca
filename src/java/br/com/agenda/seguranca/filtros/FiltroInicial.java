/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agenda.seguranca.filtros;

import br.com.agenda.seguranca.VOLogin;
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
public class FiltroInicial implements Filter {

    private FilterConfig filterConfig;

    @Override
    public void doFilter(ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        boolean autorizacao = false;
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        if (request instanceof HttpServletRequest) {
            HttpSession sessao = ((HttpServletRequest) request).getSession(true);
            
            if (sessao.getAttribute("LOGIN")==null) {
                 VOLogin vo = new VOLogin();
                ControleSessao.verificarSessaoInicial(vo);
                 
                sessao.setAttribute("LOGIN", vo);
                sessao.setAttribute("LOGIN_USUARIO", vo.getUsuario());
                sessao.setAttribute("ID_USUARIO", vo.getId());
                autorizacao = false;
               
            } else {
                autorizacao = true;
            }
            if (autorizacao) {
                VOLogin vo = (VOLogin)sessao.getAttribute("LOGIN");
                sessao.setAttribute("LOGIN_USUARIO", vo.getUsuario());
                sessao.setAttribute("ID_USUARIO", vo.getId());
                chain.doFilter(request, response);
                return;
            }else{
              chain.doFilter(request, response);
                
            
            }

        }

    }

   
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    public void destroy() {
        
    }
}
