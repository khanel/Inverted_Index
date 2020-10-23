import java.util.*
import kotlin.collections.HashMap

class InvertedIndex {
    companion object {
        private val map = HashMap<String, PostingList>()
        fun build(document: Document){
            val tokens = Token.tokenize(document)
            for (token in tokens){

                    val pl = PostingList(id=document.docId)
                    map.put(token, pl)
                    IO.append(token,document.docId)

            }

        }

        fun sort(){

            for (p in map.keys){
                val io = IO(key =p)
                var pl= io.readPostingList()
                Collections.sort(pl.toList())
                io.writePostingList(pl)
    }
}
        fun getSizeOf(token: String):Int{
            return map[token]!!.size()
        }
        fun getTheListOf(token: String):PostingList?{
            val io = IO(key = token)
            return io.readPostingList()
        }
    }
}




