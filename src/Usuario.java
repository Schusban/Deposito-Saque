public class Usuario {
    private String username;
    private String senha;
    private Cliente cliente;
    private Empresa empresa;
    
    public Usuario(){
        super();
    }

    public Usuario(String username, String senha, Cliente cliente, Empresa empresa){
        super();
        this.username = username;
        this.senha = senha;
        this.cliente = cliente;
        this.empresa = empresa;
    }

    // Inicio das verificações de Cliente ou Empresa

    public boolean isEmpresa(){
        return this.empresa != null;
    }

    public boolean isCliente(){
        return this.cliente != null;
    }

    //Fim das verificações de Cliente ou Empresa

    // Obtém o nome de usuário
    public String getUsername() {
        return username;
    }

    //Define o nome de usuário
    public void setUsername(String username) {
        this.username = username;
    }

    //Obtém a senha associada
    public String getSenha() {
        return senha;
    }
    
    //Define a senha associada
    public void setSenha(String senha) {
        this.senha = senha;
    }

    //Obtém o objeto Cliente associado
    public Cliente getCliente() {
        return cliente;
    }

    //Define o objeto Cliente associado
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    //Obtém o objeto Empresa associado
    public Empresa getEmpresa() {
        return empresa;
    }

    //Define o objeto Empresa associado
    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}
