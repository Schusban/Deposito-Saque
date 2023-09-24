import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception {

        //INSTANCIAR OBJETOS
        Empresa empresa = new Empresa(1, "10006547894613", "Barcen", 0.25, 0.0);
        Empresa empresa2 = new Empresa(2, "87642897566143", "Digital Inovation", 0.22, 0.0);

        Cliente cliente = new Cliente("49764375169", "Bianca", "schusban");
        Cliente cliente2 = new Cliente("47698356479", "Sandra", "sandra");

        Usuario usuario1 = new Usuario("empresa", "54321", null, empresa);
        Usuario usuario2 = new Usuario("empresa2", "54321", null, empresa2);
        Usuario usuario3 = new Usuario("cliente", "54321", cliente, null);
        Usuario usuario4 = new Usuario("cliente2", "54321", cliente2, null);

        List<Usuario> usuarios = Arrays.asList(usuario1, usuario2, usuario3, usuario4);
		List<Cliente> clientes = Arrays.asList(cliente, cliente2);
		List<Empresa> empresas = Arrays.asList(empresa, empresa2);
        executar(usuarios, clientes, empresas);
    }

    public static void executar(List<Usuario> usuarios, List<Cliente> clientes, List<Empresa> empresas){
		try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Entre com seu usuário e senha:");
            System.out.print("Usuário: ");
            String username = sc.next();
            System.out.print("Senha: ");
            String senha = sc.next();

            		List<Usuario> usuariosSearch = usuarios.stream().filter(x -> x.getUsername().equals(username))
            		.collect(Collectors.toList());
            if (usuariosSearch.size() > 0) {
            	Usuario usuarioLogado = usuariosSearch.get(0);
            	if ((usuarioLogado.getSenha().equals(senha))) {

            		System.out.println("Escolha uma opção para iniciar");
                    //TELA DE LOGIN DAS EMPRESAS
            		if (usuarioLogado.isEmpresa()) {
            			System.out.println("1 - Ver Saldo");
            			System.out.println("0 - Deslogar");
            			Integer escolha = sc.nextInt();

            			switch (escolha) {
                            //IMPRIMIR DADOS DA EMPRESA
            			case 1: {
            				System.out.println();
                            System.out.println("************************************************************");
                            System.out.println("EMPRESA SELECIONADA: " + usuarioLogado.getEmpresa().getNome());
                            
                            if (usuarioLogado.getEmpresa().isTransacaoRealizada()) {
                                double taxa = usuarioLogado.getEmpresa().getTaxa();
                                double saldoComTaxa = usuarioLogado.getEmpresa().getSaldo() - taxa;
                                System.out.println("Saldo após a dedução da taxa: " + saldoComTaxa);
                            } else {
                                System.out.println("Saldo da Empresa: " + usuarioLogado.getEmpresa().getSaldo());
                            }
                        
                            System.out.println("************************************************************");
                            executar(usuarios, clientes, empresas);
                            break;
            			}

                        //DESLOGAR
            			case 0: {
            				executar(usuarios, clientes, empresas);
            				break;
            			}
                        }
            		}

                    //TELA DE LOGINS DOS CLIENTES
                    else if (usuarioLogado.isCliente()){
            			System.out.println("1 - Sacar");
            			System.out.println("2 - Depositar");
            			System.out.println("0 - Deslogar");
            			Integer escolha = sc.nextInt();

            			switch (escolha) {
                            //SAQUE
                            case 1: {
                                System.out.println("Para realizar uma compra, escolha a empresa onde deseja comprar: ");
            				empresas.stream().forEach(x -> {
            					System.out.println(x.getId() + " - " + x.getNome());
            				});
            				    Integer escolhaEmpresa = sc.nextInt();
                                Empresa empresaDoCliente = empresas.stream().filter(x -> x.getId().equals(escolhaEmpresa))
            						.collect(Collectors.toList()).get(0);
                                System.out.println();
                                System.out.println("************************************************************");
                                System.out.println("SAQUE");
                                System.out.println("************************************************************");
                                System.out.print("Digite o valor do saque: ");
                                double valorSaque = sc.nextDouble();
                                
                                // Verifica se a empresa tem saldo suficiente e aplica a taxa de sistema
                                if (empresaDoCliente.realizarSaque(valorSaque)) {
                                    empresaDoCliente.aplicarTaxa();
                                    System.out.println("Saque realizado com sucesso.");
                                } else {
                                    System.out.println("Saldo insuficiente para realizar o saque.");
                                }
                                executar(usuarios, clientes, empresas);
                                break;
                            }

                            //DEPOSITO
                            case 2: {
                                System.out.println("Para realizar uma compra, escolha a empresa onde deseja comprar: ");
            				empresas.stream().forEach(x -> {
            					System.out.println(x.getId() + " - " + x.getNome());
            				});
                                Integer escolhaEmpresa = sc.nextInt();
                                Empresa empresaDoCliente = empresas.stream().filter(x -> x.getId().equals(escolhaEmpresa))
            						.collect(Collectors.toList()).get(0);
                                System.out.println();
                                System.out.println("************************************************************");
                                System.out.println("DEPÓSITO");
                                System.out.println("************************************************************");
                                System.out.print("Digite o valor do depósito: ");
                                double valorDeposito = sc.nextDouble();
                                empresaDoCliente.realizarDeposito(valorDeposito);
                                System.out.println("Depósito realizado com sucesso.");
                                executar(usuarios, clientes, empresas);
                                break;
                            }

                            //DESLOGAR
                            case 0: {
            				executar(usuarios, clientes, empresas);
            				break;
                            }
                        }
                    }
                    else{
            		System.out.println("Senha incorreta");
                    }
                }
            }
            else {
            	System.out.println("Usuário não encontrado");
            }
        }
	}
}