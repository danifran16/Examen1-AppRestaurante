package cl.danig.android.examen1_apprestaurante.modelo

class CuentaMesa(
//    val itemsListaMesa: MutableList<ItemMesa> = mutableListOf<ItemMesa>(),
//    var aceptaPropina: Boolean = true
    val mesa: Int
) {
    val itemsListaMesa = mutableListOf<ItemMesa>()
    var aceptaPropina = true


    fun agregarItem(itemsMenu: ItemMenu, cantidad: Int){ // agrega un item mas, como pastel o cazuela desde ItemMesa
        val agregarItemsMesa = ItemMesa(cantidad,itemsMenu)
        itemsListaMesa.add(agregarItemsMesa)
    }

    fun agregarItem(itemsMesa:ItemMesa){ //agrega un item a la cuenta de la mesa
        itemsListaMesa.add(itemsMesa)
    }

    fun calcularTotalSinPropina():Int {
        var totalMesa = 0
        for (itemLista in itemsListaMesa){
            totalMesa += itemLista.calcularSubtotal()
        }
        return totalMesa
    }

    fun calcularPropina():Int {
        return ((calcularTotalSinPropina() * 10) / 100)
    }

    fun calcularTotalConPropina():Int{
        return calcularTotalSinPropina() + calcularPropina()
    }


}