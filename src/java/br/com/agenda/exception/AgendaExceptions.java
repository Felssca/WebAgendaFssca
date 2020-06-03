/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agenda.exception;

import br.com.agenda.action.vo.VOAgenda;
import br.com.agenda.util.AgendaProperties;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author FelipeSSCA
 */
public class AgendaExceptions extends Exception {
    AgendaProperties propriedades = new AgendaProperties();

    private Map<String, String> erro;

    public Map<String, String> getErro() {
        return erro;
    }

    public void setErro(Map<String, String> erro) {
        this.erro = erro;
    }

    public Map verificarErrosAgenda(VOAgenda vo) {
        erro = new HashMap<String, String>();
        erro.clear();
        if (vo.getNome() == null || vo.getNome().equals("") || vo.getNome().length() < 2) {
            erro.put("ERRO", "Campo Nome inválido. O campo é obrigatório");
        }
        

        return erro;

    }

    public void logarErro(String nomeClasse,String mensagem,Exception execao){
        boolean append = true;
        try {
            FileHandler handler = new FileHandler(propriedades.getCaminhoArquivoLog(), append);
            Logger logger = Logger.getLogger(nomeClasse);
            logger.setLevel(Level.ALL);
            logger.addHandler(handler);
            logger.log(Level.OFF, mensagem,execao );

        } catch (IOException ex) {
            Logger.getLogger(AgendaExceptions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(AgendaExceptions.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
