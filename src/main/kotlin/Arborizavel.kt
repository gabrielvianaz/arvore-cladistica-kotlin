interface Arborizavel<T> {

    fun getRaiz(): No<T>?

    fun inserir(dado: T, pai: No<T>?)

    fun apagar(dado: T)

    fun existe(dado: T): Boolean

    fun buscar(dado: T): No<T>?
}
