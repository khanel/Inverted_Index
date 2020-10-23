class PostingList(val list: ArrayList<Int>?=null,val id: Int=-1) {
    private val docIds = ArrayList<Int>()
    init {
        if (id != -1){
            add(id)
        }else if (!list.isNullOrEmpty()){
            for (i in list){
                add(i)
            }
        }
    }

    fun size():Int{
        return docIds.size
    }
    fun toList():List<Int>{
        return docIds.toList()
    }


     fun add(docId: Int){
       docIds.add(docId)
    }


    private fun searchAndInsert(docId: Int){//implementing binary search to find appropriate index that keeps the list sorted
        var index: Int
        var upperBound: Int = docIds.size
        var lowerBound = 0

        while (true) {
            index = (upperBound + lowerBound) / 2
            if (upperBound == lowerBound || index == upperBound || index == lowerBound) {
                if (docId > docIds[index]) {
                    docIds.add(index + 1, docId)
                }else{
                    docIds.add(index,docId)
                }
                break
            }else if (docId > docIds[index]) {
                lowerBound = index
            } else {
                upperBound = index
            }

        }
    }



    public fun and( other: PostingList): PostingList {
        var i = 0
        var j = 0
        val result: ArrayList<Int> = ArrayList()
        while (i < this.docIds.size && j < other.get().size) {
            if (this.docIds[i] == other.get()[j]) {
                result.add(this.docIds[i])
                i++
                j++
            }
            else if (this.docIds[i] < other.get()[j])
                i++
            else
                j++
        }
        return PostingList(result)
    }

    public fun or( other: PostingList): PostingList {
        var i = 0
        var j = 0
        val result: ArrayList<Int> = ArrayList()
        while (i < this.docIds.size || j < other.get().size) {
            if (this.docIds[i] == other.get()[j]) {
                result.add(this.docIds[i])
                i++
                j++
            } else if (this.docIds[i] < other.get()[j]) {
                result.add(this.docIds[i])
                i++
            } else {
                result.add(this.docIds[j])
                j++
            }
        }
        return PostingList(result)
    }

            /*
        the docIds has generated automatically from 1 and by incrementation of 1
        this method will iterate from 1 to the last generated id and if the docId equal to it
        it will be ignored else it will push to result
        the list is sorted
         */

    public fun not(): PostingList {
        val last = Document.getlastId()
        val result = ArrayList<Int>()
        var j = 0
        for (i in 0..last) {
            if (i == this.docIds[j]) {
                if (j < this.docIds.size - 1)
                    j++
            } else result.add(i)
        }

        return PostingList(result)
    }



    public fun get():ArrayList<Int>{
        return docIds
    }

annotation class EMPTY


}