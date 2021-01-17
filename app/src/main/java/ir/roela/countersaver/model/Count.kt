package ir.roela.countersaver.model

class Count private constructor() {

    var countNumber: Long = 0
        private set
    val countNumberStr: String
        get() = countNumber.toString()

    fun addCountNumber() {
        countNumber ++
    }

    fun minusCountNumber() {
        countNumber --
    }

    fun resetCount() {
        countNumber = 0
    }

    companion object {
        var instance: Count? = null
            get() {
                if (field == null) {
                    field = Count()
                }
                return field
            }
            private set
    }
}