
package br.feevale.projetosismu.entity;

import java.sql.Date;

public class Exposicao {
    
    private Integer idExposicao;
    private Date dataInicio;
    private Date dataFim;
    private Integer codExpositor;
    private Integer codUnidade;

    public Integer getIdExposicao() {
        return idExposicao;
    }

    public void setIdExposicao(Integer idExposicao) {
        this.idExposicao = idExposicao;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Integer getCodExpositor() {
        return codExpositor;
    }

    public void setCodExpositor(Integer codExpositor) {
        this.codExpositor = codExpositor;
    }

    public Integer getCodUnidade() {
        return codUnidade;
    }

    public void setCodUnidade(Integer codUnidade) {
        this.codUnidade = codUnidade;
    }

}
