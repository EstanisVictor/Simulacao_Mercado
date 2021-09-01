import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Mercado {

    public static void cadastroProdutosEstoque(){
        Scanner teclado = new Scanner(new BufferedInputStream(System.in));
        try{
            System.out.println("nforme o nome do produto");
            String nome = teclado.nextLine();

            System.out.println("Informe o valor unitário do produto");
            double valor = teclado.nextDouble();
            teclado.skip("\n");

            System.out.println("Informe a marca do produto");
            String marca = teclado.nextLine();

            System.out.println("Inforrme codigo vinculado ao produto");
            int cod = teclado.nextInt();
            teclado.skip("\n");

            System.out.println("Informe o lote do produto");
            String lote = teclado.nextLine();

            System.out.println("Informe a quantidade dessse produto em estoque");
            int quant = teclado.nextInt();
            teclado.skip("\n");

            System.out.println("Informe o fornecedor desse produto");
            String fornece = teclado.nextLine();

            if (!nome.isEmpty() && !marca.isEmpty() && !lote.isEmpty() && !fornece.isEmpty()) {
                if (valor > 0 && quant >= 0 && cod > 0) {
                    ProdutoEstoque novoProduto = new ProdutoEstoque(nome, valor, cod, marca, lote, quant, fornece);
                    FakeBancoDados.insereProdutoEstoque(novoProduto);
                }else{
                    System.err.println("Os valores numéricos precisam ser numéricos");
                }
            }else{
                System.err.println("Nenhum parametro do cadastro pode estar vazio!!");
            }
        }catch(InputMismatchException ex){
            System.err.println("As informações de cod, valor e quantidade devem ser um tipo númerico");
        }
    }

    public static void cadastradoClientes(){
        Scanner teclado = new Scanner(new BufferedInputStream(System.in));
        
        System.out.println("Informe o nome do cliente");
        String nome =teclado.nextLine();
        
        System.out.println("Informe o endereço do cliente");
        String endereco = teclado.nextLine();
        
        System.out.println("Informe o número do cliente");
        String tel = teclado.nextLine();

        //enquanto o telefone não for entre 8 a 13 digitos e não for de tipo numérico
        while (!(tel.length()>=8 && tel.length()<=13 && tel.matches("(0-9)+"))) {
            System.out.println("O teleforne deve conter apenas números e possuir de 8 a 13 digitos");
            tel  = teclado.nextLine();
        }
        Cliente novoCliente = new Cliente(nome, endereco, tel);
        if(!nome.isEmpty() && !endereco.isEmpty()){
            FakeBancoDados.insereCliente(novoCliente);
        }else{
            System.err.println("Os campos informados não podem estar vazio");
        }
        
    }

    public static void removerProduto(Venda novaVenda){
        Scanner teclado = new Scanner(new BufferedInputStream(System.in));
        
        System.out.println("Indique quais produtos (numero) você deseja remover da compra (-1 para encerrar)");
        while (true) {
            int indice  = teclado.nextInt();
            if (indice<0) {
                break;
            }
            if (indice>0 && indice<= novaVenda.getQuantidadeProdutos()) {
                //remover da compra
                novaVenda.removeProdutoIndice(indice);
                novaVenda.imprimeVenda();
            }
        }
    }

    public static void realizaVenda(){
        Scanner teclado = new Scanner(new BufferedInputStream(System.in));

        Venda novaVenda = new Venda();
        
        //leitura de todos os produtos no carrinho
        while (true) {
            try{
                System.out.println("Inforrme o codigo do produto (valor negativo para encerrar)");
                int cod = teclado.nextInt();

                //condição que encerra o laço
                if (cod<=0) {
                    break;
                }

                ProdutoEstoque prod = FakeBancoDados.recuperaProdutoCod(cod);
            
                if (prod!=null) {
                    //existe no estoque
                    System.out.println("Informe a quantidade que deseja comprar do produto: "+prod.getNome());
                    int quantidade  = teclado.nextInt();
                    //verificando se existe produto suficiente para prosseguir com a compra
                    if (prod.getQuantidade()>=quantidade) {
                        //"clonar" o produto em estoque
                        Produto produtoComprado = new Produto(prod.getNome(), prod.getValor(), 
                        prod.getCod(), prod.getMarca(), prod.getLote(), quantidade);//diferentemente estou pegando a quantidade informada pelo usuário e não a do estoue
                        //método que atualiza a quantidade do estoque
                        if(prod.diminuiQuanttidade(quantidade)){
                            novaVenda.adicionarProdCarinho(produtoComprado);
                        }                   
                    }
                }else{
                    //não existe prroduto cadastrado com o codigo informado
                    System.err.println("ESSE CÓDIGO NÃO ESTÁ VINCULADO A NENHUM PRODUTO");
                }
            }catch(InputMismatchException ex){
                System.err.println("O código e a quantidade devem ser valores númericos");
                teclado = new Scanner(new BufferedInputStream(System.in));
            }
        }
        novaVenda.imprimeVenda();
        System.out.println("\nDeseja remover algum produto da compra? (1 - Sim e 2 - não)");
        int op = teclado.nextInt();
        if (op==1) {
            removerProduto(novaVenda);
        }
        FakeBancoDados.insereVenda(novaVenda);
    }

    public static void limparConsole() throws IOException, InterruptedException{
        //limpar o console do VsCode
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }
    
    public static void menuInformacoes(){
        Scanner teclado = new Scanner(new BufferedInputStream(System.in));
        System.out.println("Informe o que você deseja realizar:\n"
        +"1 - Para realizar a venda\n"
        +"2 - Para realizar o cadastro do cliente\n"
        +"3 - Para realizar o cadastro do produto no estoque\n"
        +"4 - Para Sair");
        int op = teclado.nextInt();
        while(op!=4){
            while(op<=0 || op>4){
                System.out.println("Esse numero não existe, informe novamente...\n\n");
                System.out.println("Informe o que você deseja realizar:\n"
                +"1 - Para realizar a venda\n"
                +"2 - Para realizar o cadastro do cliente\n"
                +"3 - Para realizar o cadastro do produto no estoque\n"
                +"4 - Para Sair");
                op = teclado.nextInt();
            }
            if (op==1) {
               realizaVenda();
            }
            if (op==2) {
               cadastradoClientes();
            }
            if (op==3) {
               cadastroProdutosEstoque();
            }
            if (op==4) {
                break;
            }
            System.out.println("Informe o que você deseja realizar:\n"
            +"1 - Para realizar a venda\n"
            +"2 - Para realizar o cadastro do cliente\n"
            +"3 - Para realizar o cadastro do produto no estoque\n"
            +"4 - Para Sair");
            op = teclado.nextInt();
        }
    }
    public static void main(String[] args) throws Exception {
        limparConsole();
        FakeBancoDados.caregarInfo();
        menuInformacoes();
    }
}