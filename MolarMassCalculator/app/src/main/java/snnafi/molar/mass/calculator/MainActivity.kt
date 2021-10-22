/*
 * Copyright © 2021 Shahriar Nasim Nafi. All rights reserved.
 */

package snnafi.molar.mass.calculator

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import snnafi.molar.mass.calculator.adapter.ElementInfoAdapter
import snnafi.molar.mass.calculator.databinding.ActivityMainBinding
import snnafi.molar.mass.calculator.model.ElementInfoKotlin
import snnafi.molar.mass.calculator.ui.ManualActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var elementInfos = arrayListOf<ElementInfoKotlin>()
    private var mass: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.formula.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.formula.text.toString().length == 1) {
                    elementInfos.clear()
                    setUpRecyclerView()
                }
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                elementInfos.clear()
                if (p0.toString().isEmpty()) {
                    binding.molecularWeight.text = ""
                    return
                }
                val sFormula = p0.toString().replace(".", "")
                performCalculation(sFormula);
                if (!isWrongFormula()) {
                    mass = getCalculatedMolarMass()
                    binding.molecularWeight.text = String.format("%.2f", mass)
                    getResult().forEach {
                        elementInfos.add(it)
                    }

                } else {
                    binding.molecularWeight.text = getString(R.string.wrongFormula)
                }
                setUpRecyclerView()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }


    fun setUpRecyclerView() {
        binding.results.layoutManager =
            GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false)
        binding.results.setHasFixedSize(true)
        val elementInfoAdapter = ElementInfoAdapter(elementInfos, mass)
        binding.results.adapter = elementInfoAdapter
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu);
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.manual -> {
                startActivity(Intent(this, ManualActivity::class.java))
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    /**
     * Native method(s) that is implemented by the 'molar_mass_calculator' native library,
     * which is packaged with this application.
     */


    external fun performCalculation(formula: String)
    external fun getCalculatedMolarMass(): Double
    external fun getResult(): ArrayList<ElementInfoKotlin>
    external fun isWrongFormula(): Boolean

    companion object {
        // Used to load the 'molar_mass_calculator' library on application startup.
        init {
            System.loadLibrary("molar_mass_calculator")

        }
    }
}