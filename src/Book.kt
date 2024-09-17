data class Book(
    val id:Int,
    val title:String,
    val author: String? = null, //у некоторых книг автор не известен и они считаются народными
    val year: Int? = null//год написания может быть неизвестен
)
