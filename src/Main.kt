fun main(args: Array<String>){

    fun indexing(){
        print("specify the documents directory")
        val directory= readLine()
        DocStore.addDocumentsFromSourceDirectory(directory)
        val docs= DocStore.getAll()
        for (doc in docs){
            InvertedIndex.build(doc)

        }
        InvertedIndex.sort()
        print("indexing finished")
    }



    while (true){
        val input= readLine()?.split(" ")
        if (input != null) {
            for (cmd in input){
                if (cmd == "Q"){
                    break
                }else if (cmd == "Indexing"){
                    indexing()
                }else {
                    inputProcess(input)
                }
            }
        }else
            continue
    }



}

fun inputProcess(input: List<String>) {
    var notCase = false
    var notcount=0
    var firstPart:PostingList? =PostingList()
    var secondPart:PostingList? = PostingList()
    var andCase = false
    var orCase = false
    for(word in input){
        if (word == "Not"){
            notCase = true
            notcount++
            continue
        }else if (notCase){
            notCase =false

            if (word[0].isLowerCase()){
                if (InvertedIndex.getTheListOf(word) != null){
                    if ((notcount % 2) ==1) {
                        if (andCase || orCase) {
                            secondPart = InvertedIndex.getTheListOf(word)?.not()
                        } else {
                            firstPart = InvertedIndex.getTheListOf(word)?.not()
                        }
                    }else{
                        if (andCase || orCase) {
                            secondPart = InvertedIndex.getTheListOf(word)
                        } else {
                            firstPart = InvertedIndex.getTheListOf(word)

                        }
                    }

                }
            }else{
                print("command error")
                break
            }

        } else if (word == "And"){
            andCase = true
            continue
        } else if (word == "Or"){
            orCase = true
            continue
        }
        if (InvertedIndex.getTheListOf(word) != null) {
            if (andCase) {
                andCase = false
                if (firstPart == null) {
                    print("command error")
                    break
                }
                firstPart = firstPart.and(other = InvertedIndex.getTheListOf(word)!!)
            }
            else if (orCase) {
                orCase = false
                if (firstPart == null) {
                    print("command error")
                    break
                } else {
                    firstPart = firstPart.or(other = InvertedIndex.getTheListOf(word)!!)
                    secondPart = null
                }
            } else {
                if (word[0].isLowerCase()) {
                    firstPart = InvertedIndex.getTheListOf(word)!!
                } else {
                    print("comand error")
                    break
                }

                // ab OR c

            }
        }
    }

    print("result: \n")
    print(firstPart?.toList())

}

