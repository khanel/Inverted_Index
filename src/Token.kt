class Token() {

    companion object{
        fun tokenize(document: Document):List<String>{

            val tokens = HashSet<String>()
            var token= String()
            for (char in document.body){
                if (char== '"' || char== '“' || char == '(')
                    continue
                token += char.toLowerCase()

              when(char){
                    ' ', ',', '\n', '\t', '.','\r','\\',':', ';','!',')',
                    '1', '2', '3', '4', '5', '6', '7', '8', '9','0','\"'-> {
                        if (token.length==1){
                            token=""
                        }else
                        if (token.length != 1) {
                            tokens.add(token.dropLast(1))
                        }
                        token= ""
                    }
                    else -> ""

                }

            }



            return tokens.toList()
        }




    }
}


