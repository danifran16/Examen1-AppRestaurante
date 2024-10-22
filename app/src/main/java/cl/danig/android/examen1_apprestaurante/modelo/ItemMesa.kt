package cl.danig.android.examen1_apprestaurante.modelo

class ItemMesa(val cantidad: Int, val itemMenu: ItemMenu) {

    fun calcularSubtotal():Int{
        val setInt = itemMenu.precio.toInt()
        return cantidad * setInt
    }
}