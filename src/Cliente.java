public class Cliente {
    private String nome;
    private String endereco;
    private String tel;
    
    public Cliente() {

    }
    
    public Cliente(String nome, String endereco, String tel) {
      this.setNome(nome);
      this.setEndereco(endereco);
      this.setTel(tel);
    }

    public String getNome() {
      return nome;
    }

    public void setNome(String nome) {
      this.nome = nome;
    }

    public String getEndereco() {
      return endereco;
    }

    public void setEndereco(String endereco) {
      this.endereco = endereco;
    }

    public String getTel() {
      return tel;
    }

    public void setTel(String tel) {
      this.tel = tel;
    }
}
