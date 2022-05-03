package kg.geektech.homework3

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import kg.geektech.homework3.databinding.ItemListBinding

class Adapter(private var listener: Listener) :
    RecyclerView.Adapter<Adapter.AdapterHolder>() {

    private val imageList = arrayListOf<Uri>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return AdapterHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterHolder, position: Int) {
        holder.bind(imageList[position], listener)
    }

    override fun getItemCount() = imageList.size


    class AdapterHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ItemListBinding.bind(item)

        fun bind(mainImage: Uri, listener: Listener) = with(binding) {
            image.setImageURI(mainImage) //Тут устанавливаем изображение
            itemView.setOnClickListener { //Слушатель на изображенеи
                if (!imageShadow.isVisible) { // Проверка то что тени выключены
                    listener.onClick(mainImage) // Тут идет нажатие на изображение
                    imageShadow.visibility = VISIBLE // Установка видимости
                } else imageShadow.visibility = INVISIBLE // Установка невидимости
            }
        }
    }

    //Интерфейс
    interface Listener {
        fun onClick(mainImage: Uri)
    }
}