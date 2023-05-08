import java.util.*;

class Queue {
    private int prioridade; //Prioridade da queue

    private List<Processo> processos; // Lista de processos em uma queue

    public Queue(int prioridade) {
        this.prioridade = prioridade;
        this.processos = new ArrayList<>();
    }

    public int getPrioridade() {
        return prioridade;
    }

    public List<Processo> getProcessos() {
        return processos;
    }

    public void setProcessos(List<Processo> processos) {
        this.processos = processos;
    }

    // Check if LL is empty
    public boolean isEmpty() {
        return this.processos.isEmpty();
    }

    //função que ordena a queue (SJF)
    public void ordenar(){
        processos.sort(Comparator.comparing(Processo::getBurstTime));
    }
}