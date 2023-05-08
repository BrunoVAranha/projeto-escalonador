import java.util.List;

class Processo {
    private String nome;

    private int prioridade;    // Priority level (0 is highest)
    private int burstTime;   // Burst time (time required to complete process)

    public Processo(String nome, int prioridade, int burstTime) {
        this.nome = nome;
        this.prioridade = prioridade;
        this.burstTime = burstTime;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    @Override
    public String toString() {
        return nome;
    }

    public static void aplicarAging(Processo p){
        if(p.getPrioridade() > 0){
            p.setPrioridade(p.getPrioridade() - 1);
        }
    }
}