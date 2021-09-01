import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Vector;

public class Venda {
    
    private double valor;
    private Date diaHora;
    private Vector<Produto> carrinhoCompra;
    private Cliente comprrador;
    
    public Venda() {
        //iniciando o valor da venda de 0 e da data, e tem que ser no default
        this.valor = 0;
        this.diaHora = new Date();
        this.carrinhoCompra = new Vector<>();
    }



    public Venda(double valor, Date diaHora, Vector<Produto> carrinhoCompra, Cliente comprador) {
        this.valor = valor;
        this.diaHora = diaHora;
        this.carrinhoCompra = carrinhoCompra;
        this.setComprrador(comprador);
    }

    public Cliente getComprrador() {
        return comprrador;
    }

    public void setComprrador(Cliente comprrador) {
        this.comprrador = comprrador;
    }
    
    public double getValor() {
        return valor;
    }

    public Date getDiaHora() {
        return diaHora;
    }

    public void setDiaHora(Date diaHora) {
        this.diaHora = diaHora;
    }

    //metodo que adiciona o produto no carrinho e também atualizando o valor da compra final
    public void adicionarProdCarinho(Produto comprado){
        this.carrinhoCompra.add(comprado);
        
        this.valor+=comprado.getValor()*comprado.getQuantidade();
    }

    public void imprimeVenda(){
        try{
            File arquivo = new File("notafiscal.txt");
            int numero = 1;
            if (!arquivo.exists()) {
                arquivo.createNewFile();
            }
            FileWriter arq = new FileWriter(arquivo);
            BufferedWriter marcaEscrita = new BufferedWriter(arq);
            marcaEscrita.write("=========================================================================");
            marcaEscrita.newLine();
            for(Produto p : this.carrinhoCompra){
                marcaEscrita.write(numero+" "+p);
                numero++;
                marcaEscrita.newLine();
            }
            marcaEscrita.write("=========================================================================");
            marcaEscrita.newLine();
            marcaEscrita.write("Total: R$"+String.format("%.2f",this.valor));
            marcaEscrita.close();
            arq.close();
        }catch(FileNotFoundException ex){
            ex.getMessage();
        }catch(IOException e){
            e.getMessage();
        }
        System.out.println("\n\n");
        System.out.println("=========================================================================");
        int num = 1;
        for(Produto p : this.carrinhoCompra){
            System.out.println(num+" "+p);
            num++;
        }
        System.out.println("=========================================================================");
        System.out.println("Total: R$"+String.format("%.2f",this.valor));
    }

    public int getQuantidadeProdutos(){
        return this.carrinhoCompra.size();
    }

    public void removeProdutoIndice(int indice){
        //indices começam de zero
        indice--;
        if (indice>=0 && indice < this.carrinhoCompra.size()) {
            Produto produtoRemovido =  this.carrinhoCompra.remove(indice);
            //recalculando o valor total da vemda
            this.valor -= produtoRemovido.getValor()*produtoRemovido.getQuantidade();
        }
    }

}
