import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    //variaveis utilizadas para saber qual processo sofrerá aging
    static int menorPrioridade = 0;
    static int maiorBurstTime = 0;
    static Processo processoAging;

    public static void main(String[] args) throws InterruptedException {

        int i; //contador geral
        int prioridade, burstTime;
        String nomeProcesso;
        List<Processo> processos = new ArrayList<>();

        Scanner input = new Scanner(System.in);
        System.out.println("Quantos processos voce quer inserir? ");
        int n = input.nextInt();

        for(i = 0; i<n ; i++){
            System.out.println("Nome do processo " +(i+1)+" : " );
            nomeProcesso = input.next();
            System.out.println("Prioridade (de 0 a 3) do processo " +(i+1)+" : " );
            prioridade = input.nextInt();
            System.out.println("burstTime (em segundos) do processo " +(i+1)+" : " );
            burstTime = input.nextInt();
            Processo p = new Processo(nomeProcesso, prioridade, burstTime);
            verificarAging(p);
            processos.add(p);
        }
        System.out.println("Processo que sofrerá aging: "+processoAging);


        Queue queue0 = new Queue(0);
        Queue queue1 = new Queue(1);
        Queue queue2 = new Queue(2);
        Queue queue3 = new Queue(3);
        List<Queue> queues = new ArrayList<>(Arrays.asList(queue0, queue1, queue2, queue3));
        //----------------------------------------------------------------------------------

        // Associando os processos as suas respectivas queues conforme a prioridade
        for(i = 0; i<4 ; i++){
            int nivel = i;
            Queue queue = queues.get(nivel);
            queue.setProcessos(processos.stream().filter(p -> p.getPrioridade() == nivel).collect(Collectors.toList()));
            //ordenar os processos (SJF)
            queue.ordenar();
            processos.removeAll(queues.get(nivel).getProcessos());

            System.out.println("Processos alocados na queue de nivel " + nivel + ": " + queues.get(nivel).getProcessos() + ".\n");
        }
        //OBS: este laço for poderia ser unido ao laço abaixo, mas escolhi separar para que as etapas ficassem distinguiveis.
        //-------------------------------------------------------------------------------------------------------------------------------

        // Executando os processos
        for(i = 0; i<4 ; i++){
            Queue queueExec = queues.get(i); //queue cujos processos serão executados

            // executar processos até que a queue fique sem processos
            while(!queueExec.getProcessos().isEmpty()){
                //função que executa (simula a execução de) um processo
                executarProcesso(queueExec);
            }
        }
        //--------------------------------------------------------------------------------------------------------------------------
        if (processos.isEmpty()) {
            System.out.println("acabou");
        } else {
            System.out.println("deu ruim");
        }
    }

    private static void verificarAging(Processo p){
        if(p.getPrioridade() >= menorPrioridade){
            if(p.getBurstTime() > maiorBurstTime){
                menorPrioridade = p.getPrioridade();
                maiorBurstTime = p.getBurstTime();
                processoAging = p;
            }
        }
    }

    private static void executarProcesso(Queue queueExec) throws InterruptedException {
        // o indice do processo a ser executado é sempre zero
        // pois o processo é removido da queue após ser executado, fazendo com que o indice do processo seguinte passe a ser zero.
        System.out.println("Processo " + queueExec.getProcessos().get(0).getNome() +" sendo executado: " +
                queueExec.getProcessos().get(0).getBurstTime() + ".\n");
        // Sleep com o tempo do processo para simular a execução, multiplicado por 1000 pois reecbe em milissegundos
        Thread.sleep(queueExec.getProcessos().get(0).getBurstTime() * 1000L);
        queueExec.getProcessos().remove(0);
    }

}