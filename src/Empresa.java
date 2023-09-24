public class Empresa {
    private Integer id;
    private String cnpj;
    private String nome;
    private Double taxa;
    private Double saldo;
    private boolean transacaoRealizada;

    public Empresa(){
        super();
        transacaoRealizada = false;
    }

    public Empresa(Integer id, String cnpj, String nome, Double taxa, Double saldo){
        super();
        this.id = id;
        this.cnpj = cnpj;
        this.nome = nome;
        this.taxa = taxa;
        this.saldo = saldo;
    }
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Double getTaxa() {
		return taxa;
	}

	public void setTaxa(Double taxa) {
		this.taxa = taxa;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

    public boolean isTransacaoRealizada() {
        return transacaoRealizada;
    }

    //Metodo para marcar que uma transação foi realizada.
    public void marcarTransacaoRealizada() {
        transacaoRealizada = true;
    }

    //INICIO DOS CALCULOS

    //APLICAÇAO DA TAXA DE SERVIÇO
    public void aplicarTaxa() {
        saldo -= taxa; //subtrair o valor da taxa do saldo da empresa
    }

    //CALCULO DO DEPOSITO AO SALDO DA EMPRESA
    public void realizarDeposito(double valor) {
        transacaoRealizada = true;
        saldo += valor;
    }

    //VERIFICAR SE HÁ SALDO SUFICIENTE E SUBTRAI O VALOR DO SAQUE
    public boolean realizarSaque(double valor) {
        if (saldo >= valor) {
            transacaoRealizada = true;
            return true;
        }
        return false;
    }
}
