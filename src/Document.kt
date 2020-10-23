import java.util.concurrent.atomic.AtomicInteger

class Document(private val name: String, val body:String ){
    companion object{
        private val docIdGenerator : AtomicInteger = AtomicInteger(0)
        private var lastId : Int = 0
        fun getlastId():Int{
            return lastId
        }

    }

    public val docId:Int
    init {
        DocStore.add(this)
        docId = docIdGenerator.incrementAndGet()
        lastId = maxOf(docId, lastId)
    }
}