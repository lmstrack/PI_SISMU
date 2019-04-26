
package br.feevale.projetosismu.entity;

import java.sql.Date;

public class Unidade {
    
    private Integer idUnidade;
    private String descricao;
    private String tamanho;
    private String historia;
    private String historiaDoador;
    private String fabricante;
    private String origem;
    private Date dataDoacao;
    private Integer codDoador;
    private Integer nPatrimonio;
    private String pacote;
    private Integer codCategoria;
    private float valorNf;
    private Integer idDoador;
    private Integer idRepresentanteLegal;

    public Integer getIdDoador() {
        return idDoador;
    }

    public void setIdDoador(Integer idDoador) {
        this.idDoador = idDoador;
    }

    public Integer getIdUnidade() {
        return idUnidade;
    }

    public void setIdUnidade(Integer idUnidade) {
        this.idUnidade = idUnidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    public String getHistoriaDoador() {
        return historiaDoador;
    }

    public void setHistoriaDoador(String historiaDoador) {
        this.historiaDoador = historiaDoador;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public Date getDataDoacao() {
        return dataDoacao;
    }

    public void setDataDoacao(Date dataDoacao) {
        this.dataDoacao = dataDoacao;
    }

    public Integer getCodDoador() {
        return codDoador;
    }

    public void setCodDoador(Integer codDoador) {
        this.codDoador = codDoador;
    }

    public Integer getnPatrimonio() {
        return nPatrimonio;
    }

    public void setnPatrimonio(Integer nPatrimonio) {
        this.nPatrimonio = nPatrimonio;
    }

    public String getPacote() {
        return pacote;
    }

    public void setPacote(String pacote) {
        this.pacote = pacote;
    }

    public Integer getCodCategoria() {
        return codCategoria;
    }

    public void setCodCategoria(Integer codCategoria) {
        this.codCategoria = codCategoria;
    }

    public float getValorNf() {
        return valorNf;
    }

    public void setValorNf(float valorNf) {
        this.valorNf = valorNf;
    }

    public Integer getIdRepresentanteLegal() {
        return idRepresentanteLegal;
    }

    public void setIdRepresentanteLegal(Integer idRepresentanteLegal) {
        this.idRepresentanteLegal = idRepresentanteLegal;
    }
    
    
    
}
