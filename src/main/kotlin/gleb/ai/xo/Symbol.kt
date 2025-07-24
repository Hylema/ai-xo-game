package gleb.ai.xo

enum class Symbol {
    X,
    O

    ;

    fun getReverseSymbol(): Symbol {
        return when (this) {
            X -> O
            else -> X
        }
    }
}