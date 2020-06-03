/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agenda.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Felipessca
 */
public class AgendaProperties {

    // Read properties file.
    private Properties properties;
    private String filePathServer;
    private String filePath;

    //Ler erros logger
    private String erroSqlInsercao;

    //Caminho do log
    private String caminhoArquivoLog;

    public String getCaminhoArquivoLog() {
        return caminhoArquivoLog;
    }

    public void setCaminhoArquivoLog(String caminhoArquivoLog) {
        this.caminhoArquivoLog = caminhoArquivoLog;
    }

    public String getErroSqlInsercao() {
        return erroSqlInsercao;
    }

    public void setErroSqlInsercao(String erroSqlInsercao) {
        this.erroSqlInsercao = erroSqlInsercao;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePathServer() {
        return filePathServer;
    }

    public void setFilePathServer(String filePathServer) {
        this.filePathServer = filePathServer;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public AgendaProperties() {
        loadProperties();
    }

    private void loadProperties() {
        try {
            properties = new Properties();
            InputStream in = getClass().getClassLoader().getResourceAsStream("agendaweb.properties");
            properties.load(in);
            in.close();
            filePathServer = properties.getProperty("imagem.servidor");
            filePath = properties.getProperty("imagem.local");
            erroSqlInsercao = properties.getProperty("erro.sql.insercao");
            caminhoArquivoLog = properties.getProperty("caminho.logger");


        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());  
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());  
            ex.printStackTrace();
        }
    }

// Write properties file.
    private void saveProperties() {
        try {
            properties.store(new FileOutputStream("agendaweb.properties"), null);

        } catch (IOException e) {
        }
    }
}
