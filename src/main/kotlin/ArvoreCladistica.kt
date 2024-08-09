class ArvoreCladistica : Arborizavel<ElementoTaxonomico> {

    private var raiz: No<ElementoTaxonomico>? = null

    override fun getRaiz(): No<ElementoTaxonomico>? {
        return this.raiz
    }

    override fun inserir(dado: ElementoTaxonomico, pai: No<ElementoTaxonomico>?) {
        val novoNo = No<ElementoTaxonomico>(dado)

        if (this.raiz == null) {
            this.raiz = novoNo
        } else {
            pai?.adicionarFilho(novoNo)
        }
    }

    override fun apagar(dado: ElementoTaxonomico) {
        val noParaRemover = buscar(dado)

        if (noParaRemover == null) {
            println("Elemento não encontrado.")
            return
        }

        if (noParaRemover == raiz) {
            raiz = null
            return
        }

        val pai = encontrarPai(raiz, noParaRemover)

        pai?.removerFilho(noParaRemover)
    }

    override fun existe(dado: ElementoTaxonomico): Boolean {
        return buscar(dado) != null
    }

    override fun buscar(dado: ElementoTaxonomico): No<ElementoTaxonomico>? {
        return buscarRecursivo(raiz, dado)
    }

    fun visualizar(no: No<ElementoTaxonomico>?, nivel: Int) {
        if (no == null) {
            return
        }

        repeat(nivel) {
            print("  ")
        }

        println(no.dado?.nome)

        for (filho in no.getFilhos()) {
            visualizar(filho, nivel + 1)
        }
    }

    fun imprimirRelacaoFilogenetica(elemento: ElementoTaxonomico) {
        val no = buscar(elemento)

        if (no == null) {
            println("Elemento não encontrado.")
            return
        }

        val elementos = mutableListOf<ElementoTaxonomico>()

        var atual = no
        while (atual != null) {
            atual.dado?.let { elementos.add(it) }
            atual = encontrarPai(raiz, atual)
        }

        elementos.reverse()

        println(elementos.joinToString(" -> ") { it.nome })
    }

    private fun buscarRecursivo(no: No<ElementoTaxonomico>?, elemento: ElementoTaxonomico): No<ElementoTaxonomico>? {
        if (no == null) return null

        if (no.dado == elemento) return no

        for (filho in no.getFilhos()) {
            val retornoBusca = buscarRecursivo(filho, elemento)
            if (retornoBusca != null) return retornoBusca
        }

        return null
    }

    private fun encontrarPai(atual: No<ElementoTaxonomico>?, alvo: No<ElementoTaxonomico>): No<ElementoTaxonomico>? {
        if (atual == null) return null

        if (atual.getFilhos().contains(alvo)) {
            return atual
        }

        for (filho in atual.getFilhos()) {
            val pai = encontrarPai(filho, alvo)
            if (pai != null) return pai
        }

        return null
    }
}
