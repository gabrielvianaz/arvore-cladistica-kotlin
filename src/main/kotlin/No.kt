class No<T>(val dado: T) {

    private val filhos: MutableList<No<T>> = mutableListOf()

    fun getFilhos(): List<No<T>> {
        return filhos
    }

    fun adicionarFilho(filho: No<T>) {
        filhos.add(filho)
    }

    fun removerFilho(filho: No<T>): Boolean {
        return filhos.remove(filho)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other?.javaClass) return false
        other as No<*>
        return dado == other.dado
    }

    override fun hashCode(): Int {
        return dado?.hashCode() ?: 0
    }
}
