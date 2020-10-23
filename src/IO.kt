
import java.io.File
import java.io.FileWriter
import java.util.*
import kotlin.collections.ArrayList


class IO(private val map: Pair<String, Int>?=null, val IIpair: Pair<String, PostingList>? =null, val key:String?= null) {


    private val file: File

    companion object{
        fun append(token: String,id:Int){
            if (token != "" || token != null) {

                val file=File("PWD"+token+".txt")//this part is a hard code to specify directory
                try {

                    file.appendText(id.toString()+" ")

                }catch (ec:Exception){

                }
            }
        }
    }

    init {
        if (map !=null) {
            file = File("PWD/${map.first}.txt")
        }else if (IIpair != null){
            file = File("PWD/${IIpair.first}.txt")
        }else{
            file = File("PWD/$key.txt")

        }
    }

     fun writePostingList(postingList: PostingList) {
         try {


             val writer = FileWriter(file.path)
             for (id in postingList.toList()) {
                 writer.append(id.toString()+" ")
             }
             writer.close()

         }catch (ex:Exception){}
     }
     fun readPostingList(): PostingList{
         val list = ArrayList<Int>()
         val intlist= ArrayList<Int>()
         try {

             val scan = Scanner(file)
             var list = scan.nextLine().split(" ")

             for (i in list){
                 intlist.add(Integer.valueOf(i))
             }
             scan.close()
         }catch (ex:Exception){}
         return PostingList(list=intlist)
    }



}

private fun ByteArray.toInt(): Int {
    var result= 0
    for(i in this){
        result += i.toInt()
    }
    return result
}

