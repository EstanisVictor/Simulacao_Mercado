public class Produto {
    /*
    Essa classe produto, o nivel de acesso do objeo pecisa ser protect, pois ela é uma super classe, 
    já que a classe Prroduto Estoque irá herdar dessa classe
    */
    protected String nome;
    protected double valor;
    protected int cod;
    protected String marca;
    protected String lote;
    protected int quantidade;
    //Construtor Default
    public Produto() {

    }
    //Construtor
    public Produto(String nome, double valor, int cod, String marca, String lote, int quantidade) {
        this.nome = nome;
        this.valor = valor;
        this.cod = cod;
        this.marca = marca;
        this.lote = lote;
        this.quantidade = quantidade;
    }
    //Definindo o acesso com o get
    public int getCod() {
        return cod;
    }
    public String getLote() {
        return lote;
    }
    public String getMarca() {
        return marca;
    }
    public String getNome() {
        return nome;
    }
    public double getValor() {
        return valor;
    }
    public int getQuantidade() {
        return quantidade;
    }
    //Definindo a alteração com o set
    public void setValor(double valor) {
        this.valor = valor;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    public String toString(){
        return this.nome+" -> "+this.valor+" . "
        +this.quantidade+" = "+String.format("%.2f",(this.valor*this.quantidade));
    }

}