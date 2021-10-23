/*
 * Copyright © 2021 Shahriar Nasim Nafi. All rights reserved.
 */

package snnafi.molar.mass.calculator.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import snnafi.molar.mass.calculator.R
import snnafi.molar.mass.calculator.databinding.ElementInfoViewBinding
import snnafi.molar.mass.calculator.model.ElementInfoKotlin

class ElementInfoAdapter(val elementInfos: ArrayList<ElementInfoKotlin>, var totalMass: Double) :
    RecyclerView.Adapter<ElementInfoAdapter.ElementInfoHolder>() {

    override fun onBindViewHolder(holder: ElementInfoHolder, position: Int) {
        var elementInfoKotlin = elementInfos[position]
        holder.binding.name.text = elementInfoKotlin.symbol
        holder.binding.total.text = elementInfoKotlin.number.toString()

        val molarMass = getElementMolarMassBySymbol(elementInfoKotlin.symbol)
        holder.binding.molarMass.text = String.format("%.4f g/mol", molarMass)

        val totalElementMass = molarMass * elementInfoKotlin.number
        holder.binding.subtotalMass.text = String.format("%.2f g/mol", totalElementMass)
        holder.binding.percentage.text = String.format(
            "%.2f",
            totalElementMass / totalMass * 100
        ) + holder.itemView.context.resources.getString(R.string.percentage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElementInfoHolder {
        return ElementInfoHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.element_info_view, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return elementInfos.size
    }


    class ElementInfoHolder(elementView: View) : RecyclerView.ViewHolder(elementView) {
        var binding: ElementInfoViewBinding = ElementInfoViewBinding.bind(itemView);
    }

    /**
     * Native method(s) that is implemented by the 'molar_mass_calculator' native library,
     * which is packaged with this application.
     */

    external fun getElementMolarMassBySymbol(symbol: String): Double

    companion object {
        // Used to load the 'molar_mass_calculator' library on application startup.
        init {
            System.loadLibrary("molar_mass_calculator")

        }
    }
}