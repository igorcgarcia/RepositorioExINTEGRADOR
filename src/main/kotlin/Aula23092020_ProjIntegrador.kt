package aula23092020

fun main(){

    var icLivros = Biblioteca("IC Livros","23/09/2020")
    var cliente1 = Cliente("Igor","123456")
    var funcionario1 = Funcionario("Igor","123456")
    var livro1 = Livro(
        "L1"
        ,"Livro 1"
        ,"Igor"
        ,2020
        ,10.00
        , 10.00
        , "Disponível")

    icLivros.cadastrarLivro(livro1)

    var livro4 = Livro(
        "L4"
        ,"Livro 4"
        ,"Igor"
        ,2020
        ,10.00
        , 10.00
        , "Disponível")

    icLivros.cadastrarLivro(livro4)

    var listaLivros1: MutableList<LivroColecao> = mutableListOf()

    listaLivros1.add(Livro(
        "L2"
        ,"Livro 2"
        ,"Igor"
        ,2020
        ,10.00
        , 10.00
        , "Disponível"))

    listaLivros1.add(Livro(
        "L3"
        ,"Livro 3"
        ,"Igor"
        ,2020
        ,10.00
        , 10.00
        , "Disponível"))

    var colecao1 = Colecao("C1"
        ,"Coleção 1"
        ,"Igor"
        ,2020
        ,10.00
        , 10.00
        , "Disponível")

    icLivros.cadastrarColecao(colecao1,listaLivros1)

//    icLivros.consultar("L1")
//    icLivros.consultar("C1")
//    icLivros.consultar("L5")

    icLivros.alugarLivro("L1",cliente1,funcionario1)
//    icLivros.alugarLivro("C1")
//    icLivros.alugarLivro("L5")

//    icLivros.efetuarVenda("L1")
    icLivros.efetuarVenda("C1",cliente1,funcionario1)
//    icLivros.efetuarVenda("L5")

    println("")
    icLivros.verificarEstoque()
//    icLivros.consultar() //Consultar acervo de livros/coleções da biblioteca


    println("\n Cliente ${cliente1.nome} - historico de Aluguel")
    cliente1.historicoAluguel?.forEach {
        var colecao = (it as? Colecao)
        colecao?.let{
            println("Coleção Alugada: ${it.codigo} | ${it.titulo} | ${it.autor} | ${it.anolancamento} | ${it.precoAluguelDia}")
        } ?: println("Livro Alugado: ${it.codigo} | ${it.titulo} | ${it.autor} | ${it.anolancamento} | ${it.precoAluguelDia}")
    }

    println("\n Cliente ${cliente1.nome} - historico de Compra")
    cliente1.historicoCompra?.forEach {
        var colecao = (it as? Colecao)
        colecao?.let{
            println("Coleção Comprada: ${it.codigo} | ${it.titulo} | ${it.autor} | ${it.anolancamento} | ${it.precoVenda}")
        } ?: println("Livro Comprado: ${it.codigo} | ${it.titulo} | ${it.autor} | ${it.anolancamento} | ${it.precoVenda}")
    }


    println("\n Funcionário ${funcionario1.nome} - historico de Aluguel")
    funcionario1.historicoAluguel?.forEach {
        var colecao = (it as? Colecao)
        colecao?.let{
            println("Coleção Alugada: ${it.codigo} | ${it.titulo} | ${it.autor} | ${it.anolancamento} | ${it.precoAluguelDia}")
        } ?: println("Livro Alugado: ${it.codigo} | ${it.titulo} | ${it.autor} | ${it.anolancamento} | ${it.precoAluguelDia}")
    }

    println("\n Funcionário ${funcionario1.nome} - historico de Venda")
    funcionario1.historicoVenda?.forEach {
        var colecao = (it as? Colecao)
        colecao?.let{
            println("Coleção Comprada: ${it.codigo} | ${it.titulo} | ${it.autor} | ${it.anolancamento} | ${it.precoVenda}")
        } ?: println("Livro Comprado: ${it.codigo} | ${it.titulo} | ${it.autor} | ${it.anolancamento} | ${it.precoVenda}")
    }

}

class Biblioteca (var nome: String, var dataCriacao: String) {
    private var listaLivros: MutableMap<LivroColecao,List<LivroColecao>> = mutableMapOf()

    fun cadastrarLivro(livro: LivroColecao){ //OK
        var listaAuxiliar: MutableList<LivroColecao> = mutableListOf()
        listaAuxiliar.add(livro)
        listaLivros.set(livro,listaAuxiliar)
        println("Livro cadastrado no Catálogo")
    }

    fun cadastrarColecao(colecaoLivros: LivroColecao, listaDeLivros: List<LivroColecao>){
        listaLivros.set(colecaoLivros,listaDeLivros)
        println("Coleção cadastrada no Catálogo")
    }

    fun consultar() {
        println("Consultar Livro/Coleção [Geral]")
        listaLivros.forEach() {
            var colecao = (it.key as? Colecao)
            colecao?.let{
                println("Coleção Encontrada: ${it.codigo} | ${it.titulo} | ${it.autor} | ${it.anolancamento}" +
                        " | ${it.precoVenda} | ${it.precoAluguelDia} | ${it.estadoAtual}")
            } ?: println("Livro Encontrado")
            it.value.forEach() {
                println("- Livro: ${it.codigo} | ${it.titulo} | ${it.autor} | ${it.anolancamento}" +
                        " | ${it.precoVenda} | ${it.precoAluguelDia} | ${it.estadoAtual}")
            }
        }
    }

    fun consultar(infoConsulta:String) {
        var encontrouInfo: Boolean = false
        listaLivros.forEach(){
            if(it.key.codigo == infoConsulta || it.key.titulo == infoConsulta){
                encontrouInfo = true
                println("Consultar Livro/Coleção")
                var colecao = (it.key as? Colecao)
                colecao?.let{
                    println("Coleção Encontrada: ${it.codigo} | ${it.titulo} | ${it.autor} | ${it.anolancamento}" +
                            " | ${it.precoVenda} | ${it.precoAluguelDia} | ${it.estadoAtual}")
                } ?: println("Livro Encontrado")
                it.value.forEach() {
                    println("- Livro: ${it.codigo} | ${it.titulo} | ${it.autor} | ${it.anolancamento}" +
                            " | ${it.precoVenda} | ${it.precoAluguelDia} | ${it.estadoAtual}")
                }
            }
        }
        if(!encontrouInfo) {
            println("Livro/Coleção não encontrado")
        }
    }

    fun alugarLivro(infoConsulta:String,cliente: Cliente, funcionario: Funcionario){
        var encontrouInfo: Boolean = false
        listaLivros.forEach(){
            if(it.key.codigo == infoConsulta || it.key.titulo == infoConsulta){
                encontrouInfo = true
                println("Alugar Livro/Coleção")
                it.key.estadoAtual = "Alugado"
                it.value.forEach() {
                    it.estadoAtual = "Alugado"
                }
                cliente.historicoAluguel = listOf(it.key)
                funcionario.historicoAluguel = listOf(it.key)
            }
        }
        if(!encontrouInfo) {
            println("Livro/Coleção não encontrado")
        }
    }

    fun efetuarVenda(infoConsulta:String,cliente: Cliente, funcionario: Funcionario){
        var encontrouInfo: Boolean = false
        listaLivros.forEach(){
            if(it.key.codigo == infoConsulta || it.key.titulo == infoConsulta){
                encontrouInfo = true
                println("Vender Livro/Coleção")
                it.key.estadoAtual = "Vendido"
                it.value.forEach() {
                    it.estadoAtual = "Vendido"
                }
                cliente.historicoCompra = listOf(it.key)
                funcionario.historicoVenda = listOf(it.key)
            }
        }
        if(!encontrouInfo) {
            println("Livro/Coleção não encontrado")
        }
    }

    fun verificarEstoque(){
        println("Verificar Estoque")
        var numeroDisponivel = 0
        var numeroAlugado = 0
        var numeroVendido = 0
        var totalValorvendido = 0.0

        listaLivros.forEach(){
            it.value.forEach() {
                when (it.estadoAtual) {
                    "Disponível" -> {
                        numeroDisponivel++
                    }
                    "Alugado" -> {
                        numeroAlugado++
                    }
                    "Vendido" -> {
                        numeroVendido++
                        totalValorvendido += it.precoVenda
                    }
                }
            }
        }
        println(" Quantidade de Livros Disponíveis: ${numeroDisponivel}\n " +
                "Quantidade de Livros Alugados: ${numeroAlugado}\n " +
                "Quantidade de Livros Vendidos: ${numeroVendido} no total R$ ${totalValorvendido}")
    }

}


abstract class LivroColecao(
    var codigo: String, var titulo: String
    , var autor: String, var anolancamento: Int
    , var precoVenda: Double, var precoAluguelDia: Double
    , var estadoAtual: String) {

}

class Livro(codigo: String, titulo: String, autor: String, anolancamento: Int, precoVenda: Double,
            precoAluguelDia: Double, estadoAtual: String)
    : LivroColecao(codigo, titulo, autor, anolancamento, precoVenda, precoAluguelDia, estadoAtual) {

}

class Colecao(codigo: String, titulo: String, autor: String, anolancamento: Int, precoVenda: Double,
            precoAluguelDia: Double, estadoAtual: String)
    : LivroColecao(codigo, titulo, autor, anolancamento, precoVenda, precoAluguelDia, estadoAtual) {

}

abstract class Pessoa (var nome:String, var rg: String, var historicoAluguel: List<LivroColecao>?, var historicoCompra: List<LivroColecao>?, var historicoVenda: List<LivroColecao>?=null){

}

class Cliente (nome: String, rg: String, historicoAluguel: List<LivroColecao>?=null, historicoCompra: List<LivroColecao>?=null) : Pessoa (nome, rg, historicoAluguel, historicoCompra){

}

class Funcionario (nome: String, rg: String, historicoAluguel: List<LivroColecao>?=null, historicoVenda: List<LivroColecao>?=null) : Pessoa (nome, rg, historicoAluguel, historicoVenda){

}