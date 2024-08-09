fun main() {
    var arvore = ArvoreCladistica()
    do {
        exibirMenu()
        val opcao = readLine()!!.toInt()
        when (opcao) {
            0 -> println("Saindo da árvore. Até mais!")
            1 -> inserirElemento(arvore)
            2 -> arvore.visualizar(arvore.getRaiz(), 0)
            3 -> {
                print("Digite o nome do elemento: ")
                val nomeElemento = readLine()!!
                println(if (arvore.existe(ElementoTaxonomico(nomeElemento))) "Elemento encontrado." else "Elemento não encontrado.")
            }
            4 -> {
                print("Digite o nome do elemento: ")
                val nomeElemento = readLine()!!
                val no = arvore.buscar(ElementoTaxonomico(nomeElemento))
                println(no?.let { "Elemento encontrado: ${it.dado}" } ?: "Elemento não encontrado.")
            }
            5 -> {
                if (arvore.getRaiz() == null) {
                    println("A árvore está vazia.")
                } else {
                    print("Digite o nome do elemento: ")
                    val nomeElemento = readLine()!!
                    arvore.apagar(ElementoTaxonomico(nomeElemento))
                }
            }
            6 -> {
                print("Digite o nome do elemento: ")
                val nomeElemento = readLine()!!
                arvore.imprimirRelacaoFilogenetica(ElementoTaxonomico(nomeElemento))
            }
            else -> println("Opção inválida. Tente novamente.")
        }
    } while (opcao != 0)
}

fun exibirMenu() {
    println("=== Árvore Cladística ===")
    println("0. SAIR");
    println("1. Inserir elemento taxonômico");
    println("2. Visualizar árvore");
    println("3. Buscar elemento taxonômico");
    println("4. Detalhar elemento taxonômico");
    println("5. Remover elemento taxonômico");
    println("6. Imprimir relação filogenética");
    print("Escolha uma opcao (0-6): ");
}

fun inserirElemento(arvore: ArvoreCladistica) {
    print("Digite o nome do elemento: ")
    val nomeElemento = readLine()!!
    val tipoElemento = obterTipoElemento()
    val elemento = ElementoTaxonomico(nomeElemento, tipoElemento)
    var pai: No<ElementoTaxonomico>? = null

    if (arvore.getRaiz() != null) {
        while (pai == null) {
            print("Digite o nome do elemento pai: ")
            val nomePai = readLine()!!
            pai = arvore.buscar(ElementoTaxonomico(nomePai))

            if (pai == null) {
                println("Elemento pai não encontrado.")
            }
        }
    }

    arvore.inserir(elemento, pai)
}

fun obterTipoElemento(): String {
    val tipos = arrayOf("Reino", "Filo", "Classe", "Ordem", "Familia", "Gênero", "Espécie")
    var tipoElemento: String? = null

    while (tipoElemento == null) {
        tipos.forEachIndexed { index, tipo ->
            println("${index + 1}. $tipo")
        }

        print("Escolha o tipo do elemento (1-7): ")
        val opcaoTipo = readLine()!!.toInt()

        if (opcaoTipo in 1..tipos.size) {
            tipoElemento = tipos[opcaoTipo - 1]
        } else {
            println("Opção inválida. Tente novamente.")
        }
    }

    return tipoElemento
}