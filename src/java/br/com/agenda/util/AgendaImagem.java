/*
 * AgendaImagem.java
 *
 * Created on 11/10/2007, 15:08:40
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.agenda.util;

import java.io.File;
import java.io.FileOutputStream;
import org.apache.struts.upload.FormFile;

/**
 *
 * @author Felipessca
 */
public class AgendaImagem {

    private String nomeArquivo = "";
    private int tamanhoArquivo = 0;
    AgendaProperties agendaProperties = new AgendaProperties();

    public void gravarFoto(FormFile foto, String acao, String fotoAtual) throws Exception {
        String contentType = foto.getContentType();
        String fileName = foto.getFileName();
       // byte[] fileData = foto.getFileData();
        tamanhoArquivo=foto.getFileSize();
         //String filePathServer = "C:\\Arquivos de programas\\Apache Software Foundation\\Tomcat 6.0\\webapps\\AgendaWebFssca\\Fotos";
         String filePathServer = agendaProperties.getFilePathServer();
         //String filePath = "G:\\FOTOS\\AgendaFotos";
         String filePath = agendaProperties.getFilePath();
        nomeArquivo = fileName;
        System.out.println("cont"+contentType+"-tamanho"+tamanhoArquivo);
        System.out.println("caminho"+filePath);
      // boolean ver= verificarArquivo();
      // if(ver==false){
      // return;
      // }
        if (acao.equals("Edicao")) {
            if (fileName.equals("") || fileName == null) {
                nomeArquivo = fotoAtual; 
            } else {
                efetuarGravacao(foto, fileName, filePath);
                efetuarGravacao(foto, fileName, filePathServer);
            }
        } else {
            if (fileName.equals("") || fileName == null) {
                nomeArquivo = "Padrao.jpg";
            } else {
                efetuarGravacao(foto, fileName, filePath);
                efetuarGravacao(foto, fileName, filePathServer);
            }
        }
    }

    private void efetuarGravacao(FormFile foto, String fileName, String filePath)throws Exception{
        File fotoNova = new File(filePath, fileName);
        if (!fotoNova.exists()) {
            FileOutputStream fileOutStream = new FileOutputStream(fotoNova);
            fileOutStream.write(foto.getFileData());
            fileOutStream.flush();
            fileOutStream.close();
        }
    }
    private boolean verificarArquivo(){
  //73585 73k -750 000 750k -2000 000
   // 1299357
  
     if(tamanhoArquivo>1000000){
     return false;
     }
     
       return true;
         
     }
    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public int getTamanhoArquivo() {
        return tamanhoArquivo;
    }

    public void setTamanhoArquivo(int tamanhoArquivo) {
        this.tamanhoArquivo = tamanhoArquivo;
    }





}