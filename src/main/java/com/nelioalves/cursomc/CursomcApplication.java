package com.nelioalves.cursomc;

import com.nelioalves.cursomc.domain.*;
import com.nelioalves.cursomc.domain.enums.EstadoPagamento;
import com.nelioalves.cursomc.domain.enums.TipoCliente;
import com.nelioalves.cursomc.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.AbstractRabbitListenerContainerFactoryConfigurer;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

    //teste
   // @Autowired
   // private CategoriaTesteRepository categoriaTesteRepository;

    //
    //@Value("${hibernate.default_schema:TESTE}")
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public static void main(String[] args) {
        SpringApplication.run(CursomcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //teste
       // CategoriaTeste categoriaTeste = new CategoriaTeste("catTeste");

       // categoriaTesteRepository.saveAll(Arrays.asList(categoriaTeste));
        //

        Categoria cat1 = new Categoria( "Informática");
        Categoria cat2 = new Categoria("Escritório");
        Categoria cat3 = new Categoria("Cama mesa e banho");
        Categoria cat4 = new Categoria( "Eletrônicos");
        Categoria cat5 = new Categoria( "Jardinagem");
        Categoria cat6 = new Categoria("Decoração");
        Categoria cat7 = new Categoria( "Perfumaria");

        Produto p1 = new Produto("Computador", 2000.00);
        Produto p2 = new Produto("Impressora 800", 800.00);
        Produto p3 = new Produto("Mouse", 80.00);

        cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProdutos().addAll(Arrays.asList(p2));


        p1.getCategorias().addAll(Arrays.asList(cat1));
        p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
        p3.getCategorias().addAll(Arrays.asList(cat1));


        categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
        categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
        produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

        Estado est1 = new Estado("Minas Gerais");
        Estado est2 = new Estado("Sao Paulo");

        Cidade c1 = new Cidade("Uberlandia", est1);
        Cidade c2 = new Cidade("Sao Paulo", est2);
        Cidade c3 = new Cidade("Campinas", est2);

        est1.getCidades().addAll(Arrays.asList(c1));
        est1.getCidades().addAll(Arrays.asList(c2, c3));

        estadoRepository.saveAll(Arrays.asList(est1, est2));
        cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));

        Cliente cli1 = new Cliente("Maria Silva", "maria@email", "36378912377", TipoCliente.PESSOAFISICA);
        cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

        Endereco e1 =new Endereco("Rua Flores", "300", "Apto 303", "Jardim", "38220384", cli1, c1);
        Endereco e2 =new Endereco("Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);

        cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

        clienteRepository.saveAll(Arrays.asList(cli1));
        enderecoRepository.saveAll(Arrays.asList(e1,e2));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Pedido ped1 = new Pedido(sdf.parse("30/09/2017 10:32"), cli1, e1);
        Pedido ped2 = new Pedido(sdf.parse("10/10/2017 19:35"), cli1, e2);

        Pagamento pgto1 = new PagamentoComCartao(EstadoPagamento.QUITADO, ped1, 6);
        ped1.setPagamento(pgto1);

        Pagamento pgto2 = new PagamentoComBoleto(EstadoPagamento.PENDENTE, ped2,sdf.parse("20/10/2017 00:00)") ,null);
        ped2.setPagamento(pgto2);

        cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

        pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
        pagamentoRepository.saveAll(Arrays.asList(pgto1, pgto2));

        ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
        ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
        ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

        ped1.getItens().addAll(Arrays.asList(ip1,ip2));
        ped2.getItens().addAll(Arrays.asList(ip3));

        p1.getItens().addAll(Arrays.asList(ip1));
        p2.getItens().addAll(Arrays.asList(ip3));
        p3.getItens().addAll(Arrays.asList(ip2));

        itemPedidoRepository.saveAll(Arrays.asList(ip2,ip2,ip3));


    }
}
