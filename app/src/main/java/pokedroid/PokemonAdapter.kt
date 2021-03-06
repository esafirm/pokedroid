package pokedroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.pokemon_view_holder.view.*
import pokedroid.data.PokemonsQuery

class PokemonAdapter : PagedListAdapter<PokemonsQuery.Pokemon, PokemonAdapter.PokemonViewHolder>(diffCallback) {

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<PokemonsQuery.Pokemon>() {
            override fun areItemsTheSame(oldItem: PokemonsQuery.Pokemon, newItem: PokemonsQuery.Pokemon): Boolean =
                oldItem.__typename == newItem.__typename

            override fun areContentsTheSame(oldItem: PokemonsQuery.Pokemon, newItem: PokemonsQuery.Pokemon): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder =
        PokemonViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.pokemon_view_holder, parent, false)
        )

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = getItem(position)?.fragments?.pokemon
        pokemon?.let {
            Glide.with(holder.itemView.context).load(it.image).into(holder.itemView.image)
            holder.itemView.name_text.text = it.name
        }
    }

    class PokemonViewHolder(view: View) : RecyclerView.ViewHolder(view)
}