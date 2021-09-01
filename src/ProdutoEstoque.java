//Classe herança
public class ProdutoEstoque extends Produto{
    
    private String fornecedor;
    
    public ProdutoEstoque(){

    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public ProdutoEstoque(String nome, double valor, int cod, String marca, String lote, int quantidade,
            String fornecedor) {
        //super refeencia a nossa classe mãe, que é Produto
        super(nome, valor, cod, marca, lote, quantidade);
        this.setFornecedor(fornecedor);
    }
    //método para modificar a quantidade de produtos que estão estocados
    public boolean diminuiQuanttidade(int quantidade){
        /*
        validando se a quantidade é um valor esperado e que a quantidade do estoque antende ao que 
        o usuário deseja comprar
        */
        if (quantidade > 0 && this.quantidade>quantidade) {
            this.quantidade-=quantidade;
            return true;
        }else{
            return false;
        }
        
    }
    
}
