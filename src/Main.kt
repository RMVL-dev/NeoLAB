fun main() {
    var myBookshelf = createBookshelf()
    var isContinue = true
    while (isContinue) {
        print("Моя книжная полка\n1.Положить новую книгу\n2.Отфильтровать по году\n3.Сортировать в алфавитном порядке\n4.Отобразить полку\n5.Выйти\n")
        val choice = readLine()
        when (choice) {
            "1" -> {
                myBookshelf = addNewBook(myBookshelf)
            }

            "2" -> {
                print("Введите год: ")
                val year = readLine()
                var yearInt = Int.MIN_VALUE
                if (year.isNullOrBlank() || !year.isNumeric())
                    print("Вы не ввели год издания или он введен неверно")
                else
                    yearInt = year.toInt()
                if (yearInt != Int.MIN_VALUE &&
                    myBookshelf.all { it.year != null }
                )
                    myBookshelf = filterByYear(myBookshelf, yearInt)
                else
                    print("Не все книги имеют год издания") //обработка таких искобчений не прописана в задании, поэтому выбрал самое простое


            }

            "3" -> {
                if (myBookshelf.all { it.title.isNotBlank() && it.title.isNotEmpty() })
                    myBookshelf = sortByAlphabet(bookshelf = myBookshelf)
                else
                    print("Не все книги имеют название") //обработка таких искобчений не прописана в задании, поэтому выбрал самое простое
            }

            "4" -> {
                println()
                myBookshelf.print()
                println()
            }
            "5" -> {
                isContinue = false
                print("Завершение работы")
            }

            else -> print("Действие не найдено")
        }
    }
}

fun addNewBook(bookshelf: List<Book>):List<Book>{
    print("Название книги: ")
    val bookName = readLine()
    if (bookName.isNullOrBlank())
        print("Вы не ввели название книги")
    print("Автор книги: ")
    val bookAuthor = readLine()
    if (bookAuthor.isNullOrBlank())
        print("Вы не ввели автора книги")
    print("Год издания книги: ")
    val bookYear = readLine()
    var yearInt = Int.MIN_VALUE
    if (bookYear.isNullOrBlank() || !bookYear.isNumeric())
        print("Вы не ввели год издания или он введен неверно")
    else
        yearInt = bookYear.toInt()

    val maxId = bookshelf.maxOf { it.id }

    val newBook = Book(
        id = maxId+1,
        title = bookName ?: "Неизвестная книга",
        author = bookAuthor,
        year = if (yearInt == Int.MIN_VALUE) null else yearInt
    )
    val returnedList = bookshelf.toMutableList()
    returnedList.add(newBook)

    return returnedList.toList()
}

fun createBookshelf():List<Book>{
    return listOf(
        Book(id = 1, title = "Мертвые души", author = "Гоголь Н.В.", year = 1852),
        Book(id = 2, title = "Евгений Онегин", author = "Пушкин А.С.", year = 1831),
        Book(id = 3, title = "Мастер и Маргарита", author = "Булгаков М.А.", year = 1929),
        Book(id = 4, title = "Обломов", author = "Гончаров И.А.", year = 1859),
        Book(id = 5, title = "Преступление и наказание", author = "Достаевский Ф.М.", year = 1866),
    )
}

fun filterByYear(bookshelf:List<Book>, year:Int):List<Book>{
    val filteredBooks = bookshelf.filter {book ->
        book.year?.let {
            it > year
        } == true
    }
    return filteredBooks
}

fun sortByAlphabet(bookshelf: List<Book>):List<Book>{
    val sortedList = bookshelf.sortedBy {
        it.title.first()
    }
    return sortedList
}

/**
 * Extensions
 */
fun List<Book>.print(){
    this.forEach {
        println("${it.title} ${it.author} ${it.year}")
    }
}

fun String.isNumeric():Boolean{
    return this.all { it.isDigit() }
}