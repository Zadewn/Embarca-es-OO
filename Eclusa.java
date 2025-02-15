import java.util.*;

public class Eclusa {

    private float largura;
    private float comprimento;
    private float capacidadeMAX;
    private float capacidadeMIN;
    private float capacidadeAtual;
    private boolean comportaRio; // Em direção ao rio, true = aberta, false = fechada
    private boolean comportaMar; // Em direção ao mar
    private float vazao; // m³ segundo
    private int quantidadeCanos;
    private float valor = 0; // salva o valor diario 
    private char status; // E = Enchendo, S = secando, N = parado
    private ArrayList<Embarcacao> filaMar = new ArrayList<>();
    private ArrayList<Embarcacao> filaRio = new ArrayList<>();
    private ArrayList<Embarcacao> naviosEncaixados = new ArrayList<>();

    public float getCapacidadeAtual() {
        return capacidadeAtual;
    }

    @SuppressWarnings("rawtypes")
    public ArrayList getfilaMar() {
        return filaMar;
    }

    @SuppressWarnings("rawtypes")
    public ArrayList getfilaRio() {
        return filaRio;
    }

    @SuppressWarnings("rawtypes")
    public ArrayList getNaviosEncaixados() {
        return naviosEncaixados;
    }

    public float getLargura() {
        return largura;
    }

    public float getComprimento() {
        return comprimento;
    }

    public float getCapacidadeMAX() {
        return capacidadeMAX;
    }

    public float getCapacidadeMIN() {
        return capacidadeMIN;
    }

    public float getVazao() {
        return vazao;
    }

    public int getQuantidadeCanos() {
        return quantidadeCanos;
    }

    public float getValor() {
        return valor;
    }

    public char getStatus() {
        return status;
    }

    public boolean getComportaMar() {
        return comportaMar;
    }

    public boolean getComportaRio() {
        return comportaRio;
    }

    public void setLargura(float largura) {
        if (largura >= 5 ) {
            this.largura = largura;
        }
    }

    public void setComprimento(float comprimento) {
        if (comprimento >= 38) {
            this.comprimento = comprimento;
        }
    }

    public void setFilaMar(Embarcacao embarcacao) {
        if (embarcacao != null) {
            if (embarcacao.getLargura() < largura && embarcacao.getComprimento() < comprimento) {
                filaMar.add(embarcacao);
            }
        }
    }

    public void setFilaRio(Embarcacao embarcacao) {
        if (embarcacao != null) {
            if (embarcacao.getLargura() < largura && embarcacao.getComprimento() < comprimento) {
                filaRio.add(embarcacao);
            }
        }
    }

    public void removerDafilaMar(Embarcacao embarcacao){
        if (filaMar.contains(embarcacao) == true){
            filaMar.remove(embarcacao);
        }
    }

    public void removerDafilaRio(Embarcacao embarcacao){
        if (filaRio.contains(embarcacao) == true){
            filaRio.remove(embarcacao);
        }
    }

    public void setCapacidadeMIN(float capacidadeMIN) {
        if (capacidadeMIN > 0) {
            this.capacidadeMIN = capacidadeMIN;
        }
    }

    public void setCapacidadeMAX(float capacidadeMAX) {
        if (capacidadeMAX > 0 && capacidadeMAX > capacidadeMIN) {
            this.capacidadeMAX = capacidadeMAX;
        }
    }

    public void setCapacidadeAtual(float capacidadeAtual) {
        if (capacidadeAtual <= capacidadeMAX && capacidadeAtual >= capacidadeMIN) {
            this.capacidadeAtual = capacidadeAtual;
        }
    }

    public void setVazao(float vazao) {
        if (vazao > 0) {
            this.vazao = vazao;
        }
    }

    public void setQuantidadeCanos(int quantidadeCanos) {
        if (quantidadeCanos > 0) {
            this.quantidadeCanos = quantidadeCanos;
        }
    }

    public void setValor(Embarcacao embarcacao) {
        if (embarcacao instanceof Lancha){
            valor += 25;
        }else if (embarcacao instanceof NavioTurismo){
            valor += 40;
        }else {
            valor += 50;
        }
    }

    public void setStatus(char status) {
        if (status == 'E' || status == 'S') {
            this.status = status;
        }
    }

    public void alterarComportaMar() {
        if(capacidadeAtual == capacidadeMAX){
            comportaMar = true;
        }
    }

    public void alterarComportaRio() {
        if(capacidadeAtual == capacidadeMIN){
            comportaRio = true;
        }
    }

    public float getTempoMAX() { //Retorna o tempo maximo para esvaziar as filas
        float tempo = (capacidadeMAX - capacidadeMIN)/vazao;
        if(filaMar.size() > filaRio.size()){
            return tempo * filaMar.size();
        }else{
            return tempo * filaRio.size();
        }
    }


    public void esvaziarEclusa(int CanosAbertos) {
        if(comportaMar == false && comportaRio == false && capacidadeAtual > capacidadeMIN){
            status = 'S';
            float porcentagemBruta;
            int porcentagemArredondada = 0;
            if (CanosAbertos <= quantidadeCanos && CanosAbertos > 0) {
                while (capacidadeAtual > capacidadeMIN) {
                    if (capacidadeAtual - (vazao * CanosAbertos) > capacidadeMIN) {
                        capacidadeAtual -= (vazao * CanosAbertos);
                    } else {
                        capacidadeAtual = capacidadeMIN;
                    }
                    porcentagemBruta = 100 * capacidadeAtual / capacidadeMAX;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if((int) porcentagemBruta != porcentagemArredondada){
                        porcentagemArredondada = (int) porcentagemBruta;
                        System.out.println("a porcentagem de agua atual e " + porcentagemArredondada + "% (" + String.format("%.2f", capacidadeAtual) + "m3)");
                    }
                    if (capacidadeAtual == capacidadeMIN){
                        System.out.println("Esse nivel de agua e o minimo");
                        break;
                    }
                }
            }
        }
    }

    public void encherEclusa(int CanosAbertos) {
        if(comportaMar == false && comportaRio == false && capacidadeAtual < capacidadeMAX){
            status = 'E';
            float porcentagemBruta;
            int porcentagemArredondada = 0;
            if (CanosAbertos <= quantidadeCanos && CanosAbertos > 0) {
                while (capacidadeAtual < capacidadeMAX) {
                    if (capacidadeAtual + (vazao * CanosAbertos) < capacidadeMAX) {
                        capacidadeAtual += (vazao * CanosAbertos);
                    } else {
                        capacidadeAtual = capacidadeMAX;
                    }
                    porcentagemBruta = 100 * capacidadeAtual / capacidadeMAX;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if((int) porcentagemBruta != porcentagemArredondada){
                        porcentagemArredondada = (int) porcentagemBruta;
                        System.out.println("a porcentagem de agua atual e " + porcentagemArredondada + "% (" + String.format("%.2f", capacidadeAtual) + "m3)");
                    }
                    if (capacidadeAtual == capacidadeMAX){
                        System.out.println("Esse nivel de agua e o maximo");
                        break;
                    }
                }
            }
        }
    }

    public void encaixarNaviosMar() {
        if (filaMar.isEmpty() == false && comportaMar == true) {
            float larguraRestante = largura;
            float comprimentoRestante = comprimento;
            for (Object navio : filaMar) {
                Embarcacao embarcacao = (Embarcacao) navio;
                if (embarcacao.getLargura() < larguraRestante && embarcacao.getComprimento() < comprimentoRestante) {
                    filaMar.remove(0);
                    naviosEncaixados.add(embarcacao);
                    larguraRestante -= (embarcacao.getLargura() - 3);
                    comprimentoRestante -= (embarcacao.getComprimento() - 3);
                } else {
                    break;
                }
            }
            alterarComportaMar();
        }
    }

    public void encaixarNaviosRio() {
        if (filaRio.isEmpty() == false && comportaRio == true) {
            float larguraRestante = largura;
            float comprimentoRestante = comprimento;
            for (Object navio : filaRio) {
                Embarcacao embarcacao = (Embarcacao) navio;
                if (embarcacao.getLargura() < larguraRestante && embarcacao.getComprimento() < comprimentoRestante) {
                    filaRio.remove(0);
                    naviosEncaixados.add(embarcacao);
                    larguraRestante -= (embarcacao.getLargura() - 3);
                    comprimentoRestante -= (embarcacao.getComprimento() - 3);
                } else {
                    break;
                }
            }
            alterarComportaRio();
        }
    }

    public Eclusa() {
        super();
        comportaMar = false;
        comportaRio = false;
    }

}
