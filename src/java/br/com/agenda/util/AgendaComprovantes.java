/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agenda.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.Blob;
import org.apache.struts.upload.FormFile;
import org.hibernate.Hibernate;


/**
 *
 * @author Felipessca
 */
public class AgendaComprovantes {

   public Blob gravarComprovante(FormFile comprovante) throws Exception {
      //  Blob blob = (Hibernate.createBlob(comprovante.getInputStream()));
      Blob blob = null;
        return blob;
    }
    

    public byte[] retornarComprovante(Blob blob) throws Exception {
        byte[] arquivo = null;



        if (blob!= null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
             BufferedInputStream bis = new BufferedInputStream(blob.getBinaryStream());
            byte bindata[] = new byte[5048576];
            int bytesread = 0;
            if ((bytesread = bis.read(bindata, 0, bindata.length)) != -1) {
                baos.write(bindata, 0, bytesread);

            } else {
                arquivo = baos.toByteArray();

            }
            arquivo = baos.toByteArray();
            bis.close();
        }

        return arquivo;
    }
}
