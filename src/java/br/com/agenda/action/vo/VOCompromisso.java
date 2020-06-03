/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agenda.action.vo;

import java.sql.Blob;
import java.util.Date;
import org.apache.struts.upload.FormFile;

/**
 *
 * @author Felipe
 */
public class VOCompromisso {

    private Integer id;
    private Integer idTipoCompromisso;
    private Date dtData;
    private Integer formaPagamento;
    private String descFormaPagamento;
    private String titulo;
    private String descricao;
    private boolean efetuado;
    private String descEfetuado;
    private Integer idUsuario;
    private String mes;
    private String ano;
    private byte[] arquivo;
    private String extencaoComprovante;
    private Integer idSubTipoCompromisso;
    private FormFile comprovantes;
    private FormFile localComprovante;
    private Blob comprovanteBlob;
    private VOTipoCompromisso tipoCompromisso;
    private Double valor;
    private String valorConvertido;
    private Integer prestacoes;
    private Integer prestacoesPagas;
    private String localComprovantes;
    
     public FormFile getLocalComprovante() {
        return localComprovante;
    }

    public void setLocalComprovante(FormFile localComprovante) {
        this.localComprovante = localComprovante;
    }

    public String getLocalComprovantes() {
        return localComprovantes;
    }

    public void setLocalComprovantes(String localComprovantes) {
        this.localComprovantes = localComprovantes;
    }

    public String getExtencaoComprovante() {
        return extencaoComprovante;
    }

    public void setExtencaoComprovante(String extencaoComprovante) {
        this.extencaoComprovante = extencaoComprovante;
    }

    public byte[] getArquivo() {
        return arquivo;
    }

    public void setArquivo(byte[] arquivo) {
        this.arquivo = arquivo;
    }

    public Integer getPrestacoesPagas() {
        return prestacoesPagas;
    }

    public void setPrestacoesPagas(Integer prestacoesPagas) {
        this.prestacoesPagas = prestacoesPagas;
    }

    public Blob getComprovanteBlob() {
        return comprovanteBlob;
    }

    public void setComprovanteBlob(Blob comprovanteBlob) {
        this.comprovanteBlob = comprovanteBlob;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public Integer getIdSubTipoCompromisso() {
        return idSubTipoCompromisso;
    }

    public void setIdSubTipoCompromisso(Integer idSubTipoCompromisso) {
        this.idSubTipoCompromisso = idSubTipoCompromisso;
    }

    public String getDescFormaPagamento() {
        return descFormaPagamento;
    }

    public void setDescFormaPagamento(String descFormaPagamento) {
        this.descFormaPagamento = descFormaPagamento;
    }

    public String getDescEfetuado() {
        return descEfetuado;
    }

    public void setDescEfetuado(String descEfetuado) {
        this.descEfetuado = descEfetuado;
    }

    public Integer getPrestacoes() {
        return prestacoes;
    }

    public void setPrestacoes(Integer prestacoes) {
        this.prestacoes = prestacoes;
    }

    public String getValorConvertido() {
        return valorConvertido;
    }

    public void setValorConvertido(String valorConvertido) {
        this.valorConvertido = valorConvertido;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public VOTipoCompromisso getTipoCompromisso() {
        return tipoCompromisso;
    }

    public void setTipoCompromisso(VOTipoCompromisso tipoCompromisso) {
        this.tipoCompromisso = tipoCompromisso;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdTipoCompromisso() {
        return idTipoCompromisso;
    }

    public void setIdTipoCompromisso(Integer idTipoCompromisso) {
        this.idTipoCompromisso = idTipoCompromisso;
    }

    public Integer getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(Integer formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Date getDtData() {
        return dtData;
    }

    public void setDtData(Date dtData) {
        this.dtData = dtData;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isEfetuado() {
        return efetuado;
    }

    public void setEfetuado(boolean efetuado) {
        this.efetuado = efetuado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the comprovantes
     */
    public FormFile getComprovantes() {
        return comprovantes;
    }

    /**
     * @param comprovantes the comprovantes to set
     */
    public void setComprovantes(FormFile comprovantes) {
        this.comprovantes = comprovantes;
    }
}
