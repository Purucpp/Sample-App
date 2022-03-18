package `in`.janitri.sampleapp

data class ItemsViewModel(val image: Int, val text: String, val url:String, var isSelected: Boolean) {

    @JvmName("setSelected1")
    fun setSelected(selected: Boolean) {
        isSelected = selected
    }
}
