package adapter

import Models.Result
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wk7taskprecious.ActivityDetails
import com.example.wk7taskprecious.R

class PokemonAdapter(private val results: List<Result>): RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val textView = view.findViewById<TextView>(R.id.pokemon_name)
        val imageView = view.findViewById<ImageView>(R.id.pokemon_img)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = results[position]
        holder.textView.text = result.name
        holder.imageView.apply {
            Glide.with(this)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/${position + 1}.png")
                .into(holder.imageView)
        }
        holder.imageView.setOnClickListener {
            val intent = Intent(holder.imageView.context, ActivityDetails::class.java)
            intent.putExtra("name", result.name)

            startActivity(holder.itemView.context, intent, null)
            Toast.makeText(holder.imageView.context, "${result.name}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int = results.size
}