import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o nome do cliente:");
        cliente.setNome(scanner.next());

        double limiteDiario = 1000;
        Conta cc = new ContaCorrente(cliente);
        Conta poupanca = new ContaPoupanca(cliente);

        // Loop para iterar sobre as operações
        while (true) {
            System.out.println("Digite a operação desejada (s para saque, d para depósito, t para transferência e q para sair):");
            String operacao = scanner.next().toLowerCase();

            switch (operacao) {
                case "s":
                    System.out.println("Digite o valor do saque:");
                    double valorSaque = scanner.nextDouble();
                    if (valorSaque == 0) {
                        System.out.println("Transações encerradas.");
                    } else if (valorSaque < 0) {
                        System.out.println("Valor de saque inválido. O valor não pode ser negativo.");
                    } else if (valorSaque > limiteDiario || valorSaque > cc.getSaldo()) {
                        System.out.println("Limite diário de saque atingido ou saldo insuficiente. Transações encerradas.");
                    } else {
                        limiteDiario -= valorSaque;
                        
                        cc.sacar(valorSaque);
                        System.out.printf("Saque de %.2f realizado. Saldo restante: %.2f%n", valorSaque, cc.getSaldo());
                    }
                    break;

                case "d":
                    System.out.println("Digite o valor do depósito:");
                    double valorDeposito = scanner.nextDouble();
                    cc.depositar(valorDeposito);
                    System.out.printf("Depósito de %.2f realizado.%n", valorDeposito);
                    break;

                case "t":
                    System.out.println("Digite o valor da transferência:");
                    double valorTransf = scanner.nextDouble();
                    if (valorTransf == 0) {
                        System.out.println("Transações encerradas.");
                    } else if (valorTransf < 0) {
                        System.out.println("Valor de transferência inválido. O valor não pode ser negativo.");
                    } else if (valorTransf > limiteDiario) {
                        System.out.println("Limite diário de saque atingido. Transações encerradas.");
                    } else {
                        limiteDiario -= valorTransf;
                        cc.transferir(valorTransf, poupanca);
                        System.out.printf("Transferência de %.2f realizada. Saldo restante: %.2f%n", valorTransf, cc.getSaldo());
                    }
                    break;

                case "q":
                    System.out.println("Transações encerradas.");
                    scanner.close();
                    cc.imprimirExtrato();
                    poupanca.imprimirExtrato();
                    return;

                default:
                    System.out.println("Operação inválida. Tente novamente.");
                    break;
            }
        }
    }
}
