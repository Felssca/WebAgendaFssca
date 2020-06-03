/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agenda.util;

import br.com.agenda.action.vo.VOAgenda;
import br.com.agenda.action.vo.VOAniversario;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

/**
 *
 * @author Felipessca
 */
public class AgendaData {

    private String mes;
    private String ano;
    private int dia;
    private Date data;

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public AgendaData() {
        Calendario();
    }

    public Date formataData(String data) throws Exception {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = (Date) formatter.parse(data);
        return date;

    }

    public void Calendario() {
        Calendar calendario = new GregorianCalendar();
        int mesInt = (calendario.get(Calendar.MONTH)) + 1;
        mes = Integer.toString(mesInt);
        if (mes.length() < 2) {
            mes = "0" + mes;
        }
        ano = Integer.toString(calendario.get(Calendar.YEAR));
        dia = calendario.get(Calendar.DAY_OF_MONTH);
    }

    public ArrayList verificarAniversario(ArrayList lista) {
        ArrayList aniversariante = new ArrayList();
        String aniversario = null;
        String mesAniversario = null;
        String diaAniversario = null;

        for (int i = 0; i < lista.size(); i++) {
            VOAniversario voNiver = new VOAniversario();
            VOAgenda vo = (VOAgenda) lista.get(i);
            if (vo.getAniversario() != null) {
                aniversario = vo.getAniversario().toString();
                mesAniversario = aniversario.substring(5, 7);
                diaAniversario = aniversario.substring(8, 10);
                if (mesAniversario.equals(mes)) {
                    voNiver.setId(vo.getId());
                    voNiver.setNome(vo.getNome());
                    voNiver.setData(diaAniversario + "/" + mes);
                    voNiver.setTipo(vo.getTipo());
                    voNiver.setFoto(vo.getFoto());
                    aniversariante.add(voNiver);

                }
            }





        }
        return aniversariante;
    }

    public void pegarMesAnoFormatado(String data) {

        mes = data.substring(3, 4);
        ano = data.substring(5, 8);
    }

        public void dataRepliacarCompromisso(Date data) {
        String formato = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(formato);
        String dataFormatada = sdf.format(data).toString();
        StringTokenizer token = new StringTokenizer(dataFormatada, "/");

        dia =Integer.parseInt( token.nextToken());
        mes = token.nextToken();
        ano = token.nextToken();



    }

        public Date criarDataReplica(Date data){
        Date dataFinal = data;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dataFinal);
        calendar.add(Calendar.MONTH,1);
        dataFinal = calendar.getTime();
        return dataFinal;
        }
    /*
     *
    public String data(Date data){
    String formato="dd/MM/yy";
    SimpleDateFormat formatada= new SimpleDateFormat(formato);
    return("Data de Hoje:"+formatada.format(hoje));
    }
    
    public String dataMes(){
    String formato="dd/MM/yyyy";
    SimpleDateFormat form= new SimpleDateFormat(formato);
    return(""+form.format(hoje).substring(0,5));
    }
    
    public String dataNormal(){
    String formato="dd/MM/yy";
    SimpleDateFormat formatada= new SimpleDateFormat(formato);
    return(""+formatada.format(hoje));
    }
    public int dataAniverComp(){
    String formato="dd/MM/yyyy";
    SimpleDateFormat form= new SimpleDateFormat(formato);
    int mes=Integer.parseInt(form.format(hoje).substring(3,5));
    return mes;
    }
    public int anoAniverCompro(){
    String formato="dd/MM/yyyy";
    SimpleDateFormat form= new SimpleDateFormat(formato);
    int ano=Integer.parseInt(form.format(hoje).substring(6));
    return ano;
    }    
    
     */
}
