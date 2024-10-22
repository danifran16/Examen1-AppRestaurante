package cl.danig.android.examen1_apprestaurante

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import cl.danig.android.examen1_apprestaurante.modelo.CuentaMesa
import cl.danig.android.examen1_apprestaurante.modelo.ItemMenu
import cl.danig.android.examen1_apprestaurante.modelo.ItemMesa

class MainActivity : AppCompatActivity() {
//Instanciar objetos del menu
    val matcha = ItemMenu(nombre = "Matcha", precio = "2500")
    val mochi = ItemMenu(nombre = "Mochi", precio = "1000")

//Instancia de objeto CuentaMesa
    val numMesa = 0
    val totalCuenta = CuentaMesa(numMesa)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val idCantMatcha = findViewById<EditText>(R.id.etCantMatcha)
        val idCantMochi = findViewById<EditText>(R.id.etCantMochi)

        val watcher: TextWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun afterTextChanged(p0: Editable?) {
                calcularTotalMatcha()
                calcularTotalMochi()

                calcularTotalCuentaSP()
                calcularPropina()
                calcularTotalConPropina()
            }
        }
        idCantMatcha.addTextChangedListener(watcher)
        idCantMochi.addTextChangedListener(watcher)
    }


    //Calcular total matcha
    fun calcularTotalMatcha() {
        //capturo el id
        val idCantMatcha = findViewById<EditText>(R.id.etCantMatcha)
        val cantMatcha = idCantMatcha.text.toString().toIntOrNull() ?: 0
        val totalMatcha = ItemMesa(cantMatcha, matcha)
        //textview Matcha
        findViewById<TextView>(R.id.etTotalMatcha).text = totalMatcha.calcularSubtotal().toString()
    }

    //Calcular total mochi
    fun calcularTotalMochi() {
        //capturo el id
        val idCantMochi = findViewById<EditText>(R.id.etCantMochi)
        val cantMochi = idCantMochi.text.toString().toIntOrNull() ?: 0
        val totalMochi = ItemMesa(cantMochi, mochi)
        //textview Mochi
        findViewById<TextView>(R.id.etTotalMochi).text = totalMochi.calcularSubtotal().toString()
    }

    //Calcular total de la cuenta sin propina
    fun calcularTotalCuentaSP() {
        //capturo el id
        val idcantMatcha = findViewById<EditText>(R.id.etCantMatcha)
        val idcantMochi = findViewById<EditText>(R.id.etCantMochi)
        //lo paso a int o 0
        val cantMatcha = idcantMatcha.text.toString().toIntOrNull() ?: 0
        val cantMochi = idcantMochi.text.toString().toIntOrNull() ?: 0


        //saco el total entre matcha y mochi
        totalCuenta.agregarItem(matcha, cantMatcha)
        totalCuenta.agregarItem(mochi, cantMochi)

        findViewById<TextView>(R.id.etTotalCuenta).text =
            totalCuenta.calcularTotalSinPropina().toString()
    }

    fun calcularPropina(){
        totalCuenta.calcularPropina()
        findViewById<TextView>(R.id.etPropina).text =
            totalCuenta.calcularPropina().toString()

    }

    fun calcularTotalConPropina(){
        totalCuenta.calcularTotalConPropina()
        findViewById<TextView>(R.id.etTotalMasPropina).text =
            totalCuenta.calcularTotalConPropina().toString()
    }

}





