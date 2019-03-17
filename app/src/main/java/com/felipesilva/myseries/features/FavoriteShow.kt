package com.felipesilva.myseries.features

import android.content.Context
import android.view.View
import com.felipesilva.myseries.R
import java.io.*

class FavoriteShow {
    companion object {
        private val listFavorites = mutableSetOf<String>()

        fun getFavorites(context: Context) : MutableSet<String> {
            loadFavorites(context)
            return listFavorites
        }

        fun isFavorite(name: String) = listFavorites.filter { it.equals(name) }.any()

        private fun loadFavorites(context: Context) : MutableSet<String>{
            lateinit var retorno: MutableSet<String>

            val FILE_NAME = "favorite_list"
            val file : File = context.getFileStreamPath(FILE_NAME)

            if (file.exists()) {
                val fis = FileInputStream(file)
                val ois = ObjectInputStream(fis)

                if (listFavorites.isNotEmpty())
                    listFavorites.clear()

                retorno = ois.readObject() as MutableSet<String>

                listFavorites.addAll(retorno)

                fis.close()
                ois.close()
            }

            return listFavorites
        }

        fun addFavorite(name: String, context: Context) {
            val FILE_NAME = "favorite_list"
            val file : File = context.getFileStreamPath(FILE_NAME)

            val fos = FileOutputStream(file)
            val oos = ObjectOutputStream(fos)

            listFavorites.add(name)

            oos.writeObject(listFavorites)
            oos.close()
            fos.close()
        }

        fun removeFavorite(name: String, context: Context) {
            val FILE_NAME = "favorite_list"
            val file : File = context.getFileStreamPath(FILE_NAME)

            val fos = FileOutputStream(file)
            val oos = ObjectOutputStream(fos)

            listFavorites.remove(name)

            oos.writeObject(listFavorites)
            oos.close()
            fos.close()
        }
    }
}