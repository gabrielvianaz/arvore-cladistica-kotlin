data class ElementoTaxonomico(
    var nome: String,
    var tipo: String? = null
) {

    constructor(nome: String) : this(nome, null)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other?.javaClass) return false
        other as ElementoTaxonomico
        return nome == other.nome
    }

    override fun hashCode(): Int {
        return nome.hashCode()
    }

    override fun toString(): String {
        return "Nome: $nome, Tipo: $tipo."
    }
}
