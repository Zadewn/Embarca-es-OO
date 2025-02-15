import java.util.*;

public abstract class Embarcacao {

    protected float largura;
    protected float comprimento;
    protected float cargaMaxima; 
    protected float pesoAdicional;
    protected String portoOrigem;
    protected String portoDestino;
    protected String pais;
    protected String codigoID;
    protected Pessoa capitao;
    protected ArrayList<Pessoa> tripulacao = new ArrayList<>();
    protected char sentido; // R = indo do Rio pro mar, M = indo do Mar pro rio

    public float getLargura() {
        return largura;
    }

    public float getPesoAdicional() {
        return pesoAdicional;
    }

    public float getComprimento() {
        return comprimento;
    }

    public float getCargaMaxima() {
        return cargaMaxima;
    }

    public String getPortoOrigem() {
        return portoOrigem;
    }

    @SuppressWarnings("rawtypes")
    public ArrayList getTripulacao() {
        return tripulacao;
    }

    public String getPortoDestino() {
        return portoDestino;
    }

    public String getPais() {
        return pais;
    }

    public String getCodigoID() {
        return codigoID;
    }

    public Pessoa getCapitao() {
        return capitao;
    }

    public char getSentido() {
        return sentido;
    }

    public void setLargura(float largura) {
        if (largura > 0) {
            this.largura = largura;
        }
    }

    public void setComprimento(float comprimento) {
        if (comprimento > 0) {
            this.comprimento = comprimento;

        }
    }

    public void setTripulacao(Pessoa pessoa) {
        if (pessoa != null) {
            tripulacao.add(pessoa);
        }
    }

    public void setCargaMaxima(float cargaMaxima) {
        if (cargaMaxima >= 0) {
            this.cargaMaxima = cargaMaxima;
        }
    }

    public void setPortoOrigem(String portoOrigem) {
        if (portoOrigem != null) {
            this.portoOrigem = portoOrigem;
        }
    }

    public void setPortoDestino(String portoDestino) {
        if (portoDestino != null) {
            this.portoDestino = portoDestino;
        }
    }

    public void setPais(String pais) {
        if (pais != null) {
            this.pais = pais;
        }
    }

    public void setCodigoID(String codigoID) {
        if (codigoID != null) {
            this.codigoID = codigoID;
        }
    }

    public void setCapitao(Pessoa pessoa) {
        this.capitao = pessoa;
    }

    public void setSentido(char sentido) {
        if (sentido == 'R' || sentido == 'M') {
            this.sentido = sentido;
        }
    }

    public void calcularPeso() {
        pesoAdicional += capitao.getPeso();
        if (tripulacao.isEmpty() == false) {
            for (Object pessoa : tripulacao) {
                Pessoa p = (Pessoa) pessoa;
                if(pesoAdicional + p.getPeso() <= cargaMaxima){
                    pesoAdicional += p.getPeso();
                }
            }
        }
    }

}
