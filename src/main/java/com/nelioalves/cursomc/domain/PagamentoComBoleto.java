package com.nelioalves.cursomc.domain;

import com.nelioalves.cursomc.domain.enums.EstadoPagamento;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class PagamentoComBoleto extends Pagamento {
    private static final long serialVersionUID = 1L;
    private Date dataVecimento;

    private Date dataPagamento;

    public PagamentoComBoleto(){

    }

    public PagamentoComBoleto(EstadoPagamento estado, Pedido pedido, Date dataVecimento, Date dataPagamento) {
        super(estado, pedido);
        this.dataVecimento = dataVecimento;
        this.dataPagamento = dataPagamento;
    }

    public Date getDataVecimento() {
        return dataVecimento;
    }

    public void setDataVecimento(Date dataVecimento) {
        this.dataVecimento = dataVecimento;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
}
