import java.io.File

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS", "RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class DocStore {
    companion object {
    private val docs: HashMap<Int, Document> = HashMap()

    fun addDocumentsFromSourceDirectory(directory: String?) {
        val folder = File(directory)
        val files = folder.listFiles()
        for (file in files) {
            val doc = Document(file.name, file.readText(Charsets.UTF_8))
            docs[doc.docId]=doc
        }
    }


        fun add(doc: Document){
            docs[doc.docId] = doc
        }

         fun get(id:Int):Document{
            return docs[id]!!
        }
         fun getAll():Collection<Document>{
            return docs.values
        }
    }
}



