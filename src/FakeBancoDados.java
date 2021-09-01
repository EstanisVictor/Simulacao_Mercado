import java.util.Vector;

//esta classe representa um banco de dados, só que não haverá integração do banco
public class FakeBancoDados {
  //representam o que seria a tabela do banco de dados
  private static Vector<ProdutoEstoque> produtosEstocados = new Vector<>();
  private static Vector<Venda> historicoVendas = new Vector<>();
  private static Vector<Cliente> clientes = new Vector<>();

  public static void caregarInfo(){
    produtosEstocados.add(new ProdutoEstoque("Toddy",15, 1,"pepsico","98745",500,"fonecedor A"));
    produtosEstocados.add(new ProdutoEstoque("Arrroz", 50, 2, "Cudil", "8741", 800, "fornecedor A"));
    produtosEstocados.add(new ProdutoEstoque("Feijão", 9, 3, "Tio João", "74", 1000, "fornecedor B"));
    produtosEstocados.add(new ProdutoEstoque("Biscoito Recheado", 1.6, 4, "Aymore", "8", 200, "fornecedor B"));
    produtosEstocados.add(new ProdutoEstoque("Açucar", 8.5, 5, "Doce Vida", "45", 700, "fornecedor C"));
    produtosEstocados.add(new ProdutoEstoque("Macarrão", 2.7, 6, "Vilma", "74", 740, "fornecedor C"));
    produtosEstocados.add(new ProdutoEstoque("Queijo", 21, 7, "Curral de Minas", "74", 50, "fornecedor A"));
  }

  public static ProdutoEstoque recuperaProdutoCod(int cod){
    //procurando o produto com o codigo informado
    for(ProdutoEstoque p : produtosEstocados){
      if (p.getCod() == cod) {
        return p;
      }
    }
    //nesse caso, não irá existir produto com o codigo especificado
    return null;
  }

  public static void insereVenda(Venda nova){
    
    historicoVendas.add(nova);
  }

  public static void insereCliente(Cliente novoCliente){
    clientes.add(novoCliente);
  }

  public static void insereProdutoEstoque(ProdutoEstoque novoProd){
    produtosEstocados.add(novoProd);
  }

}
